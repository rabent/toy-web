package toy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.domain.User;
import toy.domain.UserDTO;
import toy.domain.UserRegisterDTO;
import toy.repository.UserRepository;

//domain은 web을 참고해선 안된다

@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;

    @Transactional
    public String register(UserRegisterDTO RegisterDTO) {
        User user=new User(RegisterDTO.getUser_id(), RegisterDTO.getPassword(),RegisterDTO.getName());
        User checkUser = userRepository.findById(RegisterDTO.getUser_id());
        if(checkUser!=null) return null;
        userRepository.save(user);
        return user.getUser_id();
    }

    @Transactional
    public UserDTO login(String user_id, String password) {
        User user=userRepository.findById(user_id);
        if(user==null) return null;
        else if(!user.getPassword().equals(password)) return null;
        else {
            return new UserDTO(user.getUser_id(),user.getPassword());
        }
    }

    public void user_expire(String user_id) {
        userRepository.delete(user_id);
    }
}
