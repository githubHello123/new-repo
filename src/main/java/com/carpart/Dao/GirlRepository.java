package com.carpart.dao;

import com.carpart.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/20.
 */
@Repository
public interface GirlRepository extends JpaRepository<Girl,Long>{
}
