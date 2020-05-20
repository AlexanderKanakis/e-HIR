package com.kanakis.ehealthinnovationrecommender.service;

import com.kanakis.ehealthinnovationrecommender.dao.TagDao;
import com.kanakis.ehealthinnovationrecommender.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagDao tagDao;

    @Autowired
    public TagService(@Qualifier("postgres-tag") TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public int addTag(Tag tag) {
        if (!this.tagNameAlreadyExists(tag)) {
            return tagDao.insertTag(tag);
        }
        else {
            return -1;
        }
    }

    public int removeTagById(int id) {
        return tagDao.deleteTagById(id);
    }

    public  int updateTagById(int id, Tag tag) {
        return tagDao.updateTagByID(id, tag);
    }

    public List<Tag> selectAllTags() {
        return tagDao.selectAllTags();
    }

    public Optional<Tag> selectTagById (int id) {
        return tagDao.selectTagByID(id);
    }

    public Optional<Tag> selectTagByName (String name) {
        return tagDao.selectTagByName(name);
    }

    public boolean tagNameAlreadyExists(Tag tag) {
        if (this.selectTagByName(tag.getName()) != null) {
            return true;
        }
        return false;
    }
}
