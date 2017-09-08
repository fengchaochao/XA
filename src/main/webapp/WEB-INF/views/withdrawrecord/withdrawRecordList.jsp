<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>列表信息</title>
    <%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
    <link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet"/>
    <style>
        .showLawyer {position: fixed;width: 100%;height: 100%;background: #000;
            z-index: 9999;opacity: 0.3;top: 0px;left: 0px; display: none;}
		.showBox{display:none;position:fixed;z-index:100000;
		  background:#fff;padding:10px;top:15%;left:20%;width: 570px;
			min-height: 250px;border-radius: 5px;}
		.showBox .closeBtnRight {float: right;margin: 5px 10px;cursor: pointer;}
		.showBox span{float:right;margin:5px 10px;}
		.showBox p{margin-top:30px;line-height:20px;}
		.form-group select,#endDate{margin-right:20px;}
		#endDate{margin-left:5px;}
		#beginDate{margin-right:5px;}
		
		#formSearch select,#formSearch input{margin-bottom:15px;}
		#formSearch #btn_reset{margin-bottom:0px;}
        .confirm{background:none;border:1px solid #236adc;color:#236adc;margin-left:30px;padding: 3px 16px;}
		.referBtn,.confirm,.prohibition{border-radius:50px;}
		.referBtn,.prohibition{position:absolute;right:12%;top:-20px;z-index:999;}
		.prohibition{background: none;border: none;margin: 10px;padding: 5px 25px;
		  cursor: pointer;background:#d9d8d8;color:#666;}
	</style>
</head>
<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">余额提现</span>
    </div>
    <!--end pageHeadline -->

    <div class="whiteBox">
        <form id="formSearch" class="form-horizontal">
            <div class="form-group" style="margin-top: 15px;text-align:center;font-size:40px;">
                <span class="redText">
                	<input type="hidden" id="myEarnings" value="${myEarnings}"/>
                <c:if test="${bankWithdrawal.id==null||bankWithdrawal.id==''}"> ${myEarnings} </c:if>
                <c:if test="${bankWithdrawal.id!=null&&bankWithdrawal.id!=''}"> ${bankWithdrawal.totalRevenue} </c:if>
                </span><em class="redText" style="font-size:16px;">元</em>
            </div>
            <div class="form-group" style="margin-top: 25px;text-align:center;position:relative;">
                <span>我的收益</span>
                <c:if test="${sysUsers.userState=='1'}">
	                <button type="button" <c:if test="${msg=='2'}"> class="referBtn" </c:if><c:if test="${msg=='1'}"> class="prohibition" disabled="disabled"</c:if> 
	                onclick="editRecord();">提现</button>
                </c:if>
            </div>
            <c:if test="${sysUsers.userState=='1'}">
            <div class="form-group" style="margin: 50px 0px 25px 0px;text-align:center;">
                <span style="width:130px;text-align:right;">我的银行卡</span>
                <button type="button" class="confirm" onclick="datails();">请先绑定银行卡</button>
            </div>
            </c:if>
	    </form>
    </div>
    <div class="whiteBox">
        <p style="margin:20px 0px -35px 0px;">提现记录</p>
        <table id="tb_board"></table> 
    </div>
	
	<input type="hidden" id="id" value="${bankWithdrawal.id}"/>
	<input type="hidden" id="msg" value="${msg}"/>
	
	<div class="showLawyer"></div>
	<div class="showBox" id ="showBox">
	    <img class="closeBtnRight" onclick="cancel();" src='<spring:url value="/img/login/clos_comleft.png" />' />
		<p>
			 <c:if test="${msg=='2'}">
				<div class="form-group" style="margin-top: 15px" >
					<label class="control-label col-sm-2">提现银行</label>${bankWithdrawal.brankAddress}
				</div>
				<div class="form-group" style="margin-top: 15px" >
					<label class="control-label col-sm-2">提现卡号</label>${bankWithdrawal.brankNumber}
				</div>
				<div class="form-group" style="margin-top: 15px" >
					<label class="control-label col-sm-2">持  卡  人</label>${bankWithdrawal.brankName}
				</div>
				<div class="form-group" style="margin-top: 15px" >
					<label class="control-label col-sm-2">身份证号</label>${bankWithdrawal.idCard}
				</div>
				<div class="form-group" style="margin-top: 15px" >
					<label class="control-label col-sm-2">联系方式</label>${bankWithdrawal.brankPhone}
				</div>
			 </c:if>
			 <c:if test="${msg=='1'}">
				<div class="form-group" style="margin-top: 15px;height:35px;" >
					<label class="control-label col-sm-2" style="heigth:35px;line-height:34px;">提现银行</label>
					<div class="col-sm-8">
					    <input type="text" class="form-control" id="brankAddress" />
					</div>
				</div>
				<div class="form-group" style="margin-top: 15px;height:35px;" >
					<label class="control-label col-sm-2" style="heigth:35px;line-height:34px;">提现卡号</label>
					<div class="col-sm-8">
					    <input type="text" class="form-control" id="brankNumber"/>
					</div>
				</div>
				<div class="form-group" style="margin-top: 15px;height:35px;" >
					<label class="control-label col-sm-2" style="heigth:35px;line-height:34px;">持  卡  人</label>
					<div class="col-sm-8">
					    <input type="text" class="form-control" id="brankName" />
					</div>
				</div>
				<div class="form-group" style="margin-top: 15px;height:35px;" >
					<label class="control-label col-sm-2" style="heigth:35px;line-height:34px;">身份证号</label>
					<div class="col-sm-8">
					    <input type="text" class="form-control" id="idCard" />
					</div>
				</div>
				<div class="form-group" style="margin-top: 15px;height:35px;" >
					<label class="control-label col-sm-2" style="heigth:35px;line-height:34px;">联系方式</label>
					<div class="col-sm-8">
					    <input type="text" class="form-control" id="brankPhone" />
					</div>
				</div>
				<div class="form-group" style="margin-top: 15px;height:35px;" >
                    <label class="control-label col-sm-2" style="heigth:35px;line-height:34px;"></label>
                    <div class="col-sm-8" style="margin-top:-34px;font-size:12px;">
                        <p style="color:#f74f4c;">银行卡绑定后不可修改</p>
                    </div>
                </div>
				<div class="form-group" style="margin-top: 15px;text-align: center;" >
					<button type="button" class="keepBtn" onclick="saveBank();" style="background:#3ab2c3;">确定</button>
					<button type="button" class="cancelBtn" onclick="cancel();">取消</button>
				</div>
			 </c:if>
	    </p>
    </div>

    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/bootstrap-table.min.1.10.1.js"/>'></script>
    <script type="text/javascript" src='<spring:url value="/js/plugin/bootstrap-table-1.10.1/locale/bootstrap-table-zh-CN.min.js"/>'></script>
	<script type="text/javascript">
	    var $table;
		$(function() {
			initTable();
		});
		// 初始化表格
		function initTable() {
            $table = $('#tb_board').bootstrapTable({
                method: 'get',
                url : '<spring:url value="/withdrawRecord/doReadWithdrawRecordList" />',
                pagination: true,
                pageSize: 10,
                pageList: [10, 50, 100, 200, 500],
                sidePagination: "server", //服务端请求
                showRefresh : true,
				striped : true,
				silent : true,
				sortable: true, 
				sortOrder: "asc",
                responseHandler: responseHandler,
                columns : [ {
                	field: 'state',
					checkbox : true
				}, {
					field : 'withdrawDate',
					title : '时间'
				}, {
					field : 'withdrawPrice',
					title : '提现金额'
				}, {
					field : 'bankWithdrawal.brankAddress',
					title : '提现银行'
				}, {
					field : 'bankWithdrawal.brankNumber',
					title : '银行卡号'
				}, {
					field : 'bankWithdrawal.brankName',
					title : '开户人姓名'
				}, {
					field : 'applyState',
					title : '状态',
					formatter : statusFormatter 
				}, {
					field : 'result',
					title : '结果',
					formatter : statusFormatter1 
				}],
                onLoadSuccess:function(){

                },
                formatLoadingMessage : function() {
									return "请稍等，正在加载中...";
								},
				formatNoMatches : function() { //没有匹配的结果
					return "无符合条件的记录";
				},
                onLoadError: function () {
                    alert("数据加载失败！");
                }
            });
        }
		
		// 提现
        function editRecord() {
       	    var id=$("#id").val();
       	    var msg=$("#msg").val();
       		if(msg=="1"){
				top.layer.alert("请先绑定银行卡！",{icon:2});
				return;
			}else{
       			window.location.href='<spring:url value="/withdrawRecord/doEnWithdrawRecordEdit" />?bankWithdrawalId='+id;
			}
       		 
        }
		
		// json 数据处理
        function responseHandler(res) {
			if (res.body.total > 0) {
				return {
					"rows" : res.body.rows,
					"total" : res.body.total
				}
			} else {
				return {
					"rows" : [],
					"total" : 0
				}
			}
		}
        // 显示数据格式化 statusFormatter
        function statusFormatter(value, row, index) {
            if(value=='1')
            	return "已处理";
            if(value=='0')
            	return "未处理";
             if(value=='2')
            	return "驳回";
        }
        // 显示数据格式化 statusFormatter
        function statusFormatter1(value, row, index) {
            if(value=='1')
            	return "打款失败";
            if(value=='0')
            	return "已打款";
           	if(value==''||value==null)
           		return " ";
        }
        //弹层
        function datails(){
	        $(".showLawyer,.showBox").show();
        }
        
        function cancel(){
        	$("#brankAddress").val("");
        	$("#brankNumber").val("");
        	$("#brankName").val("");
        	$("#brankPhone").val("");
        	$(".showLawyer,.showBox").hide();
        }
		function saveBank(){
			alert();
			if($('#brankAddress').val()==null||$('#brankAddress').val()==""){
				top.layer.alert("请输入提现银行！",{icon:2});
				return;
			}
			if($('#brankNumber').val()==null||$('#brankNumber').val()==""){
				top.layer.alert("请输入提现卡号！",{icon:2});
				return;
			}
			if($('#brankName').val()==null||$('#brankName').val()==""){
				top.layer.alert("请输入持卡人姓名！",{icon:2});
				return;
			}
			if($('#brankPhone').val()==null||$('#brankPhone').val()==""){
				top.layer.alert("请输入手机号！",{icon:2});
				return;
			}
			if($('#idCard').val()==null||$('#idCard').val()==""){
				top.layer.alert("请输入身份证号！",{icon:2});
				return;
			}
			var reg=/(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
			if(!reg.test($('#idCard').val())){
				top.layer.alert("身份证号格式不正确！",{icon:2});
				return;
			}
			var reg1=/^[0-9]+$/;
			if(!reg1.test($('#brankNumber').val())){
				top.layer.alert("银行卡号只能输入数字！",{icon:2});
				return;
			}
			var reg=/^1[34578]\d{9}$/;
			if(!reg.test($('#brankPhone').val())){
				top.layer.alert("手机号码不正确，请重新输入！",{icon:2});
				return;
			}
			/**********银行卡信息第三方验证**************/
			/*************************************/
		   layer.confirm('银行卡绑定之后不能修改，确认绑定？', {
			  btn: ['确认','取消'] //按钮
			  }, function(){
				$.ajax({
					type: "POST",
					url : '<spring:url value="/withdrawRecord/doSaveBankInfo" />',
					data:{
						id:$("#id").val(),
						totalRevenue:$("#myEarnings").val(),
						brankAddress:$("#brankAddress").val(),
						brankNumber:$('#brankNumber').val(),
						brankName:$('#brankName').val(),
						idCard:$('#idCard').val(),
						brankPhone:$('#brankPhone').val()
					},
					dataType: "json",
					async: false,
					success: function(data){
						if("保存成功"==data.body){
							top.layer.alert(data.body,{icon:1});
		 					window.location.href='<spring:url value="/withdrawRecord/doEnWithdrawRecordList" />';
						}
						else{
							top.layer.alert(data.body,{icon:2});
						}
					}
				});
			}, function(){
				layer.msg('已取消', {icon: 1});
		});
	 }
	</script>

</body>
</html>
