package com.du.accountproj251001.repository;

import com.du.accountproj251001.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyuserRepository extends JpaRepository<MyUser,Long> {
    //MyUser findById(Long id) ;

     MyUser findByLoginId(String loginId);


}
