package toy.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import toy.domain.Item;
import toy.domain.User;

import javax.sql.DataSource;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryV1 implements UserRepository{
    final JdbcTemplate jdbcTemplate;

    public UserRepositoryV1(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String save(User user) {
        String sql="insert into user_table(user_id, password, name, point) values (?,?,?,?)";
        jdbcTemplate.update(sql, user.getUser_id(), user.getPassword(), user.getName(),
                user.getPoint());
        return user.getUser_id();
    }

    @Override
    public User update(String id, User updateParam) {
        String sql="update user_table set name=?, point=?, item_list=?, password=? where user_id=?";
        jdbcTemplate.update(sql, updateParam.getName(), updateParam.getPoint(), updateParam.getItems_str()
                ,updateParam.getPassword(), id);
        updateParam.setUser_id(id);
        return updateParam;
    }

    @Override
    public User findById(String id) {
        String sql="select * from user_table where user_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, UserRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    RowMapper<User> UserRowMapper() {
        return ((rs, rowNum) -> {
            User user=new User();
            user.setUser_id(rs.getString("user_id"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setItems_str(rs.getString("item_list"));
            user.setPoint(rs.getInt("point"));
            return user;
        });
    }
}
