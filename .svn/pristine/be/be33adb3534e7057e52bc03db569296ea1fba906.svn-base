<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>修改密码</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <style>
    .col-sm-2{width:90px;}
    .col-sm-10{width:40%;}
    </style>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">修改密码</span>
    </div>
    <!--end pageHeadline -->

    <div class="whiteBox" style="min-height:500px;padding-top:30px;">
		<form id="submitForm" class="form-horizontal">
			<input type="hidden" id="sysUserId" value="${sysUser.id}"/>
			<div class="form-group">
				<label class="control-label col-sm-2" for="oldPass">原密码</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="oldPass">
				</div>
		    </div>
		    <div class="form-group">
				<label class="control-label col-sm-2" for="newPass">新密码</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="newPass">
				</div>
		    </div>
		    <div class="form-group">
				<label class="control-label col-sm-2" for="confirmPass">确认密码</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="confirmPass">
				</div>
		    </div>
		    <div class="form-group">
			    <div id="toolbar" class="container" style="padding-left: 57px;margin-top:20px;">
					<button id="commit" type="button" class="confirm">确认</button>
					<button id="cancel" type="button" class="cancelBtn col-sm-offset-1" onclick=" window.history.go( -1 );">取消</button>
				</div>
		    </div>
		</form> 
	</div>				

</body>

<script>

	$(function() {
		
	});
	
	$("#commit").bind("click",function(){
		$.ajax({
			type: "post",
			url: '<spring:url value="/sysuser/account/doEditSysUserPass" />',
			data:{
				id:$("#sysUserId").val(),
				oldLoginPass:$("#oldPass").val(),
				loginPass:$("#newPass").val(),
				repeatLoginPass:$("#confirmPass").val()
			},
			dataType: "json",
			async: false,
			success: function(data){
				if("0000000"==data.head.respCode){
					alert(data.body);
					location='<spring:url value="/sysuser/account/doSysUserLogout" />';
				}else{
					alert(data.head.respContent);
				}
			}
		});
	});	

</script>
</html>
