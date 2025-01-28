package com.neodo.neodo_backend.users.infrastructure;

import com.neodo.neodo_backend.users.dto.request.UserCreateRequest;
import com.neodo.neodo_backend.users.dto.response.UserResponse;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import com.neodo.neodo_backend.users.service.UserRepository;
import com.neodo.neodo_backend.users.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserResponse signupUser(UserCreateRequest request){

        //유저 중복 확인
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new IllegalArgumentException("중복된 사용자입니다.");
        }
        //이메일 중복 확인
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }

        if (!isValidEmail(request.getEmail())) {
            throw new IllegalArgumentException("유효하지 않은 이메일 형식입니다."); // Invalid email format
        }

        //비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .password(encodedPassword)
                .email(request.getEmail())
                .build();

        UserEntity saveUser = userRepository.save(userEntity);

        return new UserResponse(saveUser.getId(), saveUser.getUsername(), saveUser.getEmail());
    }

    @Transactional
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    @Transactional
    public Optional<UserEntity> findOne(Long userId){
        return userRepository.findById(userId);
    }
}
