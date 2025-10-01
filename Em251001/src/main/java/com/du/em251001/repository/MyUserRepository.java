package com.du.em251001.repository;

import com.du.em251001.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
}
