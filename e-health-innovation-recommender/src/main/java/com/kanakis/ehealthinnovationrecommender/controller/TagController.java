package com.kanakis.ehealthinnovationrecommender.controller;

import com.kanakis.ehealthinnovationrecommender.model.Tag;
import com.kanakis.ehealthinnovationrecommender.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/tag")
@RestController
@CrossOrigin
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public void addTag (@RequestBody @Valid @NotNull Tag tag) {
        tagService.addTag(tag);
    }

    @GetMapping(path = "{id}")
    public Optional<Tag> getTagById (@PathVariable("id") int id) {
        return tagService.selectTagById(id);
    }

    @GetMapping
    public List<Tag> getAllTags () {
        return tagService.selectAllTags();
    }

    @DeleteMapping(path = "{id}")
    public int deleteTagById (@PathVariable("id") int id) {
        tagService.removeTagById(id);
        return 1;
    }

    @PutMapping(path = "{id}")
    public int updateTagById (@PathVariable("id") int id, @RequestBody @Valid @NotNull Tag tag) {
        return tagService.updateTagById(id, tag);
    }
}
