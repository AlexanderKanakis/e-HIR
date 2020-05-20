package com.kanakis.ehealthinnovationrecommender.service;

import com.kanakis.ehealthinnovationrecommender.dao.ItemImageDao;
import com.kanakis.ehealthinnovationrecommender.model.Item;
import com.kanakis.ehealthinnovationrecommender.model.ItemImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@Service
public class ItemImageService {

    private final ItemImageDao itemImageDao;

    @Autowired
    public ItemImageService(@Qualifier("postgres-image") ItemImageDao itemImageDao) {
        this.itemImageDao = itemImageDao;
    }

    public int insertImage(MultipartFile image) throws IOException {
        ItemImage img = new ItemImage(image.getOriginalFilename(),
                image.getContentType(),
                compressBytes(image.getBytes()));
        return itemImageDao.insertImage(img);
    }

    public Optional<ItemImage> selectImageByItemId(int id) {
        Optional<ItemImage> compressedImg = itemImageDao.selectImageById(id);
        Optional<ItemImage> img = Optional.of(new ItemImage(compressedImg.get().getName(), compressedImg.get().getType(),
                decompressBytes(compressedImg.get().getPicByte())));
        return img;
    }

    public int deleteImageById(int id) {
        itemImageDao.deleteImageById(id);
        return 1;
    }

    public int updateImageById(int id, ItemImage image) {
        itemImageDao.updateImageById(id, image);
        return 1;
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
