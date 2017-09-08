<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="keywords" content="">
<meta name="description" content="">
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<title>请付款-微信</title>
<style>
    *{margin: 0px;padding: 0px;font-family:'Microsoft Yahei';}
    input,textarea,select{font-family:inherit;font-size:inherit;font-weight:inherit;-webkit-border-radius:0px;border-radius:0px;border:none;-webkit-appearance:none;-webkit-tap-highlight-color:rgba(0, 0, 0, 0);font-size:100%;outline:0}
    input, select{vertical-align:middle}
    input[type="number"]::-webkit-outer-spin-button,input[type="number"]::-webkit-inner-spin-button{-webkit-appearance: none !important;margin:0;display:none}
    body{background: #ededed;font-size: 14px;}
    section,.contactInformation{border-radius: 3px;}
    .contactInformation,button{text-align: center;}
    section{background: #fff;margin: 15px; box-sizing: border-box;margin-top: 6%;}
    .contactInformation{background: #fbfbfb;padding: 10px 0px 20px 0px;color: #8a8a8a;}
    .contactInformation img{height: 50px;width: 50px;margin-bottom: 5px;}
    .transferAmount{padding: 25px;box-sizing: border-box;}
    .payMoney{margin: 15px 0px;border-bottom: 1px solid #ddd;}
    .payMoney em{font-style: normal;display: inline-block;font-size: 28px; margin-left: -5px;}
    .payMoney input{font-size: 40px;width: 75%;font-weight: bold;margin-bottom: 5px;margin-left: 5px;}
    button{width: 100%;height: 45px;line-height: 45px;color: #fff;background: #1aac19;border: none;border-radius: 5px;font-size: 18px;margin: 6% 0px;}
    
</style>
</head>
<body>
<form action="<spring:url value="/api/doWeixinPay" />" method="post" id="ListForm">
<input type="hidden" id = "storeId" name = "storeId" value="${storeId}">
<input type="hidden" id = "code" name = "code" value="${code}">
    <section>
        <div class="contactInformation"> 
            <img src="<spring:url value='${personPhoto}'/>" onerror="javascript:this.src='<spring:url value='/img/evaluateUser.png'/>'"/>
            <p>向个人用户"<span>${storeName}</span>"转账</p>
        </div>
        <div class="transferAmount">
            <p>转账金额</p>
            <p class="payMoney"><em>￥</em><input type="text" name = "amount"/></p>
            <button type="submit"  <c:if test="${msg!='1'}"> disabled="disabled" </c:if>>确认支付</button>
            <c:if test="${msg!='1'}"> <p>该商家没有在平台绑定相应的支付账号</p></c:if>
        </div>
    </section>
</form>
 <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    ${check},// 必填，签名，见附录1
		    jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
		wx.ready(function(){
			callpay();
		});
		wx.error(function(res){
		    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
		});
		function callpay(){
			wx.checkJsApi({
			    jsApiList: ['chooseWXPay'], // 检查微信支付接口是否可用
			    success: function (res) { 
			    	if(res.checkResult.chooseWXPay){
			    		wx.chooseWXPay({
							 ${str}, // 支付签名
							 	cancel:function(res){
							    	alert("支付取消");
							    },
							    error:function(res){
							    	alert("支付出错");
							    },
							    success:function(res){
							    	alert("支付成功");
							    }
			    		});
			    	}
			    }
			    });
			
		}
	</script>
 
</body>
</html>