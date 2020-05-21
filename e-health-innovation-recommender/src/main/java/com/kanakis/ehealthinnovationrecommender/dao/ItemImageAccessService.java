package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.ItemImage;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("postgres-image")
public class ItemImageAccessService implements ItemImageDao {

    private final JdbcTemplate jdbcTemplate;

    public ItemImageAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertImage(ItemImage image) throws NullPointerException{
        String sqlItemInsertion = "INSERT "
                + "INTO ehir_item_image "
                + "("
                + "name, "
                + "type, "
                + "bytes"
                + ") "
                + "VALUES (?,?,?) "
                + "RETURNING id";

        int id = jdbcTemplate.queryForObject(sqlItemInsertion,
                new Object[]{image.getName(),image.getType(),image.getPicByte()},
                (resultSet, i) -> {
                    int imageId = Integer.parseInt(resultSet.getString("id"));
                    return imageId;
                });
        return id;

    }

    @Override
    public Optional<ItemImage> selectImageById(int selectionId) {
        try {
            String sql = "SELECT "
                    + "id, "
                    + "name, "
                    + "type , "
                    + "bytes "
                    + "FROM ehir_item_image "
                    + "WHERE id = ?";
            ItemImage image = jdbcTemplate.queryForObject(sql, new Object[]{selectionId}, (resultSet, i) -> {
                int imageId = Integer.parseInt(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                byte[] bytes = resultSet.getBytes("bytes");
                return new ItemImage(imageId, name, type, bytes);
            });
            return Optional.ofNullable(image);

        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteImageById(int id) {

        String sql = "DELETE "
                + "ehir_item_image "
                + "WHERE id = ?";

        return jdbcTemplate.update(sql);
    }

    @Override
    public int updateImageById(int id, ItemImage image) {
        if (id == 0) {
            return insertImage(image);
        }

        String sql = "Update "
                + "ehir_item_image "
                + "SET "
                + "name = ?, "
                + "type = ?, "
                + "bytes = ? "
                + "WHERE id = ? "
                + "RETURNING id";

        jdbcTemplate.queryForObject(sql,
                new Object[]{image.getName(),image.getType(),image.getPicByte(), id},
                (resultSet, i) -> {
                    int imageId = Integer.parseInt(resultSet.getString("id"));
                    return imageId;
                });

        return id;
    }
}
