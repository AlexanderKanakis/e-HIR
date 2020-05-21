package com.kanakis.ehealthinnovationrecommender.controller;

import com.kanakis.ehealthinnovationrecommender.model.ItemImage;
import com.kanakis.ehealthinnovationrecommender.service.ItemImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;


@RequestMapping("api/v1/item-image")
@RestController
@CrossOrigin
public class ItemImageController {

    private final ItemImageService itemImageService;

    @Autowired
    public ItemImageController(ItemImageService itemImageService) {
        this.itemImageService = itemImageService;
    }

    @PostMapping
    public int insertItem(@RequestBody @Valid @NotNull MultipartFile image) throws IOException {
        return itemImageService.insertImage(image);
    }

    @GetMapping(path = "{id}")
    public ItemImage selectImageById(@PathVariable("id") int id) {
        return itemImageService.selectImageByItemId(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public int deleteImageById(@PathVariable("id") int id) {
        return  itemImageService.deleteImageById(id);
    }

    @PutMapping(path = "{id}")
    public int updateImageById(@PathVariable("id") int id, @RequestBody @Valid @NotNull MultipartFile image) throws IOException{
        return itemImageService.updateImageById(id, image);
    }


}


