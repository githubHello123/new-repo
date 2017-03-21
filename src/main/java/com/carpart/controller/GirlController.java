package com.carpart.controller;

import com.carpart.entity.Girl;
import com.carpart.model.AjaxJson;
import com.carpart.service.GirlService;
import com.gx.tag.vo.DataGrid;
import com.gx.tag.vo.TagUtil;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping(value = "girlController.do")
public class GirlController {

    private final static Logger log = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlService girlService;

    @RequestMapping(params = "list",method = RequestMethod.GET)
    public String list(){
        return "girl/girlList";
    }

    @RequestMapping(params = "datagrid")
    @ResponseBody
    public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid){
        List<Girl> list = girlService.grilsAll();
        dataGrid.setResults(list);
        dataGrid.setRows(list.size());
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "del", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(Girl girl){
        AjaxJson j = new AjaxJson();
        j.setSuccess(true);

        girl = girlService.findOne(girl.getId());

        String msg = "删除成功";
        try{
            girlService.delete(girl);
        }catch (Exception e){
            log.error(e.getMessage());
            msg = "删除失败";
            j.setSuccess(false);
        }
        j.setMsg(msg);
        return j;
    }

    @RequestMapping(params = "save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(Girl girl){
        AjaxJson j = new AjaxJson();
        j.setSuccess(true);

        String msg = "删除成功";
        try{
            girl = girlService.add(girl);
            j.setObj(girl);
        }catch (Exception e){
            log.error(e.getMessage());
            msg = "删除失败";
            j.setSuccess(false);
        }
        j.setMsg(msg);
        return j;
    }

    @RequestMapping(params = "addOrUpate")
    public String addOrUpdate(HttpServletRequest request){
        String id = request.getParameter("id");
        if(StringUtils.isNotBlank(id)){
            Girl girl = girlService.findOne(Long.valueOf(id));
            if(girl != null){
                request.setAttribute("girl",girl);
            }
        }
        return "girl/girl";
    }
}
