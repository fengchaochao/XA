<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商品详情</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
	<script type="text/javascript" src='<spring:url value="/js/plugin/layer/layer.js" />'></script>
    <link href='<spring:url value="/css/common/swiper.min.css" />' rel="stylesheet"/>
    <link href='<spring:url value="/css/common/sharePage.css" />' rel="stylesheet"/>
</head>
<body>
    <header><!-- <a></a> --><!--<img src="imgs/back.png" />--><span>商品详情</span></header>
    <!--start 轮播图-->
    <div class="swiper-container topLisderImg">
    <input type="hidden" id="id" value="${goods.id}"/>
    <input type="hidden" id="shareUserId" value="${shareUserId}"/>
        <div class="swiper-wrapper">
        	<c:forEach items="${commodityImages}" var="var" varStatus="vat">
        		<c:if test="${vat.index==0}">
					<div class="swiper-slide"><img id="licencePicture_img" src="<spring:url value='${var}' />" style="width: 100%;height: 154px"  onerror="javascript:this.src='<spring:url value='/img/loading.png'/>'"/></div>
				</c:if>
			</c:forEach>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination topPagination"></div>
    </div>
    <!--end 轮播图-->
    
    <!--start productDetails-->
    <section class="productDetails">
        <p style="margin-bottom:5px;">
            <span class="price">￥${goods.goodsPrice}</span>
            <span class="repertory">库存${goods.inventory}</span>
        </p>
        <p class="textBox">${goods.commodityName}</p>
        <p class="fromStoreName"><em></em><span>${goods.merchantsName}</span></p>
    </section>
    <!--end productDetails-->
    
    <!--start productSize-->
    <section class="productSize">
        <p class="specification">规格</p>
        <div class="sizeCategories">
        	<c:forEach items="${goodsSpecificationsList}" var="var" varStatus="vat">
           	 <span  id="${var.id}"
           	 	<c:if test="${vat.index==0}">class="chooseSize" </c:if>
           	 onclick="goodsUtil('${var.price}','${var.inventory}')">${var.specificationsName}</span>
            </c:forEach>
        </div>
    </section>
    <!--end productSize-->
    
    <!--start commodityEvaluation-->
    <section class="commodityEvaluation">
        <p class="specification">商品评价</p>
        <c:forEach items="${goodsEvaluateList}" var="var" varStatus="vat">
        	<c:if test="${vat.index==0}">
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
		            </p>
		            <p class="evaluateTime">${var.evaluateDate}</p>
		            <p class="evaluateContent">${var.evaluateContent}</p>
		        </div>
	        </c:if>
        </c:forEach>
        <p class="anyMore"><button onclick="evaluateRecord();">查看更多评价</button></p>
    </section>
    <!--end commodityEvaluation-->
    
    <!--start graphicDetails-->
    <section class="graphicDetails">
        <p class="graphicHeadline">图文详情</p>
        <div class="graphicText">${goods.commodityDescription}</div>
        <c:forEach items="${goodsPhotos}" var="var">
				<img id="licencePicture_img" src="<spring:url value='${var}' />" style="width: 130px;height: 90px"/>
			</c:forEach>
    </section>
    <!--end graphicDetails-->
    
    <div><button class="buyNowBtn" onclick="buyNow();">立即购买</button></div>
    <script type="text/javascript" src='<spring:url value="/js/_base/swiper.jquery.min.js" />'></script>
<script>
    $(function(){
        //图片轮播
        var swiper = new Swiper('.topLisderImg', {
            pagination: '.topPagination',
            paginationClickable: true,
            centeredSlides: true,
            autoplay: 2500,
            autoplayDisableOnInteraction : false
        });
        //选择尺寸
        $(".sizeCategories span").click(function(){
            $(this).addClass("chooseSize").siblings().removeClass("chooseSize");
        });
    })
    function goodsUtil(price,num){
	     $(".price").html("￥"+price);
	     $(".repertory").html("库存"+num);
    }
    // 更多评价
    function evaluateRecord() {
		window.location.href='<spring:url value="/shareGoods/doEnGoodsSelById"/>'+'?goodsId='+$("#id").val();
    }
    //立即购买
    function buyNow() {
    	var goodsUnit=$(".chooseSize").attr("id");
    	if(goodsUnit==null||goodsUnit==''){
    		layer.msg("请先选择需要购买的商品");
    		return;
    	}
		window.location.href='<spring:url value="/shareGoods/doEnBuyNow"/>'+'?goodsUnitId='+goodsUnit+'&goodsId='+$("#id").val()+"&shareUserId="+$("#shareUserId").val();
    }
</script>
</body>


</html>