package com.kanakis.ehealthinnovationrecommender.controller;

import com.kanakis.ehealthinnovationrecommender.model.Country;
import com.kanakis.ehealthinnovationrecommender.model.Tag;
import com.kanakis.ehealthinnovationrecommender.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/country")
@RestController
@CrossOrigin
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public void addCountry (@RequestBody @Valid @NotNull Country country) {
        countryService.addCountry(country);
    }

    @PostMapping(path = "{id}/tags")
    public int insertTagRelationShip( @PathVariable("id") int countryId,@RequestBody @Valid @NotNull Tag tag) {
        return countryService.insertTagRelationShip(countryId, tag);
    }

    @GetMapping(path = "{id}")
    public Optional<Country> getCountryById (@PathVariable("id") int id) {
        return countryService.selectCountryById(id);
    }

    @GetMapping
    public List<Country> getAllCountries () {
        return countryService.selectAllCountries();
    }

    @DeleteMapping(path = "{id}")
    public int deleteTagById (@PathVariable("id") int id) {
        countryService.removeCountryById(id);
        return 1;
    }

    @GetMapping(path = "{id}/tags")
    public List<Tag> getCountryTags (@PathVariable("id") int id) {
        return countryService.getCountryTags(id);
    }
}
