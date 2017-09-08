<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet">
</head>

<body style="width: 96%;">

	<form id="submitForm" class="form-horizontal">
	
		<input type="hidden" id="payRecordId" value="${payRecord.id}" />
		
	    <div class="form-group" style="margin-top: 15px" >
			<label class="control-label col-sm-2">名称</label>
			<div class="col-sm-10">
			    <input type="text" class="form-control" id="name" value="${payRecord.name}" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">状态</label>
			<div class="col-sm-10">
			    <input type="text" class="form-control" id="state" value="${payRecord.state}" />
			</div>
		</div>
		
		<div class="form-group">
	        <div class="col-sm-12">
			  <div class="navbar-btn">
			  	  <input type="button" value="确定" class="btn btn-primary col-sm-offset-3" id="commit" />
			 	  <input type="button" value="取消" class="btn btn-primary col-sm-offset-1" id="cancel" />
			  </div>
	        </div>
        </div>
	  
	</form> 
</body>

<script type="text/javascript">
	
	$(function() {

		$("#cancel").bind("click",function(){
			closeWindow(window.name);
		});
				
	});
		
	$("#commit").bind("click",function(){
		
		$.ajax({
			type: "POST",
			url : '<spring:url value="/payRecord/doEditPayRecord" />',
			data:{
				id:$("#payRecordId").val(),
				name:$('#name').val(),
				state:$('#state').val()
			},
			dataType: "json",
			async: false,
			success: function(data){
				if("0000000"==data.head.respCode){
					parent.window.contFrame.window.beCalled(data);
 					closeWindow(window.name);
				}
				else{
					alert(data.head.respContent);
				}
			}
		});
	});	
	
</script>
</html>
