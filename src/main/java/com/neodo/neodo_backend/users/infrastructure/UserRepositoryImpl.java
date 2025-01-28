package com.neodo.neodo_backend.users.infrastructure;

import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import com.neodo.neodo_backend.users.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }
}
