package com.kanakis.ehealthinnovationrecommender.service;

import com.kanakis.ehealthinnovationrecommender.dao.ItemDao;
import com.kanakis.ehealthinnovationrecommender.model.Item;
import com.kanakis.ehealthinnovationrecommender.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class ItemService {
    Double itemListLength = 1.1;

    private final ItemDao itemDao;

    @Autowired
    public ItemService(@Qualifier("postgres-item") ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public int insertItem(Item item) {
        return itemDao.insertItem(item);
    }

    public int insertTagRelationShip(int itemId, Tag tag) {
        return itemDao.insertTagRelationShip(itemId, tag);
    }

    public List<Item> selectAllItems() {
        return itemDao.selectAllItems();
    }

    public Optional<Item> selectItemById(int id) {
        return itemDao.selectItemById(id);
    }

    public int deleteItemById(int id) {
        itemDao.deleteItemById(id);
        return 1;
    }

    public int updateItemById(int id, Item item) {
        itemDao.updateItemById(id, item);
        return 1;
    }

    public List<Tag> getItemTags (int id) {
        return itemDao.getItemTags(id);
    }

    public double determineIF(double num) {
        return 1 + Math.log(num);
    }

    public double determineIDF() {
        return Math.log(determineIDF()/this.itemListLength);
    }


}
