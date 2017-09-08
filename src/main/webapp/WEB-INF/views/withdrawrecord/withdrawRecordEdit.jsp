<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
    <style>
        .showLawyer {position: fixed;width: 100%;height: 100%;background: #000;
            z-index: 9999;opacity: 0.3;top: 0px;left: 0px; display: none;}
		.showBox{display:none;border:1px solid #666;position:fixed;z-index:100000;
		    background:#fff;padding:10px;top:15%;left:25%;width: 200px;min-height: 100px;}
		.showBox span{float:right;margin:5px 10px;}
		.showBox p{margin-top:30px;line-height:20px;}
		.form-group select,#endDate{margin-right:20px;}
		#endDate{margin-left:5px;}
		#beginDate{margin-right:5px;}
		
		#formSearch select,#formSearch input{margin-bottom:15px;}
		#formSearch #btn_reset{margin-bottom:0px;}
		.col-sm-2{padding-left: 0px;width:90px;}
		#withdrawPrice,#code{display:inline-block;width:70%;}
		.getCode{display:inline-block;width:100px;color:#236adc;background:none;outline: none;}
	</style>
</head>

<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">提现</span>
    </div>
    <!--end pageHeadline -->
    
    <div class="whiteBox">
		<form id="submitForm" class="form-horizontal">
		
			<input type="hidden" id="bankId" value="${bankWithdrawal.id}" />
			<input type="hidden" id="myEarnings" value="${bankWithdrawal.totalRevenue}" />
			<input type="hidden" id="price" value="${price}" />
			
		    <div class="form-group" style="margin-top: 15px" >
				<label class="control-label col-sm-2">提现金额</label>
				<div class="col-sm-5">
				    <input type="text" class="form-control" id="withdrawPrice" />
				    <span>元</span> 
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">手机验证码</label>
				<div class="col-sm-5">
				    <input type="text" class="form-control" id="code" />
				    <input type="button" value="获取验证码" class="form-control getCode" id="send"/>
				</div>
			</div>
			
			<div class="form-group">
		        <div class="col-sm-12">
				  <div class="navbar-btn" style="text-align:left;margin:40px 0px 0px 80px;">
				  	  <input type="button" value="提交申请" class="referBtn" id="commit" />
				  </div>
		        </div>
	        </div>
		  
		</form> 
	</div>
	<div class="showLawyer"></div>
	<div class="showBox" id ="showBox">
		<p>
			<em>申请成功</em>
		</p>
		<div class="form-group" style="margin-top: 15px" >
			<button type="button" class="btn btn-primary" onclick="saveBank();">继续提现</button>
			<button type="button" class="btn btn-primary" onclick="account();">个人中心</button>
		</div>
  	</div>
	<script type="text/javascript">
	$(function() {
		//发送验证码
	 	var times=60;
		var timer=null;
		
	 	$("#send").click(function(){
		 	var that=this;
			this.disabled=true;
			sendMsg();
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
		
		//确认提交申请
		$("#commit").bind("click",function(){
			if($('#withdrawPrice').val()==null||$('#withdrawPrice').val()==""){
				top.layer.alert("提现金额不能为空！",{icon:2});
				return;
			}
			var reg1=/^[0-9]*$/;
			if(!reg1.test($('#withdrawPrice').val())){
				top.layer.alert("只能输入数字！",{icon:2});
				return;
			}
			if(Number($("#price").val())<100){
				top.layer.alert("您可申请的提现额度小于100元，不能提现！",{icon:2});
				return;
			}
			 if(Number($('#withdrawPrice').val())<100){
				top.layer.alert("提现金额不能小于100元！",{icon:2});
				return;
			}
			if(Number($('#withdrawPrice').val())>Number($('#price').val())){
				top.layer.alert("您可申请的提现额度为"+Number($('#price').val())+",不能超过该额度！",{icon:2});
				return;
			}
			//验证码是否通过
			
			 if($('#code').val()==null||$('#code').val()==""){
				top.layer.alert("验证码不能为空！",{icon:2});
				return;
			} 
			
			$.ajax({
				type: "POST",
				url : '<spring:url value="/withdrawRecord/doEditWithdrawRecord" />',
				data:{
					withdrawPrice:$("#withdrawPrice").val(),
					code:$("#code").val(),
					bankId:$('#bankId').val()
				},
				dataType: "json",
				async: false,
				success: function(data){
					if(data.body=="提交成功"){
						 $(".showBox").show();
					}else{
						top.layer.alert(data.body,{icon:2});
					}
				}
			});
		});	
 	});
	//获取手机验证码	 
	function  sendMsg(){
		$.ajax({
			type: "get",
			url: '<spring:url value="/withdrawRecord/doEnSendCode" />',
			async: true,
			dataType: "json",
			success: function(data){
				top.layer.alert(data.body);
			}
		});
	}	
	//继续提现
	function saveBank(){
		window.location.href='<spring:url value="/withdrawRecord/doEnWithdrawRecordEdit" />?bankWithdrawalId='+$('#bankId').val();
	}
	//个人中心
	function account(){
		window.location.href='<spring:url value="/sysuser/doEnEditSysUserInfo" />';
	}
	</script>
</body>


</html>
