<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>权限-部门管理</title>
   	<%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/js/plugin/zTree/zTreeStyle/zTreeStyle.css" />' rel="stylesheet"/>
</head>
<body>
<div id="mainContent">
	<input id="depId"  type="hidden"/>
    <input id="parentId"  type="hidden"/>
    <button type="button"  class="btn btn-default" onclick="doAddDepartment(1);">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增下级部门
	</button>
    <button type="button" value="" onclick="doAddDepartment(2);" class="btn btn-default">
  		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增同级部门
    </button>
    <button type="button" onclick="doDelDepartment();" class="btn btn-default">
    	<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除部门
    </button>
    <div>
        <ul id="perDepartmentTree" class="ztree"></ul>
    </div>
</div>
   <script type="text/javascript" src='<spring:url value="/js/plugin/zTree/jquery.ztree.core.min.3.5.2.js" />'></script>
   <script type="text/javascript" src='<spring:url value="/js/plugin/zTree/jquery.ztree.excheck.min.3.5.2.js" />'></script>
   
<script type="text/javascript">
$(function() {
	
	var zTreeObj=null;
	var seletedNode=null;
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
				$("#depId").val(treeNode.id);
				seletedNode=treeNode;
				$("#parentId").val(treeNode.pId==null?0:treeNode.pId);
			}
		}
	};
	
	var zNodes =[
		<c:forEach var="item" items="${departmentList}" varStatus="st">     
		{ 
			id:"${item.id}", 
			pId:"${item.parentId}", 
			name:"${item.departmentName}", 
			click:'clickEvent(\'<spring:url value="/department/doEnEditDepartment?id=${item.id}" />\')',
			open:true
		},
		</c:forEach>
	];
	
	zTreeObj=$.fn.zTree.init($("#perDepartmentTree"), setting, zNodes); 
	
	var code;
	
	/**
	 * 新增加一个节点
	 */
	doAddDepartment = function (addType){
		var parentId;
		if(${fn:length(departmentList)}==0){
			parentId = "0";
		}
		else 
		{
			if(addType==1){
				parentId = $("#depId").val();
			}
			else if(addType==2){
				parentId = $("#parentId").val();
			}
		}
		if(parentId==null || parentId==''){
			top.layer.alert("请选择一个节点",{icon:7});
		}
		else{
			window.location.href = '<spring:url value="/department/doEnEditDepartment?parentId=" />'+parentId;
		}
		
	}
	
	doDelDepartment=function (){
		var depId = $("#depId").val();
		if(depId==''){
			top.layer.alert("请选择一个节点",{icon:7});
			return;
		}else{
			top.layer.confirm('确定要删除节点吗?', {icon: 3, title:'注意'}, function(index){
				top.layer.close(index);
				$.post('<spring:url value="/department/doDelDepartment" />',{
					perDepartmentIds : getNodeAndChildNodeIds(seletedNode)
	            },function(dep,status){
	            	if("0000000"==dep.head.respCode){
	            		top.layer.msg("删除成功!",{icon:1});
	            		//刷新树
	            		zTreeObj.removeNode(seletedNode,true);
	            	}else{
	            		top.layer.alert("删除失败！",{icon:2});
	            	}
	            },"json");
			});
		}
	}
	
	clickEvent=function (urlStr){
		window.location.href = urlStr;
	}
	
	/*获取当前节点及其子节点的id集合*/
	function getNodeAndChildNodeIds(treeNode) {
	    var childNodes = zTreeObj.transformToArray(treeNode);
	    var nodes = new Array();
	    for(i = 0; i < childNodes.length; i++) {
	         nodes[i] = childNodes[i].id;
	    }
	    return nodes.join(",");
	}
	
});
</script>
</body>