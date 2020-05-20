package com.kanakis.ehealthinnovationrecommender.controller;

import com.kanakis.ehealthinnovationrecommender.model.Item;
import com.kanakis.ehealthinnovationrecommender.model.Tag;
import com.kanakis.ehealthinnovationrecommender.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/item")
@RestController
@CrossOrigin
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public int insertItem(@RequestBody @Valid @NotNull Item item) {
        return itemService.insertItem(item);
    }

    @PostMapping(path = "{id}/tags")
    public int insertTagRelationShip( @PathVariable("id") int itemId,@RequestBody @Valid @NotNull Tag tag) {
        return itemService.insertTagRelationShip(itemId, tag);
    }

    @GetMapping
    public List<Item> selectAllItems() {
        return itemService.selectAllItems();
    }

    @GetMapping(path = "{id}")
    public Item selectItemById(@PathVariable("id") int id) {
        return itemService.selectItemById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public int deleteItemById(@PathVariable("id") int id) {
        return  itemService.deleteItemById(id);
    }

    @PutMapping(path = "{id}")
    public int updateItemById(@PathVariable("id") int id, @RequestBody @Valid @NotNull Item item) {
        return itemService.updateItemById(id, item);
    }

    @GetMapping(path = "{id}/tags")
    public List<Tag> getItemTags(@PathVariable("id") int id) {
        return itemService.getItemTags(id);
    }
}
