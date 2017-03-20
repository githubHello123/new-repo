package com.carpart.controller;

import com.carpart.Entity.Girl;
import com.carpart.service.GirlService;
import com.gx.tag.vo.DataGrid;
import com.gx.tag.vo.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
@Controller
@RequestMapping(value = "/girl")
public class GirlController {

    @Autowired
    private GirlService girlService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "girl/girlList";
    }

    @RequestMapping(value = "/datagrid")
    public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid){
        List<Girl> list = girlService.grilsAll();
        dataGrid.setResults(list);
        dataGrid.setRows(list.size());
        TagUtil.datagrid(response, dataGrid);
    }
}
