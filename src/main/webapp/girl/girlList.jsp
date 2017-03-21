<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="system_function_functionList" class="easyui-layout" fit="true">
    <div region="center" style="padding:1px;">
        <t:datagrid actionUrl="girlController.do?datagrid"  name="menuList" queryMode="group" fitColumns="true" idField="id">
            <t:dgCol title="编号" field="id" width="100"></t:dgCol>
            <t:dgCol title="罩杯" field="cupSize"  width="100"></t:dgCol>
            <t:dgCol title="年龄" field="age"  width="100"></t:dgCol>

            <t:dgCol title="操作" field="opt"></t:dgCol>
            <t:dgDelOpt title="删除" url="girlController.do?del&id={id}" />
            <t:dgToolBar title="录入菜单" icon="icon-add" url="girlController.do?addOrUpate" funname="add"></t:dgToolBar>
            <t:dgToolBar title="菜单编辑" icon="icon-edit" url="girlController.do?addOrUpate" funname="update"></t:dgToolBar>
        </t:datagrid>
    </div>
</div>