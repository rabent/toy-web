package toy.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy.domain.User;
import toy.domain.UserDTO;
import toy.domain.UserRegisterDTO;
import toy.repository.UserRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    UserRegisterDTO registerDTO;

    @BeforeEach
    void beforeEach() {
        registerDTO=new UserRegisterDTO("id123123","1q2w3e4r","rabent");
    }

    @AfterEach
    void after() {
        userService.user_expire("id123123");
    }

    @Test
    void register() {
        userService.register(registerDTO);
        User user=userRepository.findById(registerDTO.getUser_id());
        assertThat(user.getUser_id()).isEqualTo(registerDTO.getUser_id());
        assertThat(user.getPassword()).isEqualTo(registerDTO.getPassword());
    }

    @Test
    void login() {
        UserDTO login = userService.login(registerDTO.getUser_id(), registerDTO.getPassword());
        assertThat(login).isNull();
        userService.register(registerDTO);
        login=userService.login(registerDTO.getUser_id(), "wrondpw");
        assertThat(login).isNull();
        login=userService.login(registerDTO.getUser_id(), registerDTO.getPassword());
        assertThat(login.getUser_id()).isEqualTo(registerDTO.getUser_id());
        assertThat(login.getPassword()).isEqualTo(registerDTO.getPassword());
    }
}