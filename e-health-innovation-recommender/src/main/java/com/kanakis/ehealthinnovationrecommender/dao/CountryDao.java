package com.kanakis.ehealthinnovationrecommender.dao;
import com.kanakis.ehealthinnovationrecommender.model.Country;
import com.kanakis.ehealthinnovationrecommender.model.Tag;

import java.util.List;
import java.util.Optional;

public interface CountryDao {

    int insertCountry(Country country);

    List<Country> selectAllCountries();

    Optional<Country> selectCountryByID(int id);

    int deleteCountryById(int id);

    int insertTagRelationShip(int id, Tag tag);

    List<Tag> selectCountryTags(int id);
}
