<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>确认订单</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <script type="text/javascript" src='<spring:url value="/js/plugin/layer/layer.js" />'></script>
    <link href='<spring:url value="/css/common/swiper.min.css" />' rel="stylesheet"/>
    <link href='<spring:url value="/css/common/lArea.css" />' rel="stylesheet"/>
    <link href='<spring:url value="/css/common/sharePage.css" />' rel="stylesheet"/>
</head>
<script type="text/javascript">
		function isWeiXin(){
			    var ua = window.navigator.userAgent.toLowerCase();
			    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
			        return true;
			    }else{
			        return false;
			    }
		}

		jQuery(document).ready(function(){
			/* if(isWeiXin()){
				location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxab6a41f141db132a&redirect_uri=http://hilink.chengguokj.com/XA/shareGoods/doEnGoodsView?response_type=code&scope=snsapi_base&state=0#wechat_redirect";
			} */
		})
</script>
<body>
    <header><a href="javascript:history.back(-1);"></a><span>确认订单</span></header>
    <input type="hidden" id="inventory" value="${goodsSpecifications.inventory}"/>
    <input type="hidden" id="goodsPrice" value="${goodsSpecifications.price}"/>
     <input type="hidden" id="shareUserId" value="${shareUserId}"/>
    <!--start confirmOrder-->
    <section class="confirmOrder">
    	<c:forEach items="${commodityImages}" var="var" varStatus="vat">
        		<c:if test="${vat.index==0}">
					<img id="licencePicture_img" src="<spring:url value='${var}' />" style="width: 130px;height: 90px"  onerror="javascript:this.src='<spring:url value='/img/loading.png'/>'"/>
				</c:if>
		</c:forEach>
		<input type="hidden" id="goodsUnitId" value="${goodsSpecifications.id}"/>
		<input type="hidden" id="goodsId" value="${goods.id}"/>
        <div class="rightBox">
            <p class="nameAndMoney"><span>${goods.commodityName}</span><em id="price">${goodsSpecifications.price}</em><em>￥</em></p>
            <p class="fromWhichStores">外贸百货</p>
            <p class="specification">
                <span>规格:${goodsSpecifications.specificationsName}</span>
                <span class="amount"><em onclick="upNum();">-</em><i id="num">1</i><em onclick="addNum();">+</em></span>
            </p>
            <p class="productDescription"><em></em><span >${goods.merchantsName}</span><span class="numRight">库存${goodsSpecifications.inventory}</span></p>
        </div>
    </section>
    <!--end confirmOrder-->
    
    <p class="consigneeHeadline">填写收货地址</p>
    <!--start consigneeInformation-->
    <section class="consigneeInformation">
        <p><span>收货人姓名</span><input type="text"  id="recipients" /></p>
        <p><span>联系电话</span><input type="text" id="telphone" /></p>
        <p>
            <!-- <span>省市区</span> -->
            <select id="sel_country4" style="display:none;"></select>
            <select id="sel_province4" name="sel_province4"></select>
            <select id="sel_city4" name="sel_city4"></select>
            <select id="sel_area4" name="sel_area4"></select>
        </p>
        <p><span>详细地址</span><input type="text" id="address" /></p>
    </section>
    <!--end consigneeInformation-->
    
    <div><button class="confirmPayment" >确认支付</button></div>
    <!--start payLawyer-->
    <div class="payLawyer"></div>
    <div class="payforBox">
        <p>请选择支付方式<span class="closePayLawyer"></span></p>
        <ul><li class="choosePayfor" onclick="surePay(1);">支付宝</li><li onclick="surePay(2);">微信</li></ul>
    </div>
    <!--end payLawyer-->
    <div id="allmap"></div>
