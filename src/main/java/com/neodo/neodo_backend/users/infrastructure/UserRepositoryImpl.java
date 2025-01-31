package com.neodo.neodo_backend.users.infrastructure;

import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import com.neodo.neodo_backend.users.service.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository){
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public UserEntity save(UserEntity userEntity){
        return userJpaRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username){
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email){
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public Optional<UserEntity> findById(Long id){
        return userJpaRepository.findById(id);
    }
}
