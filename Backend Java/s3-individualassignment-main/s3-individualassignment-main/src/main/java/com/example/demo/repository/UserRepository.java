package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    List<UserEntity> findAll();
//    Optional<UserEntity> findById(long id);
//    UserEntity save(UserEntity userEntity);
   UserEntity findByUsername(String username);
//    boolean existsById(Long id);

}
