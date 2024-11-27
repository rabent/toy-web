package toy.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy.domain.User;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryV1Test {

    @Autowired
    UserRepository userRepository;

    User user;
    User updateUser;

    @BeforeEach
    void beforeEach() {
        user=new User("id232123", "1q2e3e4w","rabent0207");
        updateUser=new User("idupdated","2q2e4w2q","rabent00");
    }

    @AfterEach
    void after() {
        userRepository.delete(user.getUser_id());
    }

    @Test
    void save() {
        String save = userRepository.save(user);
        assertThat(save).isEqualTo(user.getUser_id());
    }

    @Test
    void findById() {
        userRepository.save(user);
        User user1 = userRepository.findById(user.getUser_id());
        assertThat(user1).isEqualTo(user);
    }

    @Test
    void update() {
        userRepository.save(user);
        User update = userRepository.update(user.getUser_id(), updateUser);
        User user1 = userRepository.findById(updateUser.getUser_id());
        assertThat(user1.getUser_id()).isEqualTo(update.getUser_id());
        assertThat(user1.getName()).isEqualTo(update.getName());
        assertThat(user1.getPassword()).isEqualTo(update.getPassword());
        assertThat(user1.getItems_str()).isEqualTo(update.getItems_str());
    }
}