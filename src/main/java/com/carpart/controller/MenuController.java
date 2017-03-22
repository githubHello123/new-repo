package com.carpart.controller;

import com.carpart.constant.Globals;
import com.carpart.entity.Menu;
import com.carpart.model.AjaxJson;
import com.carpart.model.ComboTree;
import com.carpart.model.TreeGrid;
import com.carpart.service.LogService;
import com.carpart.service.MenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2017/3/22 0022.
 */
@Controller
@RequestMapping(value = "menuController.do")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private LogService logService;

    @RequestMapping(params = "menu")
    public ModelAndView function() {
        return new ModelAndView("menu/menuList");
    }

    @RequestMapping(params = "menuGrid")
    @ResponseBody
    public List<TreeGrid> functionGrid(HttpServletRequest request, TreeGrid treegrid) {
        List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();

        String treeId = treegrid.getId();
        Long id = null;
        if(StringUtils.isNotBlank(treeId)){
            id = Long.valueOf(treeId);
        }
        List<Menu> list = menuService.findByParentId(id);
        for(Menu menu : list){
            TreeGrid tg = new TreeGrid();
            tg.setId(String.valueOf(menu.getId()));
            tg.setText(menu.getFunctionName());
            tg.setSrc(menu.getFunctionUrl());
            tg.setOrder(menu.getFunctionOrder());
            tg.setCode("/plug-in/accordion/images/pictures.png");
            List<Menu> childList = menuService.findByParentId(menu.getId());
            if(childList != null && childList.size() > 0){
                tg.setState("closed");
                tg.setCode("/plug-in/accordion/images/folder.png");
            }

            treeGrids.add(tg);
        }
        return treeGrids;
    }

    @RequestMapping(params = "del",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(Menu menu){
        AjaxJson j = new AjaxJson();
        j.setSuccess(false);

        menu =  menuService.findOne(menu.getId());
        if(menu == null){
            j.setMsg("菜单不存在");
            return  j;
        }
        List<Menu> childList = menuService.findByParentId(menu.getId());
        if(childList != null && childList.size() > 0){
            j.setMsg("请先删除子菜单");
            return j;
        }
        menuService.delete(menu);
        j.setSuccess(true);
        j.setMsg("删除成功");
        return j;
    }

    @RequestMapping(params = "addorupdate")
    public ModelAndView addorupdate(Menu menu,HttpServletRequest request){
        Long id = menu.getId();
        if(id != null){
            menu =  menuService.findOne(menu.getId());
            request.setAttribute("function",menu);
            if(menu.getMenu() != null && menu.getMenu().getId() != null){
                menu.setFunctionLevel((short)1);

                Menu parent = menuService.findOne(menu.getMenu().getId());
                menu.setMenu(parent);
                request.setAttribute("function",menu);
            }
        }
        return new ModelAndView("menu/menu");
    }

    @RequestMapping(params = "setPFunction")
    @ResponseBody
    public List<ComboTree> setPFunction(HttpServletRequest request, ComboTree comboTree) {
        List<ComboTree> comboTrees = new ArrayList<ComboTree>();
        String selfId = request.getParameter("selfId");
        List<Menu> list = menuService.findParenMenus(selfId,comboTree.getId());
        for(Menu menu : list){
            ComboTree tree = new ComboTree();
            tree.setId(String.valueOf(menu.getId()));
            tree.setText(menu.getFunctionName());

            List<Menu> childList = menu.getMenus();
            if(childList != null && childList.size() > 0){
                tree.setState("closed");
                tree.setChecked(false);
            }

            comboTrees.add(tree);
        }
        return comboTrees;
    }


    @RequestMapping(params = "saveFunction")
    @ResponseBody
    public AjaxJson saveFunction(Menu menu, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String message = null;
        String functionOrder = menu.getFunctionOrder();
        if (StringUtils.isEmpty(functionOrder)) {
            menu.setFunctionOrder("0");
        }
        if (menu.getMenu().getId() == null) {
            menu.setMenu(null);
        } else {
            Long parentId = menu.getMenu().getId();
            Menu parent = menuService.findOne(parentId);
            menu.setFunctionLevel(Short.valueOf(parent.getFunctionLevel() + 1 + ""));
            menu.setParentFunctionId(parentId);
        }
        menuService.save(menu);
        if (menu.getId() != null) {
            message = "权限: " + menu.getFunctionName() + "被更新成功";
            logService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else {
            message = "权限: " + menu.getFunctionName() + "被添加成功";
            logService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        return j;
    }
}
