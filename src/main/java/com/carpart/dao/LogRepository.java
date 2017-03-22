package com.carpart.dao;

import com.carpart.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/21.
 */
@Repository
public interface LogRepository extends JpaRepository<Log,Long>{

}
