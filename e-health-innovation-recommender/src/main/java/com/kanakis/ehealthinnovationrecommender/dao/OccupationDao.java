package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.Occupation;
import com.kanakis.ehealthinnovationrecommender.model.Tag;

import java.util.List;
import java.util.Optional;

public interface OccupationDao {

    int insertOccupation(Occupation occupation);

    List<Occupation> selectAllOccupations();

    Optional<Occupation> selectOccupationByID(int id);

    Optional<Occupation> selectOccupationByName(String name);

    int deleteOccupationById(int id);

    int updateOccupationByID(int id, Occupation occupation);

    int insertTagRelationShip(int id, Tag tag);

    List<Tag> getOccupationTags(int id);
}
