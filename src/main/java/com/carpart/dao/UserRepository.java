package com.carpart.dao;

import com.carpart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    List<User> findByUserName(String userName);

    List<User> findByUserNameAndPassword(String userName,String password);
}