<script>
    $(function(){
    	select_Init4();
        //打开支付弹层
        $(".confirmPayment").click(function(){
        	var reg=/^1[34578]\d{9}$/;
        	if($('#recipients').val()==null||$('#recipients').val()==''){
				layer.msg("收货人不能为空");
				return;
			}
			if($('#telphone').val()==null||$('#telphone').val()==''){
				layer.msg("联系电话不能为空");
				return;
			}
			if(!reg.test($('#telphone').val())){
				layer.msg("联系电话格式不正确");
				return;
			}
			if($('#sel_province4').val()==null||$('#sel_province4').val()==''){
				layer.msg("省份不能为空");
				return;
			}
			if($('#sel_city4').val()==null||$('#sel_city4').val()==''){
				layer.msg("市不能为空");
				return;
			}
			if($('#sel_area4').val()==null||$('#sel_area4').val()==''){
				layer.msg("区域不能为空");
				return;
			}
			if($('#address').val()==null||$('#address').val()==''){
				layer.msg("详细地址不能为空");
				return;
			}
			if(isWeiXin()){
			//微信支付
				surePay(2);
			}else{
			//支付宝支付
				surePay(1);
			}
            /* $(".payLawyer,.payforBox").show(); */
        });
      /*   //关闭支付弹层
        $(".closePayLawyer").click(function(){
            $(".payLawyer,.payforBox").hide();
        });
        //选择支付方式
        $(".payforBox li").click(function(){
            $(this).addClass("choosePayfor").siblings().removeClass("choosePayfor");
        }); */
    })
    function surePay(type){
				
		window.location.href='<spring:url value="/shareGoods/doEnSurePay" />?recipients='+$("#recipients").val()+'&telphone='+$('#telphone').val()+'&pricesId='+$('#sel_province4').val()
		+'&cityId='+$('#sel_city4').val()+'&areaId='+$('#sel_area4').val()+'&address='+$('#address').val()+'&goodsUnitId='+$('#goodsUnitId').val()+'&goodsId='+$('#goodsId').val()
		+'&accountType='+type+'&num='+$('#num').html()+'&price='+$('#price').html()+"&shareUserId="+$("#shareUserId").val();
		
    }
    //增加数量
    function addNum(){
       var num=$("#num").html();
       //库存
       var inventory=$("#inventory").val();
       if(Number(num)==Number(inventory)){
       		layer.msg("数量已达库存上限");
			return;
       }else{
       		num++;
       }
       $("#num").html(num);
       var pice=$('#goodsPrice').val();
       $('#price').html((num*pice)); 
    }
    //减少数量
     function upNum(){
       var num=$("#num").html();
       if(Number(num)==1){
       		layer.msg("数量不能小于1");
			return;
       }else{
       		num--;
       }
       $("#num").html(num);
       var pice=$('#goodsPrice').val();
       $('#price').html(num*pice);
    }
    
	// 下拉列表初始化
	function select_Init4(){
			// 初始化国家
			country_Init4();
			// 默认选中中国且不可修改
			$("#sel_country4").find("option[value='1']").attr("selected", true);
			$("#sel_country4").attr("disabled",true);
			
			// 初始化省份
			province_Init4();
	        
			// 初始化城市
			city_Init4();
	        
	        // 初始化县区
			area_Init4();
		}
		
		// 国家下拉事件
		$('#sel_country4').change(function(){
			province_Init4();
		});
		
		// 省份下拉事件
		$('#sel_province4').change(function(){
			city_Init4();
		});
		
		// 市下拉事件
		$('#sel_city4').change(function(){
			area_Init4();
		});
		
		// 初始化国家
		function country_Init4(){
			$("#sel_country4").append('<option value="">请选择</option>');
			<c:forEach var="item" items="${countryList}">
		    if('${item.parentId==0}'){
		    	$("#sel_country4").append('<option value=${item.areaId}>${item.areaName}</option>');
		    }
			</c:forEach>
		}
		
		// 初始化省份
		function province_Init4(){
			$("#sel_province4").empty();
			$("#sel_province4").append('<option value="" selected="selected">请选择省</option>');
			$("#sel_city4").empty();
			$("#sel_city4").append('<option value="">请选择市</option>');
			<c:forEach var="item" items="${provinceList}">
	            if($('#sel_country4 option:selected').val()=='${item.parentId}'){
	            	$("#sel_province4").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		
		// 初始化城市
		function city_Init4(){
			$("#sel_city4").empty();
			$("#sel_city4").append('<option value="">请选择市</option>');
			$("#sel_area4").empty();
			$("#sel_area4").append('<option value="">请选择区</option>');
			<c:forEach var="item" items="${cityList}">
	            if($('#sel_province4 option:selected').val()=='${item.parentId}'){
	            	$("#sel_city4").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
		
		// 初始化县区
		function area_Init4(){
			$("#sel_area4").empty();
			$("#sel_area4").append('<option value="">请选择区</option>');
			<c:forEach var="item" items="${areaList}">
	            if($('#sel_city4 option:selected').val()=='${item.parentId}'){
	            	$("#sel_area4").append('<option value=${item.areaId}>${item.areaName}</option>');
	            }
	        </c:forEach>
		}
</script>
</body>

</html>