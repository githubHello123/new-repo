package com.carpart.dao;

import com.carpart.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu,Long>,JpaSpecificationExecutor {

    List<Menu> findByParentFunctionId(Long id);
}
