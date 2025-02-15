package com.neodo.neodo_backend.users.infrastructure;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.DuplicatedResources;
import com.neodo.neodo_backend.users.dto.request.UserCreateRequest;
import com.neodo.neodo_backend.users.dto.response.UserResponse;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import com.neodo.neodo_backend.users.service.UserRepository;
import com.neodo.neodo_backend.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
    public UserResponse signup(@Valid @RequestBody UserCreateRequest request){

        //유저 중복 확인
        if(userRepository.existsByUsername(request.getUsername())){
            throw new DuplicatedResources(ErrorResponseEnum.DUPLICATED_USERNAME);
        }
        //이메일 중복 확인
        if(userRepository.existsByEmail(request.getEmail())){
            throw new DuplicatedResources(ErrorResponseEnum.DUPLICATED_EMAIL);
        }

        //비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .password(encodedPassword)
                .email(request.getEmail())
                .build();

        UserEntity saveUser = userRepository.save(userEntity);

        return UserResponse.from(saveUser);
    }

    @Override
    public UserResponse get(UserEntity user) {
        return UserResponse.from(user);
    }
}
