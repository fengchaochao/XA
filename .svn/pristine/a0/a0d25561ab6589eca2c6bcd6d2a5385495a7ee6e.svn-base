<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>权限-资源管理</title>
	<%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
<body>
<form id="submitForm" class="form-horizontal">
	<input type="hidden" id="resourceId" value="${perResource.id}" />
	<input type="hidden" id="parentId" value="${perResource.parentId}" />
    
    <div class="form-group">
           <label class="col-sm-2 control-label">资源名称：</label>
           <div class="col-sm-10">
               <input type="text" class="form-control" id="resName" value="${perResource.resName}"/>
           </div>
	</div>
	
	<div class="form-group">
        <label class="col-sm-2 control-label">是否菜单项：</label>
        <div class="col-sm-10">
            <select id="menuFlag" class="form-control">
               <option value="0" <c:if test='${perResource.menuFlag=="0"}'>selected</c:if> >否</option>
               <option value="1" <c:if test='${perResource.menuFlag==null || perResource.menuFlag=="1"}'>selected</c:if>>是</option>
            </select>
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-sm-2 control-label">资源地址：</label>
        <div class="col-sm-10">
            <input type="text" id="resUrl" class="form-control" value="${perResource.resUrl}" />
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-sm-2 control-label">资源介绍：</label>
        <div class="col-sm-10">
            <input type="text" id="resIntroduce" class="form-control" value="${perResource.resIntroduce}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">排序：</label>
        <div class="col-sm-10">
            <input type="text" id="resOrderNum" class="form-control" value="${perResource.resOrderNum}" />
        </div>
    </div>
    
    <div class="navbar-btn">
        <input type="button" class="btn-success col-sm-offset-5" value="提交" id="submit"/>
    </div>
 
 </form>
	<script>
	$("#submit").bind("click",function(){
		$.ajax({
			type: "POST",
			url: '<spring:url value="/resource/doEditResource" />',
			data:{
				id:$("#resourceId").val(),
				parentId:$("#parentId").val(),
				resName:$("#resName").val(),
				menuFlag:$("#menuFlag").val(),
				resUrl:$("#resUrl").val(),
				resIntroduce:$("#resIntroduce").val(),
				resOrderNum:$("#resOrderNum").val()
			},
			dataType: "json",
			async: false,
			success: function(data){
				if("0000000"==data.head.respCode){
					window.location.href = '<spring:url value="/resource/doEnResourceList" />';
				}
			}
		});
		
	});	
	</script>
</body>

</html>