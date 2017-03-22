package com.carpart.entity;

import org.springframework.data.repository.query.parser.Part;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2017/3/22 0022.
 */
@Entity
@Table(name = "menu")
public class Menu implements java.io.Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String functionName;//菜单名称
    private Short functionLevel;//菜单等级
    private String functionUrl;//菜单地址
    private String functionOrder;//菜单排序
    private Long parentFunctionId;//菜单排序

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parentFunctionId",insertable = false,updatable = false)
    private Menu menu;//父菜单

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "menu")
    private List<Menu> menus = new ArrayList<Menu>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Short getFunctionLevel() {
        return functionLevel;
    }

    public void setFunctionLevel(Short functionLevel) {
        this.functionLevel = functionLevel;
    }

    public String getFunctionUrl() {
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    public String getFunctionOrder() {
        return functionOrder;
    }

    public void setFunctionOrder(String functionOrder) {
        this.functionOrder = functionOrder;
    }

    public Long getParentFunctionId() {
        return parentFunctionId;
    }

    public void setParentFunctionId(Long parentFunctionId) {
        this.parentFunctionId = parentFunctionId;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
