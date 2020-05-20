package com.kanakis.ehealthinnovationrecommender.service;

import com.kanakis.ehealthinnovationrecommender.dao.CountryDao;
import com.kanakis.ehealthinnovationrecommender.model.Country;
import com.kanakis.ehealthinnovationrecommender.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryDao countryDao;

    @Autowired
    public CountryService(@Qualifier("postgres-country") CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public int addCountry(Country country) {
        return countryDao.insertCountry(country);
    }

    public int insertTagRelationShip(int countryId, Tag tag) {
        return countryDao.insertTagRelationShip(countryId, tag);
    }

    public int removeCountryById(int id) {
        return countryDao.deleteCountryById(id);
    }


    public List<Country> selectAllCountries() {
        return countryDao.selectAllCountries();
    }

    public Optional<Country> selectCountryById (int id) {
        return countryDao.selectCountryByID(id);
    }

    public List<Tag> getCountryTags (int id) {
        return countryDao.selectCountryTags(id);
    }
}
