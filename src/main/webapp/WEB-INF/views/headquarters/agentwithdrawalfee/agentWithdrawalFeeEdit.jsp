<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
    <style>
    .col-sm-2{padding:0px;width:125px;}
    .whiteBox{min-height:500px;}
    </style>
</head>

<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">代理商提现手续费</span>
    </div>
    <!--end pageHeadline -->
    
    <div class="whiteBox">
		<form id="submitForm" class="form-horizontal">
			<input type="hidden" id="id" value="${agentWithdrawalFee.id}" />
		    <div class="form-group" style="margin-top: 15px" >
				<label class="control-label col-sm-2">代理商提现手续费</label>
				<div class="col-sm-4">
				    <input type="text" class="form-control" id="poundage" value="${agentWithdrawalFee.poundage}" disabled="disabled"/>
				    <span class="unitBusinessEdit">%</span>
				</div>
			</div>
			<div class="form-group" style="padding-left:125px;padding-top:20px;">
			  	  <input type="button" value="修改" class="confirm" id="update" />
			  	  <input type="button" value="保存" class="referBtn" id="commit" style="display: none"/>
			 	  <input type="button" value="取消" class="cancelBtn" id="cancel" style="display: none"/>
	        </div>
		</form>
	</div>
	<script type="text/javascript">
	$(function() {
		$("#cancel").bind("click",function(){
			window.location.href='<spring:url value="/headquarters/agentWithdrawalFee/doEnAgentWithdrawalFeeList" />';
		});
		//修改
	   	$("#update").bind("click",function(){
			$('#poundage').attr("disabled",false); 
			$("#cancel").show();
			$("#commit").show();
			$("#update").hide();
			
		});
		$("#commit").bind("click",function(){
			var reg= /^[0-9]*$/;
			if($('#poundage').val()==null||$('#poundage').val()==""){
				top.layer.alert("手续费不能为空！",{icon:2});
				return;
			}
			if(!reg.test($('#poundage').val())){
				top.layer.alert("格式输入不正确！",{icon:2});
				return;
			}
			var id=$("#id").val();
			if(id==null||id==""){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/agentWithdrawalFee/doSaveAgentWithdrawalFee" />',
					data:{
						poundage:$('#poundage').val()
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							top.layer.alert("保存成功！",{icon:1});
							window.location.href='<spring:url value="/headquarters/agentWithdrawalFee/doEnAgentWithdrawalFeeList"/>';
						}
						else{
							top.layer.alert("保存失败！",{icon:2});
						}
					}
				 });
			}else{
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/agentWithdrawalFee/doEditAgentWithdrawalFee" />',
					data:{
						id:$('#id').val(),
						poundage:$('#poundage').val()
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							top.layer.alert("修改成功！",{icon:1});
							window.location.href='<spring:url value="/headquarters/agentWithdrawalFee/doEnAgentWithdrawalFeeList"/>';
						}
						else{
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
