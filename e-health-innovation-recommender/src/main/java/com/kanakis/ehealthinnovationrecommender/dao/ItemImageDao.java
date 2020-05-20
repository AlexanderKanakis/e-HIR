package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.ItemImage;

import java.util.Optional;

public interface ItemImageDao {

    int insertImage(ItemImage image);

    Optional<ItemImage> selectImageById(int id);

    int deleteImageById(int id);

    int updateImageById(int id, ItemImage image);
}
