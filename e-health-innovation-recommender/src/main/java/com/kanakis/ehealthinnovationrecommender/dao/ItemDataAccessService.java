package com.kanakis.ehealthinnovationrecommender.dao;

import com.kanakis.ehealthinnovationrecommender.model.Item;
import com.kanakis.ehealthinnovationrecommender.model.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgres-item")
public class ItemDataAccessService implements ItemDao{

    private final JdbcTemplate jdbcTemplate;

    public ItemDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertTagRelationShip(int itemId, Tag tag) {
            String sqlItemTagRelationInjection = "INSERT INTO ehir_item_tag (tag_id, item_id) VALUES (?,?)";
            jdbcTemplate.update(sqlItemTagRelationInjection, tag.getId(), itemId);
        return 1;
    }

    @Override
    public int insertItem(Item item) {


        String sqlItemInsertion = "INSERT "
                + "INTO ehir_item "
                + "("
                + "name, "
                + "description, "
                + "links, "
                + "image_id,"
                + "ratingSum,"
                + "ratingUsers"
                + ") "
                + "VALUES (?,?,?,?,0,0) "
                + "RETURNING id";

        return jdbcTemplate.queryForObject(sqlItemInsertion,
                new Object[]{item.getName(), item.getDescription(), item.getLinks(), item.getImageId()},
                (resultSet, i) -> {
                    int itemId = Integer.parseInt(resultSet.getString("id"));
                    return itemId;
                });
    }

    @Override
    public List<Item> selectAllItems() {
        String sql = "SELECT "
                + "* "
                + "FROM ehir_item "
                + "ORDER BY id ASC";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            int id = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String links = resultSet.getString("links");
            int image_id = Integer.parseInt(resultSet.getString("image_id"));
            int ratingSum = Integer.parseInt(resultSet.getString("ratingSum"));
            int ratingUsers = Integer.parseInt(resultSet.getString("ratingUsers"));
            return new Item(id, name, description, links, image_id, ratingSum, ratingUsers);
        });
    }

    @Override
    public Optional<Item> selectItemById(int id) {
        String sql = "SELECT "
                + "* "
                + "FROM ehir_item "
                + "WHERE id = ?";
        Item item = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            int itemId = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String links = resultSet.getString("links");
            int image_id = resultSet.getInt("image_id");
            int ratingSum = Integer.parseInt(resultSet.getString("ratingSum"));
            int ratingUsers = Integer.parseInt(resultSet.getString("ratingUsers"));
            return new Item(id, name, description, links, image_id, ratingSum, ratingUsers);
        });
        return Optional.ofNullable(item);
    }

    @Override
    public int deleteItemById(int id) {
        String sql = "DELETE "
        + "FROM ehir_item "
        + "WHERE id = ?";

        jdbcTemplate.update(sql, id);
        return 1;
    }

    @Override
    public int deleteTagRelations(int id) {
        String sql = "DELETE "
                + "FROM ehir_item_tag "
                + "WHERE item_id = ?";

        jdbcTemplate.update(sql, id);
        return 1;
    }

    @Override
    public int updateItemById(int id, Item item) {
        String sql = "UPDATE "
                + "ehir_item "
                +"SET "
                + "name = ?, "
                + "description = ?, "
                + "links = ?, "
                + "image_id = ?, "
                + "ratingSum = ?, "
                + "ratingUsers = ? "
                + "WHERE id = ?";

        jdbcTemplate.update(sql, item.getName(), item.getDescription(), item.getLinks(), item.getImageId(),
                item.getRatingSum(), item.getRatingUsers(), id);
        return 1;
    }

    public List<Tag> getItemTags(int itemId) {
        String sql = "SELECT"
                + " id,"
                + " name"
                + " FROM ehir_tag"
                + " INNER JOIN ehir_item_tag"
                + " ON ehir_item_tag.tag_id = ehir_tag.id"
                + " WHERE ehir_item_tag.item_id = (?)"
                + " ORDER BY name ASC";
        return jdbcTemplate.query(sql, new Object[] { itemId }, (resultSet, i) -> {
            int id = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Tag(id, name);
        });
    }

    public double updateRatingById(int id, Item item) {
        String sql = "UPDATE " +
                "ehir_item " +
                "SET ratingSum = ratingSum + ?, " +
                "ratingUsers = ratingUsers + 1 " +
                "WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[] {item.getRatingSum(), id}, (resultSet, i) -> {
            int sum = Integer.parseInt(resultSet.getString("ratingSum"));
            int users = Integer.parseInt(resultSet.getString("ratingUsers"));

            double avg = sum/users;
            return avg;
        });

    }

    public double selectRatingById(int id) {
        String sql = "SELECT" +
                " ratingSum, " +
                "ratingUsers " +
                "FROM ehir_item " +
                "WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[] {}, (resultSet, i) -> {
            int sum = Integer.parseInt(resultSet.getString("ratingSum"));
            int users = Integer.parseInt(resultSet.getString("ratingUsers"));

            double avg = sum/users;
            return avg;
        });
    }
}
