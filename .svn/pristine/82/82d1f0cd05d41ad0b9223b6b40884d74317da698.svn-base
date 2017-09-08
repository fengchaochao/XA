<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>角色编辑</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/js/plugin/zTree/zTreeStyle/zTreeStyle.css" />' rel="stylesheet">
</head>
<body style="background:#fff;padding-top:30px;">

	<form id="submitForm" class="form-horizontal">
	<input type="hidden" id="roleId" value="${perRole.id}" />
	    <div class="form-group">
			<label class="control-label col-sm-2" for="roleName">角色名称：</label>
			<div class="col-sm-10"> 
				<input type="text" class="form-control" id="roleName" value="${perRole.roleName}" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="roleType">角色类型：</label>
			<div class="col-sm-10"> 
				<select id="roleType" class="select form-control">
					<option value="0"
						<c:if test='${perRole.roleType=="0"}'>selected</c:if>>系统默认</option>
					<option value="1"
						<c:if test='${perRole.roleType=="1"}'>selected</c:if>>类型1</option>
					<option value="2"
						<c:if test='${perRole.roleType=="2"}'>selected</c:if>>类型2</option>
				</select>
			</div>
		</div>
		<div class="form-group">
		    <label class="control-label col-sm-2" for="roleIntroduce">角色介绍：</label> 
		    <div class="col-sm-10"> 
			    <input type="text" class="form-control" id="roleIntroduce" value="${perRole.roleIntroduce}" />
		    </div>
		</div>
		<div class="form-group">
		    <label class="control-label col-sm-2" for="roleResourceTree">角色资源：</label>
		    <div class="col-sm-10"> 
			    <ul id="roleResourceTree" class="ztree"></ul>
		    </div>
	    </div>
		<div class="navbar-btn">
			<button type="button" class="cancelBtn" id="cancel" >关闭</button>
			<button type="button" class="referBtn col-sm-offset-1" id="submit">更新</button>
		</div>
	</form>

    <script type="text/javascript" src='<spring:url value="/js/plugin/zTree/jquery.ztree.core.min.3.5.2.js" />'></script>
    <script type="text/javascript" src='<spring:url value="/js/plugin/zTree/jquery.ztree.excheck.min.3.5.2.js" />'></script>
    <script type="text/javascript" src='<spring:url value="/js/common/common.js" />'></script>
	<script type="text/javascript">
	var treeObj;
	$(function() {
		$("#cancel").bind("click",function(){
			closeWindow(window.name);
		});
		var setting = {
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y" : "ps", "N" : "ps" },
				radioType: "all"
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		
		var zNodes =[
	        <c:forEach var="item" items="${perRole.perResourceList}" varStatus="st">     
			{ 
				id : "${item.id}", 
				pId : "${item.parentId}", 
				name: "${item.resName}<c:if test='${item.menuFlag==0}'>[菜单]</c:if>", 
				checked: ${item.roleOwner},
				open:true
			},
			</c:forEach>
		];
		
		treeObj = $.fn.zTree.init($("#roleResourceTree"),setting, zNodes);
		
		// 获取选中ID
		function doGetAllChecked(){
			var checkedResourceIds;
			var sNodes =  treeObj.getCheckedNodes(true);
			var innerHtml = "";
			for(i=0;i<sNodes.length;i++){
				if(checkedResourceIds=='' || checkedResourceIds==null){
					checkedResourceIds = sNodes[i].id;
				}
				else{
					checkedResourceIds = checkedResourceIds + "," +sNodes[i].id;
				}
			}
			return checkedResourceIds;
		}

		$("#submit").bind("click",function(){
			$.ajax({
				type: "POST",
				url: '<spring:url value="/role/doEditRole" />',
				data:{
					id:$("#roleId").val(),
					roleName:$("#roleName").val(),
					roleType:$("#roleType").val(),
					roleIntroduce:$("#roleIntroduce").val(),
					perResourceIds: doGetAllChecked()
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
		
	});
	</script>
</body>
</html>