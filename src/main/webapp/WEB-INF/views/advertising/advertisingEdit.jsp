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
    <style type="text/css">
		h6{color:#236adc;}
		textarea{resize:none;}
		#word{color:#f74f4c;}
		.col-sm-2{width:90px;}
	</style>
</head>

<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">发布广告</span>
    </div>
    <!--end pageHeadline -->
    
    <div class="whiteBox">
		<form id="submitForm" class="form-horizontal">
		
			<input type="hidden" id="advertisingId" value="${advertising.id}" />
			
		    <div class="form-group" style="margin-top: 15px" >
				<label class="control-label col-sm-2">发布标题</label>
				<div class="col-sm-4">
				    <input type="text" class="form-control" id="headline" name = "headline" value="${advertising.headline}" maxlength="14"/>
				</div>
				<label class="control-label col-sm-2">发布时间</label>
				<div class="col-sm-4">
				    <input type="text" class="form-control" id="createDate" value="${advertising.createDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">广告内容</label>
				<div class="col-sm-10">
				    <textarea class="form-control" style="width:100%;height:200px;" id="context" name ="context" placeholder="请输入广告内容">${advertising.context }</textarea>
				    <h6>说点什么吧，你可以输入<span>70</span>个字，现在剩余<span id="word">70</span>个</h6>
				</div>
			</div>
			
		</form> 
	</div>
	<div class="form-group">
        <div class="col-sm-12">
          <div class="navbar-btn">
              <input type="button" value="提交审核" class=greenBtnRad id="commit" />
              <input type="button" value="取消" class="redBtnRad col-sm-offset-1" id="cancel" />
          </div>
        </div>
    </div>
</body>

<script type="text/javascript">

	$(function() {
	
		 var contextlen = $("#context").val().length;
         if(contextlen > 69){
	     	$(this).val($(this).val().substring(0,70));
		 }
		 if(contextlen < 71){
		   var num = 70 - contextlen;
		   $("#word").text(num);
	     }
	
		$('#submitForm').bootstrapValidator({
	       message: 'This value is not valid',
	       feedbackIcons: {
	           valid: 'glyphicon glyphicon-ok',
	           invalid: 'glyphicon glyphicon-remove',
	           validating: 'glyphicon glyphicon-refresh'
	       },
	       fields: {
	       		headline : {
	               validators: {
	                   notEmpty: {
	                       message: '广告标题不能为空'
	                   },
	               }
	           },
	            context : {
	               validators: {
	                   notEmpty: {
	                       message: '广告内容不能为空'
	                   }
	                 }
	               }
	        }
	        
	 });
	 
	 $("#context").keyup(function(){
		   var len = $(this).val().length;
		   if(len > 69){
		    	$(this).val($(this).val().substring(0,70));
		   }
		   if(len < 71){
			   var num = 70 - len;
			   $("#word").text(num);
		   }
		   
	  });
	 
	 $("#cancel").bind("click",function(){
		window.location.href='<spring:url value="/business/advertising/doEnAdvertisingList" />';
	 });
	 
	 $("#commit").bind("click",function(){
	 		
			var bootstrapValidator = $("#submitForm").data('bootstrapValidator');
		    bootstrapValidator.validate();
			if(bootstrapValidator.isValid()){
				if($('#createDate').val()==null||$('#createDate').val()==""){
					top.layer.alert("发布时间不能为空！",{icon:2});
					return;
				}
				$.ajax({
					type: "POST",
					url : '<spring:url value="/business/advertising/doEditAdvertising" />',
					data:{
						id:$("#advertisingId").val(),
						headline:$('#headline').val(),
						createDate:$('#createDate').val(),
						context:$('#context').val()
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("0000000"==data.head.respCode){
							window.location.href='<spring:url value="/business/advertising/doEnAdvertisingList" />';
						}
						else{
							alert(data.head.respContent);
						}
					}
				});
		}
	});	
				
	});
	
	
	
	
</script>
</html>
