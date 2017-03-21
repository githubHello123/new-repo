<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="staticHtmlfrom" dialog="true" layout="table" action="girlController.do?save">
    <input name="id" type="hidden" value="${girl.id }">
    <table cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <label class="Validform_label">年龄:</label>
            </td>
            <td class="value">
                <input name="age" class="inputxt" value="${girl.age }" style="width:300px;" datatype="*">
                <span class="Validform_checktip">年龄不能为空</span>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">罩杯:</label>
            </td>
            <td class="value">
                <input name="cupSize" class="inputxt" value="${girl.cupSize }" style="width:300px;" datatype="*">
                <span class="Validform_checktip">罩杯不能为空</span>
            </td>
        </tr>
    </table>
</t:formvalid>
</body>