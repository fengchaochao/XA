<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
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
							    	//成功之后跳转到嗨联项目的Action 
							    	location.href="https://hilink.chengguokj.com/XA/api/wxResShow?amount=${amount}";
							    }
			    		});
			    	}
			    }
			    });
			
		}
	</script>
