<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>忘记密码</title>
	    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
	    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
        <style>
            body{background: #f5f5f5;text-align: center;font-size: 14px;}
            .forgetPassBox,span{display: inline-block;}
            .resetPasswords{width: 1000px;font-size: 24px;margin: auto;margin-top:2%;}
            .resetPasswords img{ margin-right: 10px;vertical-align: middle;}
            .forgetPassBox{background: #fff;margin: auto;box-shadow: 1px 1px 20px #ddd;padding:0px 20px;padding-right: 70px;margin-top: 2%;}
            input{border: 1px solid #ddd;height:33px;line-height: 33px;padding-left: 6px;width:300px;border-radius: 2px;outline: none;}
            .forgetPassBox span{width:120px;text-align: right;margin-right: 10px;}
             p{text-align: left;margin-bottom:15px;}
             .codeText{width:190px;border-top-right-radius: 0px;border-bottom-right-radius: 0px;vertical-align: middle;}
            .codeBox{width:115px;background: none;border: 1px solid #ddd;height:33px;margin-left: -10px;vertical-align: middle;
            border-left: 0px;border-top-left-radius: 0px;border-bottom-left-radius: 0px;
            cursor: pointer;color: #236adc;line-height: 30px;}
            .btnBox{margin: 50px 0px 50px 0px;text-align: center;padding-left:70px;}
            .btnBox button{margin: 0px 20px;}
            .confirm, .cancelBtn{padding:6px 40px;}
        </style>
    </head>
    <body>
        <p class="resetPasswords"><img src='<spring:url value="/img/login/logo.png" />' />嗨联软件后台</p>
        <div class="forgetPassBox">
            <p style="font-size:20px;margin-bottom:40px;margin-top:20px;">重置登录密码</p>
            <p><span>手机号</span><input type="text" placeholder="请输入手机号" id="phone" /></p>
            <p>
                <span>验证码</span><input class="codeText"  type="text" placeholder="请输入验证码" id="code"/>
                <input class="codeBox" type="button" value="获取验证码" id="send"/>
            </p>
            <p><span>新密码</span><input type="password" placeholder="请输入新密码" id="password1" /></p>
            <p><span>确认新密码</span><input type="password" placeholder="请再次输入新密码" id="password2"/></p>
            <p class="btnBox">
                <button class="confirm" onclick="updatePassword();">确认</button>
                <button class="cancelBtn" onclick="ReturnBack();">取消</button>
            </p>
        </div>
        <script type="text/javascript">
    $(function(){
    	//发送验证码
	 	var times=60;
		var timer=null;
		
	 	$("#send").click(function(){
	 		var myregPhone = /^0?(13[0-9]|15[012356789]|18[0-9]|17[0-9])[0-9]{8}$/;
	 		var phone=$("#phone").val();
	 	 	if(phone==null||phone==""){
				top.layer.alert("请输入账号关联的手机号码!",{icon:2});
				return false;
			}else if(!myregPhone.test($("#phone").val())){
				top.layer.alert("请填写正确的手机号码!",{icon:2});
				return false;
			}
		 	var that=this;
			this.disabled=true;
			sendMsg(phone);
			timer=setInterval(function(){
 				times--;
 				that.value = times + "秒后重试";
 				if(times<=0){
		 			that.disabled = false;
		 			that.value = "发送验证码";
		 			clearInterval(timer);
		 			times = 60;
 				}
		
			},1000);
		});
    })
   	function sendMsg(phone){
		$.ajax({
			type: "get",
			url: '<spring:url value="/sysuser/account/doEnSendCode" />',
			data:{
				phone:phone,
			},
			async: true,
			dataType: "json",
			success: function(data){
				top.layer.alert(data.msg);
			}
		});
	}
	function updatePassword(){
		var myregPhone = /^0?(13[0-9]|15[012356789]|18[0-9]|17[0-9])[0-9]{8}$/;
 		var phone=$("#phone").val();
 		var code=$("#code").val();
 	 	if(phone==null||phone==""){
			top.layer.alert("请输入账号关联的手机号码!",{icon:2});
			return false;
		}else if(!myregPhone.test($("#phone").val())){
			top.layer.alert("请填写正确的手机号码!",{icon:2});
			return false;
		}
		if(code==null||code==""){
			top.layer.alert("请输入验证码!",{icon:2});
			return false;
		}
		var newPass = "";
		if($("#password1").val()==''){
			top.layer.alert("新密码不能为空",{icon:2});
			return;
		}
		if($("#password1").val()!=$("#password2").val()){
			top.layer.alert("两次密码不一致",{icon:2});
			return;
		}
		newPass = $("#password1").val();
		$.ajax({
			type: "POST",
			url: '<spring:url value="/sysuser/account/doEnFindPassword" />',
			data:{
				phone:phone,
				loginPass:newPass,
				code:code
			},
			async: true,
			dataType: "json",
			success: function(data){
				
				if(data.body=="成功找回密码"){
					top.layer.alert(data.body,{icon:1});
					window.location.href='<spring:url value="/sysuser/account/doEnSysUserLogin" />';
				}else{
					top.layer.alert(data.body,{icon:2});
				}
			}
		});
	}
	function ReturnBack(){
		window.location.href='<spring:url value="/sysuser/account/doEnSysUserLogin" />';
	}
    </script>
    </body>
    
</html>
