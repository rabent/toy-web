package toy.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import toy.domain.Item;

@Slf4j
@Repository
public class ItemRepositoryV1 implements ItemRepository{
    final JdbcTemplate jdbcTemplate;

    public ItemRepositoryV1(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Long save(Item item) {
        String sql="insert into item(item_id, filename, point, upload_date, tags_str) values (?,?,?,?,?)";
        jdbcTemplate.update(sql, item.getItem_id(), item.getFileName(), item.getPoint(), item.getDate()
        , item.getTags_str());
        return item.getItem_id();
    }

    @Override
    public Item findById(Long id) {
        String sql="select * from item where item_id=?";
        return jdbcTemplate.queryForObject(sql, ItemRowMapper(), id);
    }

    @Override
    public Item update(Long id, Item updateParam) {
        String sql="update item set filename=?, point=?, upload_date=?, tags_str=? where item_id=?";
        jdbcTemplate.update(sql, updateParam.getFileName(), updateParam.getPoint(), updateParam.getDate()
                , updateParam.getTags_str(), updateParam.getItem_id());
        updateParam.setItem_id(id);
        return updateParam;
    }

    @Override
    public void delete(Long id) {
        String sql="delete from item where item_id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Item> findAll() {
        String sql="select * from item";
        return jdbcTemplate.query(sql,ItemRowMapper());
    }
    //list를 통해 대규모 데이터를 받아오는 것은 문제가 있음
    //한 페이지씩 읽어오는 등의 새로운 방법 필요

    @Override
    public List<Item> findPage(int curPage) {
        String sql="select * from item order by upload_date DESC LIMIT " + String.valueOf(8*(curPage-1)) + ",8";
        return jdbcTemplate.query(sql,ItemRowMapper());
    }

    RowMapper<Item> ItemRowMapper() {
        return ((rs, rowNum) -> {
            Item item=new Item();
            item.setItem_id(rs.getLong("item_id"));
            item.setFileName(rs.getString("filename"));
            item.setDate(rs.getDate("upload_date").toLocalDate());
            item.setTags_str(rs.getString("tags_str"));
            item.setPoint(rs.getInt("point"));
            return item;
        });
    }
}
