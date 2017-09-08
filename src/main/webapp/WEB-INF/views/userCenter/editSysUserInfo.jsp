<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>修改用户信息</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
    <link href='<spring:url value="/js/plugin/zTree/zTreeStyle/zTreeStyle.css" />' rel="stylesheet"/>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">修改资料</span>
    </div>
    <!--end pageHeadline -->

	<form id="submitForm" class="form-horizontal">
		<div class="whiteBox">		
		    <input type="hidden" id="sysUserId" value="${sessionSysUser.id}"/>
		
			 <div class="form-group">
				<label class="control-label col-sm-3" for="loginName">登录名</label>
			    <div class="col-sm-6">
				    <input type="text" class="form-control" id="loginName" value="${sysUser.loginName}"/>
			    </div>
			</div>
			<div class="form-group">
			    <label class="control-label col-sm-3" for="realName">姓名</label>
			    <div class="col-sm-6">
				    <input type="text" class="form-control" id="realName" value="${sysUser.realName}"/>
			     </div>
			</div>
			<div class="form-group">
			    <label class="control-label col-sm-3" for="mobile">手机</label>
			    <div class="col-sm-6">
				    <input type="text" class="form-control" id="mobile"  value="${sysUser.mobile}"/>
			    </div>
			</div>
			<div class="form-group">
			    <label class="control-label col-sm-3" for="telephone">电话</label>
			    <div class="col-sm-6">
				     <input type="text" class="form-control" id="telephone"  value="${sysUser.telephone}"/>
			    </div>
			</div>
			<div class="form-group">
			    <label class="control-label col-sm-3" for="email">邮箱</label>
			    <div class="col-sm-6">
				     <input type="text" class="form-control" id="email"  value="${sysUser.email}"/>
			    </div>
			    <input id="roleIds" type="hidden" value="${sysUser.hxRoleIds}"/>
			 </div>
		    <div class="form-group">
			     <div id="toolbar" class="container" style="text-align: center;padding-top:40px;">
				    <button id="commit" type="button" class="confirm" style="margin-right:80px;">更新</button>
					<button id="cancel" type="button" class="cancelBtn" onclick=" window.history.go( -1 );">取消</button>
					
				</div>
			</div>
		</div>
	</form>

</body>
<script>
	$(function() {
		$("#cancel").bind("click",function(){
			if(confirm("确定要取消编辑操作吗？")){
				window.history.go(-1);
			}
		});
	});
	
	$("#commit").bind("click",function(){
		$.ajax({
			type: "get",
			url: '<spring:url value="/sysuser/doEditSysUser" />',
			data:{
				id:$("#sysUserId").val(),
				loginName:$("#loginName").val(),
				realName:$("#realName").val(),
				mobile:$("#mobile").val(),
				telephone:$("#telephone").val(),
				email:$("#email").val(),
				hxRoleIds:$("#roleIds").val().substring(1)
			},
			dataType: "json",
			async: false,
			success: function(data){
				if("0000000"==data.head.respCode){
					alert("修改成功");
				}else{
					alert("修改失败");
				}
			}
		});
	});	

</script>
</html>
