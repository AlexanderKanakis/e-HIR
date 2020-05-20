package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.Tag;


import java.util.List;
import java.util.Optional;

public interface TagDao {

    int insertTag(Tag tag);

    List<Tag> selectAllTags();

    Optional<Tag> selectTagByID(int id);

    Optional<Tag> selectTagByName(String name);

    int deleteTagById(int id);

    int updateTagByID(int id, Tag tag);
}
