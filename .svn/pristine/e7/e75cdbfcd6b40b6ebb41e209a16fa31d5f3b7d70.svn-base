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
		.userNewBox{background:#f5f5f5;padding:10px 8px;position:relative;border-radius: 5px;
		  margin-top: 20px;line-height:22px;margin-top:13px;margin-bottom:10px;}
        .userNewBox em{position: absolute;width: 0;height: 0;border-width: 0 22px 18px 11px;
        border-style: solid;border-color: transparent transparent #f5f5f5;left: 20px;top: -9px;}
        .merchantNewBox{border:1px solid #f5f5f5;background:#fafafa;padding:10px;border-radius:5px;}
        hr{width:120%;margin-left: -10%;margin-top:25px;}
	</style>
</head>

<body class="mainContant" style="background:#fff;width:97%;overflow-y: auto;">
    <div class="whiteBox">
	<form id="submitForm" class="form-horizontal">
	
	    <div class="form-group" style="margin-top: 15px" >
	    	<c:forEach items="${leavingMessagList }" var = "var">
	    	    <p><span>用户：</span><span style="float:right;">${var.leavingmessageDate }</span></p>
	    	    <div class="userNewBox"><em></em>${var.leavingmessageContent }</div>
				<c:if test="${var.replyContent != null && var.replyContent != ''}">
				    <div class="merchantNewBox"><span>客服：</span>${var.replyContent }</div>
				</c:if>
				<hr/>
	    	</c:forEach>
			
		</div>
		
		<div class="form-group">
	        <div class="col-sm-12">
			  <div class="navbar-btn">
			 	  <input type="button" value="取消" class="cancelBtn" id="cancel" />
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
		
	});
		
	
</script>
</html>
