<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商品评价</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/common/swiper.min.css" />' rel="stylesheet"/>
    <link href='<spring:url value="/css/common/sharePage.css" />' rel="stylesheet"/>
</head>
<body style="background:#fff;">
    <header><a href="javascript:history.back(-1);"></a><span>商品评价</span></header>
    
    <!--start commodityEvaluation-->
    <section class="commodityEvaluation pageEvaluate">
        <c:forEach items="${goodsEvaluateList}" var="var" varStatus="vat">
		        <div class="evaluationNumber">
		            <p class="evaluationUser">
		             <c:if test="${var.consumersPhoto!=null&&var.consumersPhoto!=''}">
		            	<img src="<spring:url value='${var.consumersPhoto}' />" onerror="javascript:this.src='<spring:url value='/img/evaluateUser.png'/>'"/>
		            </c:if>
		            <c:if test="${var.consumersPhoto==null||var.consumersPhoto==''}">
		            	<img src="<spring:url value='/img/evaluateUser.png' />" />
		            </c:if>
		            <span>
		            <c:if test="${var.consumersName!=null&&var.consumersName!=''}">
		            	${var.consumersName}
		            </c:if>
		            <c:if test="${var.consumersName==null||var.consumersName==''}">
		            	匿名
		            </c:if>
		            </span>
		            	<span class="timeBox">${var.evaluateDate}</span>
		            </p>
		            <p class="evaluateContent">${var.evaluateContent}</p>
		            <c:if test="${var.replyContent!=null&&var.replyContent!=''}">
		            	<p class="responseEvaluation"><em></em>${var.replyContent}</p>
		            </c:if>
		        </div>
        </c:forEach>
    </section>
    <!--end commodityEvaluation-->
    <script type="text/javascript" src='<spring:url value="/js/_base/swiper.jquery.min.js" />'></script>
	<script>
	</script>
</body>
</html>