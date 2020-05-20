package com.kanakis.ehealthinnovationrecommender.service;

import com.kanakis.ehealthinnovationrecommender.dao.OccupationDao;
import com.kanakis.ehealthinnovationrecommender.model.Occupation;
import com.kanakis.ehealthinnovationrecommender.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OccupationService {

    private final OccupationDao occupationDao;

    @Autowired
    public OccupationService(@Qualifier("postgres-occupation") OccupationDao occupationDao) {
        this.occupationDao = occupationDao;
    }

    public int addOccupation(Occupation occupation) {
        if (!this.occupationNameAlreadyExists(occupation)) {
            return occupationDao.insertOccupation(occupation);
        }
        else {
            return -1;
        }
    }

    public int insertTagRelationShip(int id, Tag tag) {
        return occupationDao.insertTagRelationShip(id, tag);
    }

    public int removeOccupationById(int id) {
        return occupationDao.deleteOccupationById(id);
    }

    public  int updateOccupationById(int id, Occupation occupation) {
        return occupationDao.updateOccupationByID(id, occupation);
    }


    public List<Occupation> selectAllOccupations() {
        return occupationDao.selectAllOccupations();
    }

    public Optional<Occupation> selectOccupationById (int id) {
        return occupationDao.selectOccupationByID(id);
    }

    public Optional<Occupation> selectOccupationByName (String name) {
        return occupationDao.selectOccupationByName(name);
    }

    public boolean occupationNameAlreadyExists(Occupation occupation) {
        if (this.selectOccupationByName(occupation.getName()) != null) {
            return true;
        }
        return false;
    }

    public List<Tag> getOccupationTags (int id) {
        return occupationDao.getOccupationTags(id);
    }
}
