<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrapValidator/bootstrapValidator.min.js" />'></script>
	<script type="text/javascript" src='<spring:url value="/js/plugin/bootstrapValidator/zh_CN.js" />'></script>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
</head>

<body style="background:#fff;padding-top:30px;">
    <div class="whiteBox">
		<form id="submitForm" class="form-horizontal">
			<input type="hidden" id="id" value="${businessInformation.id}" />
			<div class="form-group">
				<label class="control-label col-sm-3">锁定消费者消费抽成</label>
				<div class="col-sm-6">
				    <input type="text" class="form-control" id="localConsumption" name="localConsumption" value="${businessInformation.localConsumption}" />
				    <span class="unitBusinessEdit">%</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3">引流消费抽成</label>
				<div class="col-sm-6">
				    <input type="text" class="form-control" id="drainageConsumption" name="drainageConsumption" value="${businessInformation.drainageConsumption}"/>
				    <span class="unitBusinessEdit">%</span>
				</div>
			</div>
			<div class="form-group">
		        <div class="col-sm-12">
				  <div class="navbar-btn">
				  	  <input type="button" value="保存" class="confirm" id="commit" />
				 	  <input type="button" value="取消" class="cancel" id="cancel" />
				  </div>
		        </div>
	        </div>
		</form> 
	</div>
	<script type="text/javascript">
	$(function() {
		$("#cancel").bind("click",function(){
			closeWindow(window.name);
		});
		$("#commit").bind("click",function(){
			var bootstrapValidator = $("#submitForm").data('bootstrapValidator');
		    bootstrapValidator.validate();
			if(bootstrapValidator.isValid()){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/headquarters/businessInformation/doEditBusinessInformationCustomAs" />',
					data:{
						id:$("#id").val(),
						localConsumption:$('#localConsumption').val(),
						drainageConsumption:$('#drainageConsumption').val()
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							parent.window.contFrame.window.beCalled(data);
		 					closeWindow(window.name);
						}else{
							alert(data.head.respContent);
						}
					}
				});
			}
		});	
   		//输入验证
		$('#submitForm').bootstrapValidator({
	       message: 'This value is not valid',
	       feedbackIcons: {
	           valid: 'glyphicon glyphicon-ok',
	           invalid: 'glyphicon glyphicon-remove',
	           validating: 'glyphicon glyphicon-refresh'
	       },
	       fields: {
	       		localConsumption : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   },regexp: {
	                       regexp: /^[0-9]*$/,
	                       message: '格式不正确!'
	                   }
	               }
	           },
	       		drainageConsumption : {
	               validators: {
	                   notEmpty: {
	                       message: '不能为空'
	                   },regexp: {
	                       regexp: /^[0-9]*$/,
	                       message: '格式不正确!'
	                   }
	               }
	           }
	        }
	 	});
	})
	</script>
</body>
</html>
