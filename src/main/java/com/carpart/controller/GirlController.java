package com.carpart.controller;

import com.carpart.Entity.Girl;
import com.carpart.model.AjaxJson;
import com.carpart.service.GirlService;
import com.gx.tag.vo.DataGrid;
import com.gx.tag.vo.TagUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
@Controller
@RequestMapping(value = "/girlController.do")
public class GirlController {

    private final static Logger log = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlService girlService;

    @RequestMapping(params = "list",method = RequestMethod.GET)
    public String list(){
        return "girl/girlList";
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid){
        List<Girl> list = girlService.grilsAll();
        dataGrid.setResults(list);
        dataGrid.setRows(list.size());
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "delete",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(Girl girl,HttpServletRequest request){
        AjaxJson j = new AjaxJson();
        girl = girlService.findById(girl.getId());
        try{
            girlService.delete(girl);
            j.setMsg("删除成功");
            j.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            j.setMsg("删除失败");
            j.setSuccess(false);
        }
        return j;
    }
}
