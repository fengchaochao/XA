<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>权限-资源管理</title>
	<%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/js/plugin/zTree/zTreeStyle/zTreeStyle.css" />' rel="stylesheet"/>
</head>
<body>
<div id="mainContent">
	<input id="resId"  type="hidden"/>
    <input id="parentId"  type="hidden"/>
	<button type="button"  class="btn btn-default" onclick="doAddResource(1);">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增下级资源
	</button>
    <button type="button" value="" onclick="doAddResource(2);" class="btn btn-default">
  		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增同级资源
    </button>
    <button type="button" onclick="doDelResource();" class="btn btn-default">
    	<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除资源
    </button>

    <div>
        <ul id="perResourceTree" class="ztree"></ul>
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
				$("#resId").val(treeNode.id);
				seletedNode=treeNode;
				$("#parentId").val(treeNode.pId==null?0:treeNode.pId);
			}
		}
	};
	
	var zNodes =[
		<c:forEach var="item" items="${resourceList}" varStatus="st"> 
		{ 
			id:"${item.id}", 
			pId:"${item.parentId}", 
			name:"${item.resName}<c:if test='${item.menuFlag==0}'>[菜单]</c:if>", 
			click:'clickEvent(\'<spring:url value="/resource/doEnEditResource?id=${item.id}"/>\')',
			open:true
		},
		</c:forEach>
	];
	
	zTreeObj=$.fn.zTree.init($("#perResourceTree"), setting, zNodes); 
	
	var code;
	
	/**
	 * 新增加一个节点
	 */
	doAddResource = function (addType){
		var parentId;
		if(${fn:length(resourceList)}==0){
			parentId = "0";
		}
		else 
		{
			if(addType==1){
				parentId = $("#resId").val();
			}
			else if(addType==2){
				parentId = $("#parentId").val();
			}
		}
		if(parentId==null || parentId==''){
			top.layer.alert("请选择一个节点",{icon:7});
		}
		else{
			window.location.href = '<spring:url value="/resource/doEnEditResource?parentId=" />'+parentId;
		}
	}
	
	doDelResource=function (){
		var resId = $("#resId").val();
		if(resId==''){
			top.layer.alert("请选择一个节点",{icon:7});
			return;
		}else{
			top.layer.confirm('确定要删除节点吗?', {icon: 3, title:'注意'}, function(index){
				top.layer.close(index);
				$.post('<spring:url value="/resource/doDelResource" />',{
					perResourceIds : getNodeAndChildNodeIds(seletedNode)
	            },function(res,status){
	            	if("0000000"==res.head.respCode){
	            		top.layer.msg("删除成功!",{icon:1});
	            		//刷新树
	            		zTreeObj.removeNode(seletedNode,true);
	            	}else{
	            		top.layer.alert("删除记录失败！",{icon:2});
	            	}
	            },"json");
			});
		}
	}
	
	clickEvent=function (urlStr){
		window.location.href = urlStr;
	}
	
	/*获取当前节点所有子节点的id集合*/
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