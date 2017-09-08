<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑用户</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/js/plugin/zTree/zTreeStyle/zTreeStyle.css" />' rel="stylesheet"/>
</head>
<body style="background: #fff;padding-top:20px;">

<form id="submitForm" class="form-horizontal">

    <input type="hidden" id="sysUserId" value="${sysUser.id}" />
	<div class="form-group">
		<label class="col-sm-2 control-label" for="loginName">登录名</label>
		<div class="col-sm-10">
			<input type="text" readonly="readonly" class="form-control"  id="loginName" value="${sysUser.loginName}"/>
		</div>
	</div>
	<div class="form-group">
	<label class="control-label col-sm-2" for="realName">姓名</label>
	<div class="col-sm-10">
		<input type="text" class="form-control" id="realName" value="${sysUser.realName}"/>
	</div>
	</div>
	
	<div class="form-group">
	<label class="control-label col-sm-2" for="mobile">手机</label>
	<div class="col-sm-10">
		<input type="text" class="form-control" id="mobile"  value="${sysUser.mobile}"/>
	</div>
	</div>
	
	<div class="form-group">
	<label class="control-label col-sm-2" for="telephone">电话号码</label>
	<div class="col-sm-10">
		<input type="text" class="form-control" id="telephone"  value="${sysUser.telephone}"/>
	</div>
	</div>
	
	<div class="form-group">
	<label class="control-label col-sm-2" for="email">电子邮件</label>
	<div class="col-sm-10">
		<input type="text" class="form-control" id="email"  value="${sysUser.email}"/>
	</div>
	</div>
	<c:if test="${sysUsers.userState!='1'}">
		<div class="form-group">
		    <label class="control-label col-sm-2" for="role">角色</label>
		    <div class="col-sm-10">
		        <div class="col-sm-12">
		            <c:forEach var="role"  items="${sysUser.hxRoleList}">
		                <input type="checkbox" name="hxRoleIds"  <c:if test="${role.userOwner=='true'}">checked</c:if> value="${role.id}" />
		                ${role.roleName}
		            </c:forEach>
		        </div>
		    </div>
	    </div>
     </c:if>   
    <c:if test="${sysUser.id!=null && sysUser.id!=''}">    
    <div class="form-group">
        <label class="col-sm-2 control-label" for="modiPassCheck">修改密码</label>
        <div class="col-sm-10"> 
            <input id="modiPassCheck" type="checkbox" value="1" /> 
        </div>
    </div>
    
   	<fieldset id="modiPassView">
	   	<div class="form-group">
			<label class="col-sm-2 control-label">输入新密码</label>
			<div class="col-sm-10">
			    <input type="password" class="form-control" id="userPass1" /> 
			</div>
		</div>
		<div class="form-group">
                <label class="control-label col-sm-2">再次输入密码</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="userPass2" /> 
                </div>
            </div>
	    </fieldset>
	
	    </c:if>
	    
	    <div class="form-group">
		    <div class="navbar-btn col-sm-12">
		    	<input type="button" value="更新" class="yellowReturnRad col-sm-offset-3" id="commit"/>
		   		<input type="button" value="取消" class="redBtnRad col-sm-offset-1" id="cancel"/>
		    </div>
	    </div>
	
	</form> 

 
</body>

<script type="text/javascript" src='<spring:url value="/js/plugin/zTree/jquery.ztree.core.min.3.5.2.js"/>'></script>
<script type="text/javascript" src='<spring:url value="/js/plugin/zTree/jquery.ztree.excheck.min.3.5.2.js"/>'></script>

<script>
	var treeObj;
	$(function() {
		$("#cancel").bind("click",function(){
			closeWindow(window.name);
		});
		
		if($("#sysUserId").val()!=null && $("#sysUserId").val()!=''){
			$("#modiPassCheck").bind("click",function(){
				if($("#modiPassCheck").is(':checked')){
					$("#modiPassView").show();
				}
				else{
					$("#modiPassView").hide();
				}
			});
			
			// 修改密码默认不显示
			$("#modiPassView").hide();
		}
	});
	var setting = {
			check: {
				enable: true,
				chkStyle: "radio",
				radioType: "all"
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck: function (e, treeId, treeNode) {
					$("#perDepartmentId").val(treeNode.id);
					$("#perDepartmentName").val(treeNode.name);
				}
			}
	};
	var zNodes =[
        <c:forEach var="item" items="${perDepartmentList}" varStatus="st">     
		{ 
			id : "${item.id}", 
			pId : "${item.parentId}", 
			name: "${item.departmentName}", 
			open:true
		},
		</c:forEach>
	];
	treeObj = $.fn.zTree.init($("#userDepartmentTree"),setting, zNodes);
	var node = treeObj.getNodeByParam("id",$("#perDepartmentId").val(), null);
	if(node!=null){
		node.checked=true;
	}
	treeObj.refresh();
	
	$("#commit").bind("click",function(){
		
		var newPass = "";
		if($("#modiPassCheck").is(':checked')){
			if($("#userPass1").val()==''){
				top.layer.alert("新密码不能为空");
				return;
			}
			if($("#userPass1").val()!=$("#userPass2").val()){
				top.layer.alert("两次密码不一致");
				return;
			}
			newPass = $("#userPass1").val();
		}
		
		$.ajax({
			type: "get",
			url: '<spring:url value="/sysuser/doEditSysUser" />',
			data:{
				id:$("#sysUserId").val(),
				loginName:$("#loginName").val(),
				loginPass:newPass,
				realName:$("#realName").val(),
				mobile:$("#mobile").val(),
				telephone:$("#telephone").val(),
				email:$("#email").val(),
				hxRoleIds: function (){
					var chk_value =[]; 
					$('input[name="hxRoleIds"]:checked').each(function(){ 
					    chk_value.push($(this).val()); 
					}); 
					return chk_value;
				},
			},
			dataType: "json",
			async: false,
			success: function(data){
				if("0000000"==data.head.respCode){
					//关闭
					parent.window.contFrame.window.beCalled(data);
 					closeWindow(window.name);
				}
				else{
					alert("编辑失败");
				}
			}
		});
	});	

</script>
</html>
