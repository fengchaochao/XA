<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>权限-部门管理</title>
	<%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
</head>
<body>
<form id="submitForm" class="form-horizontal">
	<input type="hidden" id="departmentId" value="${perDepartment.id}" />
	<input type="hidden" id="parentId" value="${perDepartment.parentId}" />
	
     <div class="form-group">
         <label class="col-sm-2 control-label">部门名称：</label>
         <div class="col-sm-10">
             <input type="text" class="form-control" id="departmentName" value="${perDepartment.departmentName}"/>
         </div>
     </div>
     
     <div class="form-group">
         <label class="col-sm-2 control-label">排序：</label>
         <div class="col-sm-10">
             <input type="text" id="depOrderNum" class="form-control" value="${perDepartment.depOrderNum}" />
         </div>
     </div> 
     
     <div class="navbar-btn">
        <input type="button" class="btn-success btn-sm col-sm-offset-5" value="提交" id="submit"/>
     </div>
 </form>
	<script>
	$("#submit").bind("click",function(){
		$.ajax({
			type: "POST",
			url: '<spring:url value="/department/doEditDepartment" />',
			data:{
				id:$("#departmentId").val(),
				parentId:$("#parentId").val(),
				departmentName:$("#departmentName").val(),
				depOrderNum:$("#depOrderNum").val()
			},
			dataType: "json",
			async: false,
			success: function(data){
				if("0000000"==data.head.respCode){
					window.location.href = '<spring:url value="/department/doEnDepartmentList" />';
				}
			}
		});
		
	});	
	</script>
</body>

</html>