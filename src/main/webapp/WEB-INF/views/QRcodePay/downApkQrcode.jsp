<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	/*  
	 * 智能机浏览器版本信息:  
	 *  
	 */
	var browser = {
		versions : function() {
			var u = navigator.userAgent, app = navigator.appVersion;
			return {//移动终端浏览器版本信息   
				trident : u.indexOf('Trident') > -1, //IE内核  
				presto : u.indexOf('Presto') > -1, //opera内核  
				webKit : u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核  
				gecko : u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核  
				mobile : !!u.match(/AppleWebKit.*Mobile.*/)
						|| !!u.match(/AppleWebKit/), //是否为移动终端  
				ios : !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端  
				android : u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器  
				iPhone : u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器  
				iPad : u.indexOf('iPad') > -1, //是否iPad  
				webApp : u.indexOf('Safari') == -1
			//是否web应该程序，没有头部与底部  
			};
		}(),
		language : (navigator.browserLanguage || navigator.language)
				.toLowerCase()
	}

	if (browser.versions.ios || browser.versions.iPhone
			|| browser.versions.iPad) {
		//window.location = "https://itunes.apple.com/cn/app/xi-cai-cai-piao/id887115907?mt=8";//ios版本的下载地址  
		document.writeln("正在更新敬请期待");
	} else if (browser.versions.android) {
		//window.location = "http://xxxx.xxx.xx/downapk/xxx.apk"; //android版本的下载地址  
		document.writeln("android");
	}

	document.writeln("语言版本: " + browser.language);
	document.writeln(" 是否为移动终端: " + browser.versions.mobile);
	document.writeln(" ios终端: " + browser.versions.ios);
	document.writeln(" android终端: " + browser.versions.android);
	document.writeln(" 是否为iPhone: " + browser.versions.iPhone);
	document.writeln(" 是否iPad: " + browser.versions.iPad);
	document.writeln(navigator.userAgent);
</script>
</head>
<body>
</html>