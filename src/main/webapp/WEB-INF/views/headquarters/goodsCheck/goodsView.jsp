<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑</title>
     <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <script type="text/javascript" src='<spring:url value="/js/plugin/ckeditor/ckeditor.js" />'></script>
    <script type="text/javascript" src='<spring:url value="/js/plugin/ckeditor/samples/js/sample.js" />'></script>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
    <link href='<spring:url value="/css/common/goods.css" />' rel="stylesheet"/>
    <style>
    .tabInform input[type="text"]{background:none;border:none;}
    </style>
</head>

<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">商品审核</span>
       <p>
           <input type="button" value="审核通过" class="referBtn" id="checkSucess" />
           <input type="button" value="审核未通过" class="cancelBtn col-sm-offset-1" id="checkFail" />
       </p>
    </div>
    <!--end pageHeadline -->
    
    <div class="whiteBox">
	<form id="submitForm" class="form-horizontal">
		<input type="hidden" id="id" value="${goods.id}"/>
	    <div class="form-group rowshortBox" style="margin-top: 15px" >
			<label class="control-label col-sm-2">商品名称</label>
			<div class="col-sm-10">
			    <input type="text" class="form-control" id="commodityName" name="commodityName" value="${goods.commodityName}" disabled="disabled"/>
			</div>
		</div>
	    <div class="form-group rowshortBox" style="margin-top: 15px" >
			<label class="control-label col-sm-2">商品分类</label>
			<div class="col-sm-10">
			    <select class="form-control"  name="commodityTypeId" id = "commodityTypeId" disabled="disabled">
					<option value="">请选择商品分类</option>
					<c:forEach items="${goodsClassificationList}" var="var">
						<option value="${var.id}" 
						<c:if test="${var.id==goods.commodityTypeId}">
							selected="selected"
						</c:if>
						>${var.categoryName}</option>
					</c:forEach>
				</select> 
			</div>
		</div>
        <div class="form-group rowshortBox">
            <label class="control-label col-sm-2">推广费</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="promotionFee" name="promotionFee"  value="${goods.promotionFee}" disabled="disabled"/>
            </div>
        </div>
        <div class="form-group rowshortBox">
            <label class="control-label col-sm-2">配送费</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="shippingFee" name="shippingFee" value="${goods.shippingFee}" disabled="disabled"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2">规格表</label>
            <div class="col-sm-10">
                <div class="tabInform">
                    <p class="tabName"><span>规格名称</span><span>库存</span><span>价格</span></p>
                    <c:forEach items="${goodsSpecificationsList}" var="var" varStatus="indx">
                        <p class="specifications">
                            <input type="text"  id="specificationsName${indx.index+1}" value="${var.specificationsName }" disabled="disabled"/>
                            <input type="text"  id="inventory${indx.index+1}" value="${var.inventory }" disabled="disabled"/>
                            <input type="text"  id="price${indx.index+1}" value="${var.price}" disabled="disabled"/>
                        </p>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="form-group" style="margin-top: 15px" >
			<label class="control-label col-sm-2">商品描述</label>
			<div class="col-sm-10">
			    <textarea id="commodityDescription" name="commodityDescription" rows="10" cols="30" disabled="disabled">${goods.commodityDescription}</textarea>
			</div>
		</div>
        <div class="form-group">
            <label class="control-label col-sm-2">商品详情图片</label>
            <div class="col-sm-10" style="width:730px;">
                <span id="picLaywer">
                    <c:forEach items="${goodsPhotos}" var="var">
                        <img id="licencePicture_img" src="<spring:url value='${var}' />" style="width: 130px;height: 90px"/>
                    </c:forEach>
                </span>
            </div>
        </div>
		<div class="form-group">
			<label class="control-label col-sm-2">商品轮播图片</label>
			<div class="col-sm-10" style="width:730px;">
				<span id="picLaywer">
					<c:forEach items="${commodityImages}" var="var">
						<img id="licencePicture_img" src="<spring:url value='${var}' />" style="width: 130px;height: 90px"/>
					</c:forEach>
				</span>
			</div>
		</div>
	</form> 
	</div>
	<script type="text/javascript">
		$(function() {
		   //审核通过
		   $("#checkSucess").bind("click",function(){
				$.ajax({
				type: "POST",
				url : '<spring:url value="/headquarters/goodsCheck/doCheckGoodsing" />',
				data:{
					id:$("#id").val(),
					goodsState:"3"
				},
				dataType: "json",
				async: false,
				success: function(data){
					if("0000000"==data.head.respCode){
						window.location.href='<spring:url value="/headquarters/goodsCheck/doEnGoodsCheckList" />';
						layer.msg('操作成功', {icon: 1});
					}
					else{
						layer.msg('操作失败', {icon: 6});
					}
				}
			 });
		  });
		   //审核未通过
		   $("#checkFail").bind("click",function(){
				$.ajax({
				type: "POST",
				url : '<spring:url value="/headquarters/goodsCheck/doCheckGoodsing" />',
				data:{
					id:$("#id").val(),
					goodsState:"2"
				},
				dataType: "json",
				async: false,
				success: function(data){
					if("0000000"==data.head.respCode){
						window.location.href='<spring:url value="/headquarters/goodsCheck/doEnGoodsCheckList" />';
						layer.msg('操作成功', {icon: 1});
					}
					else{
						layer.msg('操作失败', {icon: 6});
					}
				}
			 });
		  });		
		});
	</script>
</body>
</html>
