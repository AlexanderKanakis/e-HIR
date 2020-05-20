package com.kanakis.ehealthinnovationrecommender.controller;

import com.kanakis.ehealthinnovationrecommender.model.Occupation;
import com.kanakis.ehealthinnovationrecommender.model.Tag;
import com.kanakis.ehealthinnovationrecommender.service.OccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/occupation")
@RestController
@CrossOrigin
public class OccupationController {

    private final OccupationService occupationService;

    @Autowired
    public OccupationController(OccupationService occupationService) {
        this.occupationService = occupationService;
    }

    @PostMapping
    public void addTag (@RequestBody @Valid @NotNull Occupation occupation) {
        occupationService.addOccupation(occupation);
    }

    @PostMapping(path = "{id}/tags")
    public int insertTagRelationShip( @PathVariable("id") int id,@RequestBody @Valid @NotNull Tag tag) {
        return occupationService.insertTagRelationShip(id, tag);
    }

    @GetMapping(path = "{id}")
    public Optional<Occupation> getTagById (@PathVariable("id") int id) {
        return occupationService.selectOccupationById(id);
    }

    @GetMapping
    public List<Occupation> getAllTags () {
        return occupationService.selectAllOccupations();
    }

    @DeleteMapping(path = "{id}")
    public int deleteTagById (@PathVariable("id") int id) {
        occupationService.removeOccupationById(id);
        return 1;
    }

    @PutMapping(path = "{id}")
    public int updateTagById (@PathVariable("id") int id, @RequestBody @Valid @NotNull Occupation occupation) {
        return occupationService.updateOccupationById(id, occupation);
    }

    @GetMapping(path = "{id}/tags")
    public List<Tag> getOccupationTags (@PathVariable("id") int id) {
        return occupationService.getOccupationTags(id);
    }
}
