package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.Item;
import com.kanakis.ehealthinnovationrecommender.model.Tag;


import java.util.List;
import java.util.Optional;

public interface ItemDao {

    int insertItem(Item item);

    List<Item> selectAllItems();

    Optional<Item> selectItemById(int id);

    int deleteItemById(int id);

    int updateItemById(int id, Item item);

    int insertTagRelationShip(int itemId, Tag tag);

    List<Tag> getItemTags(int id);

    double updateRatingById(int id, Item item);

    double selectRatingById(int id);

    int deleteTagRelations(int id);
}
