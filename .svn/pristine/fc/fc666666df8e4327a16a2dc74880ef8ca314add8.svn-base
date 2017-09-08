<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>列表信息</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
    <style>
    #aboutTitle,#aboutTest{display: inline-block;width: 30%;margin-left: 10px;}
    #aboutTest{width:95%;max-width:95%;height:150px;}
    .referBtn,.keepBtn,.cancelBtn{margin-left:41px;}
    </style>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">关于我们</span>
    </div>
    <!--end pageHeadline -->
    
	<div class="whiteBox" style="margin-top:30px;min-height:500px;">
	
	    <form id="formSearch" class="form-horizontal">
	    <input type="hidden" id="id" value="${about.id}" />
			<div class="form-group">
		    	<label for="name">标题</label>
		    	<input  type="text" class="form-control" id="aboutTitle"  value="${about.aboutTitle}" disabled="disabled" />
	 		</div>
	  		<div class="form-group">
	    		<label for="name" style="vertical-align: top;">正文</label>
	   			<textarea class="form-control" rows="3" id="aboutTest"  disabled="disabled">${about.aboutTest}</textarea>
	  		</div>
	  		 <input type="button" value="修改" class="referBtn" id="update" />
			 <input type="button" value="保存" class="keepBtn" id="commit" style="display: none"/>
			 <input type="button" value="取消" class="cancelBtn" id="cancel" style="display: none"/>
		</form>
		
	</div>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/bootstrap-table.min.1.10.1.js"/>'></script>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/locale/bootstrap-table-zh-CN.min.js"/>'></script>
	<script type="text/javascript">
		$(function() {
		$("#cancel").bind("click",function(){
			window.location.href='<spring:url value="/headquarters/about/doEnAboutList" />';
		});
		//修改
	   	$("#update").bind("click",function(){
			$('#aboutTitle').attr("disabled",false); 
			$('#aboutTest').attr("disabled",false); 
			$("#cancel").show();
			$("#commit").show();
			$("#update").hide();
			
		});
		$("#commit").bind("click",function(){
			if($('#aboutTitle').val()==null||$('#aboutTitle').val()==""){
				top.layer.alert("标题不能为空！",{icon:2});
				return;
			}
			
			if($('#aboutTest').val()==null||$('#aboutTest').val()==""){
				top.layer.alert("正文不能为空！",{icon:2});
				return;
			}
			var id=$("#id").val();
			if(id==null||id==""){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/about/doSaveAbout" />',
					data:{
						aboutTitle:$('#aboutTitle').val(),
						aboutTest:$('#aboutTest').val()
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							top.layer.alert("保存成功！",{icon:1});
							window.location.href='<spring:url value="/headquarters/about/doEnAboutList"/>';
						}
						else{
							top.layer.alert("保存失败！",{icon:2});
						}
					}
				 });
			}else{
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/about/doEditAbout" />',
					data:{
						id:$("#id").val(),
						aboutTitle:$('#aboutTitle').val(),
						aboutTest:$('#aboutTest').val()
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							top.layer.alert("修改成功！",{icon:1});
							window.location.href='<spring:url value="/headquarters/about/doEnAboutList"/>';
						}else{
							top.layer.alert("修改失败！",{icon:2});
						}
					}
				 });
		   	}
			
		});	
	});
	
        
	</script>

</body>
</html>
