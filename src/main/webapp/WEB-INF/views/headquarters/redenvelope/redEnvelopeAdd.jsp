<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑</title>
<%@ include file="/WEB-INF/views/common/headCommon.jsp"%>
<link href='<spring:url value="/css/plugin/bootstrap-table/bootstrap-table.min.1.10.1.css" />' rel="stylesheet" />
<style>
     ul,li{text-align: left;padding:0px;margin:0px;}
     ul{margin: auto;width:801px;}
     li{display: inline-block;list-style:none;float:left;border:1px solid #000;border-left:none;border-top:none;
     width:80px;text-align:center;line-height: 30px;}
     li:nth-child(1),li:nth-child(2),li:nth-child(3),li:nth-child(4),li:nth-child(5),li:nth-child(6),
     li:nth-child(7),li:nth-child(8),li:nth-child(9),li:nth-child(10){border-top:1px solid #000;}
     li:nth-child(10n+1){border-left:1px solid #000;}
     .col-sm-2{width:86px;padding-left:0px;}
     #createDate,#createTime{display:inline-block;width:48%;}
     #redEnvelopeType,#redEnvelopeType1{display: inline-block;width: 20px;height: 20px;vertical-align: middle;margin-top:-3px;}
     .orangeBtn,.greenBtn{float:left;}
</style>
</head>

<body class="mainContant">
    <!--start pageHeadline -->
    <div class="pageHeadline">
       <span class="leftNotice">新增红包</span>
    </div>
    <!--end pageHeadline -->

    
	<form id="submitForm" class="form-horizontal">
	    <div class="whiteBox">
            <div class="form-group" style="margin-top: 15px">
	            <label class="control-label col-sm-2">红包金额</label>
	            <div class="col-sm-5">
	                <input type="text" class="form-control" id="totalPrice" name="totalPrice" />
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="control-label col-sm-2">红包个数</label>
	            <div class="col-sm-5">
	                <input type="text" class="form-control" id="number" name="number" />
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="control-label col-sm-2">发布时间</label>
	            <div class="col-sm-5"> 
	                <input type="text" class="form-control" id="createDate"
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" />
	                <em>-</em>
	                <input type="text" class="form-control" id="createTime"
	                    onclick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'%H:{%m+15}:%s'})" />
	            </div>
	        </div>
	         <div class="form-group">
	            <label class="control-label col-sm-2">红包失效时间（分钟）</label>
	            <div class="col-sm-5"> 
	            	<input type="text" class="form-control" id="expirationDate" name ="expirationDate"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="control-label col-sm-2">红包种类</label>
	            <div class="col-sm-10" style="line-height:32px;">
	                <input type="radio" class="" name="redEnvelopeType" id="redEnvelopeType"
	                    value="0" checked="checked" onclick="random();" />随机红包 <input
	                    type="radio" class="" name="redEnvelopeType" id="redEnvelopeType1" value="1"
	                    onclick="fixedAllocation();" style="margin-left:20px;" />固定红包
	            </div>
	        </div>
	        <div class="form-group" id="randomPrice">
	            <label class="control-label col-sm-2">最小金额</label>
	            <div class="col-sm-3" style="width:20%;">
	                <input type="text" class="form-control" id="minPrice" />
	            </div>
	            <label class="control-label col-sm-2">最大金额</label>
	            <div class="col-sm-3" style="width:20%;">
	                <input type="text" class="form-control" id="maxPrice" />
	            </div>
	        </div>
	        <div class="form-group">
	            <div class="col-sm-12">
	                <div class="navbar-btn" style="padding-left:80px;">
	                    <input type="button" value="生成红包"
	                        class="orangeBtn" onclick="generate();"/> <input
	                        type="button" value="修改红包" class="greenBtn col-sm-offset-1"
	                        onclick="update();" />
	                </div>
	            </div>
	        </div>
        </div>
	    
		<div class="form-group" style="background: #fff;padding: 30px 0px;margin-bottom:20px;">
            <div class="col-sm-12">
              <div class="navbar-btn">
                <ul id="tb_board"></ul>
                <input type="hidden" id="amountAllocated" />
              </div>
            </div>
        </div>
        
		<div class="form-group">
			<div class="col-sm-12">
				<div class="navbar-btn">
					<input type="button" value="保存" class="yellowReturnRad" id="commit" /> 
					<input type="button" value="取消" class="redBtnRad col-sm-offset-1" id="cancel" />
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			//输入验证
			$('#submitForm')
					.bootstrapValidator(
							{
								message : 'This value is not valid',
								feedbackIcons : {
									valid : 'glyphicon glyphicon-ok',
									invalid : 'glyphicon glyphicon-remove',
									validating : 'glyphicon glyphicon-refresh'
								},
								fields : {
									totalPrice : {
										validators : {
											notEmpty : {
												message : '不能为空'
											},
											regexp : {
												regexp : /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/,
												message : '格式有误，例如：0.00/00!'
											}
										}
									},
									number : {
										validators : {
											notEmpty : {
												message : '不能为空'
											},
											regexp : {
												regexp : /^[0-9]+$/,
												message : '只能是数字!'
											}
										}
									},
									expirationDate : {
										validators : {
											notEmpty : {
												message : '不能为空'
											},
											regexp : {
												regexp : /^[0-9]+$/,
												message : '只能是数字!'
											}
										}
									}

								}
							});
			$("#cancel")
					.bind(
							"click",
							function() {
								window.location.href = '<spring:url value="/headquarters/redEnvelope/doEnRedEnvelopeList" />';
							});
			//保存
			$("#commit")
					.bind(
							"click",
							function() {
								var data = {};
								if ($('#amountAllocated').val() == null
										|| $('#amountAllocated').val() == "") {
									top.layer.alert("请先生成红包！", {
										icon : 2
									});
									return;
								}
								if ($('input[name="redEnvelopeType"]:checked')
										.val() == '0') {
									data = {
										"totalPrice" : $("#totalPrice").val(),
										"number" : $("#number").val(),
										"createDate" : $('#createDate').val(),
										"createTime" : $('#createTime').val(),
										"expirationDate" : $('#expirationDate').val(),
										"minPrice" : $('#minPrice').val(),
										"maxPrice" : $('#maxPrice').val(),
										"amountAllocated" : $(
												'#amountAllocated').val(),
										"redEnvelopeType" : $(
												'input[name="redEnvelopeType"]:checked')
												.val()
									}
								}
								if ($('input[name="redEnvelopeType"]:checked')
										.val() == '1') {
									data = {
										"totalPrice" : $("#totalPrice").val(),
										"number" : $("#number").val(),
										"createDate" : $('#createDate').val(),
										"expirationDate" : $('#expirationDate').val(),
										"createTime" : $('#createTime').val(),
										"amountAllocated" : $(
												'#amountAllocated').val(),
										"redEnvelopeType" : $(
												'input[name="redEnvelopeType"]:checked')
												.val()
									}
								}
								$
										.ajax({
											type : "POST",
											url : '<spring:url value="/headquarters/redEnvelope/doSaveRedEnvelope" />',
											data : data,
											dataType : "json",
											async : false,
											success : function(data) {
												if ("保存成功" == data.body) {
													layer.msg('保存成功', {
														icon : 1
													});
													window.location.href = '<spring:url value="/headquarters/redEnvelope/doEnRedEnvelopeList" />';
												} else {
													layer.msg('保存失败', {
														icon : 6
													});
													return;
												}
											}
										});

							});

		});

		function random() {
			$("#randomPrice").show();
		}
		function fixedAllocation() {
			$("#randomPrice").hide();
		}
		function generate() {
			var data = {};
			var bootstrapValidator = $("#submitForm")
					.data('bootstrapValidator');
			bootstrapValidator.validate();
			if (bootstrapValidator.isValid()) {
				if ($('#createDate').val() == null
						|| $('#createDate').val() == "") {
					top.layer.alert("发布时间不能为空！", {
						icon : 2
					});
					return;
				}
				if ($('#number').val() == "0") {
					top.layer.alert("红包个数至少一个！", {
						icon : 2
					});
					return;
				}
				if ($('input[name="redEnvelopeType"]:checked').val() == '0') {
					var reg = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
					if ($('#minPrice').val() == null
							|| $('#minPrice').val() == "") {
						top.layer.alert("最小金额不能为空！", {
							icon : 2
						});
						return;
					}
					if (!reg.test($('#minPrice').val())) {
						top.layer.alert("最小金额格式有误，正确格式为0.00/00！", {
							icon : 2
						});
						return;
					}
					if ($('#maxPrice').val() == null
							|| $('#maxPrice').val() == "") {
						top.layer.alert("最大金额不能为空！", {
							icon : 2
						});
						return;
					}
					if (!reg.test($('#maxPrice').val())) {
						top.layer.alert("最大金额格式有误，正确格式为0.00/00！", {
							icon : 2
						});
						return;
					}
					if (Number($('#maxPrice').val()) < Number($('#minPrice')
							.val())) {
						top.layer.alert("最小金额不能大于最大金额！", {
							icon : 2
						});
						return;
					}
					var a = Number($('#totalPrice').val())
							/ Number($('#number').val());
					if (Number($('#maxPrice').val()) < Number(a)) {
						top.layer.alert("最大金额不能小于平均金额！", {
							icon : 2
						});
						return;
					}
					if (Number($('#minPrice').val()) > Number(a)) {
						top.layer.alert("最小金额不能大于平均金额！", {
							icon : 2
						});
						return;
					}
					data = {
						"totalPrice" : $("#totalPrice").val(),
						"number" : $("#number").val(),
						"minPrice" : $('#minPrice').val(),
						"maxPrice" : $('#maxPrice').val(),
						"redEnvelopeType" : $(
								'input[name="redEnvelopeType"]:checked').val()
					}

				}
				if ($('input[name="redEnvelopeType"]:checked').val() == '1') {
					data = {
						"totalPrice" : $("#totalPrice").val(),
						"number" : $("#number").val(),
						"redEnvelopeType" : $(
								'input[name="redEnvelopeType"]:checked').val()
					}
				}
				$
						.ajax({
							type : "POST",
							url : '<spring:url value="/headquarters/redEnvelope/doGenerateRedEnvelope" />',
							data : data,
							dataType : "json",
							async : false,
							success : function(data) {
								if (data.body == "您输入的红包金额和红包个数不能均分，请重新输入！") {
									top.layer.alert(data.body, {
										icon : 2
									});
									return;
								} else {
									$('#totalPrice').attr("disabled", true);
									$('#number').attr("disabled", true);
									$('#redEnvelopeType').attr("disabled", true);
									$('#redEnvelopeType1').attr("disabled", true);
									$('#minPrice').attr("disabled", true);
									$('#maxPrice').attr("disabled", true);
									$('#createDate').attr("disabled", true);
									$('#createTime').attr("disabled", true);
									$("#tb_board").html("");
									$("#amountAllocated").val(data.body);
									
		                            var a=data.body.split(",");
		                            var num=10-(a.length%10);
		                            if(a.length%10==0){
		                                for(var i =0; i < a.length; i++){
		                                    var liHtml="<li class=tab"+a[i]+">"+a[i]+"</li>";
		                                    $('ul').append(liHtml); 
		                                }
		                            }else{
		                                for(var i =0; i < a.length+num; i++){
		                                    if(i<a.length){
		                                         var liHtml="<li class=tab"+a[i]+">"+a[i]+"</li>";
		                                    }else{
		                                     var liHtml="<li class=tab"+a[i]+"></li>";
		                                    }
		                                    $('ul').append(liHtml); 
		                                } 
		                            }
		                             
								}
							}
						});
			}
		}
		function update() {
			$('#totalPrice').attr("disabled", false);
			$('#number').attr("disabled", false);
			$('#redEnvelopeType').attr("disabled", false);
			$('#redEnvelopeType1').attr("disabled", false);
			$('#minPrice').attr("disabled", false);
			$('#maxPrice').attr("disabled", false);
			$('#createDate').attr("disabled", false);
			$('#createTime').attr("disabled", false);
			$("#tb_board").html("");
			$("#amountAllocated").val();
		}
	</script>
</body>
</html>
