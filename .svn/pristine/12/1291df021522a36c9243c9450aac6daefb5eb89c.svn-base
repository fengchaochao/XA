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
		.userNewBox{background:#f5f5f5;padding:10px 8px;position:relative;border-radius: 5px;margin-top: 20px;line-height:22px;}
		.userNewBox em{position: absolute;width: 0;height: 0;border-width: 0 22px 18px 11px;
        border-style: solid;border-color: transparent transparent #f5f5f5;left: 20px;top: -9px;}
        #replyContent{border-radius:5px;height:100px;}
	</style>
</head>

<body class="mainContant" style="background:#fff;width:97%;">
    <div class="whiteBox">
	<form id="submitForm" class="form-horizontal">
		<input type="hidden" id="leavingMessageId" value="${leavingMessage.id}" />
		<p style="margin-top: 15px;margin-bottom: -5px;">
		   <span>用户：</span><span style="float:right;">${leavingMessage.leavingmessageDate }</span>
		</p>
		<div class="userNewBox"><em></em>${leavingMessage.leavingmessageContent }</div>
	    
		<p style="margin-top:25px;">我的回复：</p>
	    <textarea type="text" class="form-control" id="replyContent" placeholder="请输入回复内容"></textarea>
	    <h6>说点什么吧，你可以输入<span>1000</span>个字，现在剩余<span id="word">1000</span>个</h6>
		
		<div class="form-group">
	        <div class="col-sm-12">
			  <div class="navbar-btn">
			  	  <input type="button" value="确定" class="referBtn" id="commit" />
			 	  <input type="button" value="取消" class="cancelBtn col-sm-offset-1" id="cancel" />
			  </div>
	        </div>
        </div>
	  
	</form> 
	</div>
</body>

<script type="text/javascript">
	
	$(function() {

		$("#cancel").bind("click",function(){
			closeWindow(window.name);
		});
		
		$("#replyContent").keyup(function(){
		   var len = $(this).val().length;
		   if(len > 999){
		    	$(this).val($(this).val().substring(0,1000));
		   }
		   if(len < 1001){
			   var num = 1000 - len;
			   $("#word").text(num);
		   }
		   
	  });		
	});
		
	$("#commit").bind("click",function(){
		var replyContent = $("#replyContent").val();
		if(replyContent == null || replyContent == ""){
			top.layer.alert("回复内容不能为空！",{icon:2});
			return false;
		}
		$.ajax({
			type: "POST",
			url : '<spring:url value="/headquarters/leavingMessage/doEditLeavingMessage" />',
			data:{
				id:$("#leavingMessageId").val(),
				replyContent:replyContent,
				status:"2"
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
