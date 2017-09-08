<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<script type="text/javascript"
			src='<spring:url value="/js/_base/jquery-1.12.1.min.js" />'>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>转账</title>
        <style>
            html,body{
                margin: 0;
                padding: 0;
                width: 100%;
            }
            .back-left{
                height: 60px;
                line-height: 60px;
                background: #292929;
                color: #fff;
                text-align: center;
                font-size: 20px;
            }
            .back-title{
                margin-left: -30px;
            }
            .arrow-left{
                float: left;
                margin-top: 15px;
                padding-left: 15px;
                vertical-align: middle;
            }
            .transfer-accounts{
                position: relative;
                margin:0 auto;
                padding-top: 5vh;
                width: 100%;
                height: 100%;
                text-align: center;
                font-family: "heiti";
            }
            .success-img img{
            }
            .success-text{
                font-size: 20px;
            }
            .success-money{
                font-size: 30px;
            }
            .transaction-time{
                position: fixed;
                bottom:0;
                left: 0;
                width: 100%;
                color: #c6c6c6;
                font-size: 15px;
            }
        </style>
    </head>
    <body>
        <div class="back-left">
            <img class="arrow-left" src="arrow-left.png" height="30" width="16" alt="">
            <span class="back-title">转账成功</span>
        </div>
        <div class="transfer-accounts">
            <p class="success-img"><img src="success.png" height="100" width="100" alt=""></p>
            <p class="success-text">转账成功</p>
            <p class="success-money"><span>¥</span><span class="money">${amount}</span></p>
            <div class="transaction-time">
                <p><span>转账时间:</span><span id="transfer-time">2017-09-04 10:34</span></p>
                <p><span>收钱时间:</span><span id="collect-time">2017-09-04 10:34</span></p>
            </div>
        </div>
        <script>
            var transferTime=document.getElementById('transfer-time');
            var collectTime=document.getElementById('collect-time');
            transferTime.innerHTML=getTime();
            collectTime.innerHTML=getTime();

            function getTime(){
              var date = new Date();
              this.year = date.getFullYear();
              this.month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth()+ 1) : (date.getMonth()+ 1);
              this.date = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
              this.day = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")[date.getDay()];
              this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
              this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
              this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
              var currentTime = this.year + "-" + this.month + "-" + this.date + " " + this.hour + ":" + this.minute + ":" + this.second;
              return currentTime;
            }
        </script>
    </body>
</html>