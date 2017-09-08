package com.prj.utils;

import cn.jpush.api.JPushClient;//
import cn.jpush.api.common.resp.APIConnectionException;//
import cn.jpush.api.common.resp.APIRequestException;//
import cn.jpush.api.push.PushResult;//
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;//
import cn.jpush.api.push.model.Platform;//
import cn.jpush.api.push.model.PushPayload;//
import cn.jpush.api.push.model.audience.Audience;//
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;//









public class JPushClientUtil {

    // demo App defined in resources/jpush-api.conf 
//	private static final String appKey ="4c94a590d2c376371c392ec6";
//	private static final String masterSecret = "0d347897744dce8af3740134";
//	private static final String appKey ="95f1cceebae91e96d067d07c";
//	private static final String masterSecret = "03e1e99fbb1707506f72e21e";
	
	
//	private static final String appKey ="fc699e368d2a42736cc55cf5";
//	private static final String masterSecret = "8581b72b428c0e185be7c97f";
	
//	  private static final String  appKey ="92adf5da9f13f446902d8361";
//	    private static final String   masterSecret = "c5527f75db69b8ddd3499c45";
	
	
//	private static final String appKey ="92adf5da9f13f446902d8361";
//	private static final String masterSecret = "c5527f75db69b8ddd3499c45";
//	private static final String appKeyCap ="6e2b4ad4bc6b7814135eef18";
//	private static final String masterSecretCap = "936fc106195c2bb26efcf5f7";
	
	
	private static final String appKey ="233b733a6b0becc9de322bca";
	private static final String masterSecret = "2b068619e9a84c2de7706a5e";
	public static final String TITLE = "标题";
    public static final String ALERT = "警告";
    public static final String MSG_CONTENT = "内容122211";
    public static final String REGISTRATION_ID = "041afde4789";
    public static final String TAG = "tag_api";

	public static void main(String[] args) {
		//pushMessage("爱迪达");
		//pushMessage("系统消息","你好","3de8506e9a6d52e569622a96c09b862a7d8b4361");
		//pushMessageCap(TITLE,MSG_CONTENT,"0a1dfa75d12");121c83f76027c6bfa86
	//pushMessage("afwewa","用户在其它手机上登录，请确认是否是本人操作。","121c83f76027c6bfa86");
	//pushMessage("afwewa","用户在其它手机上登录，请确认是否是本人操作。","141fe1da9eaa12294cb");
	//pushMessage("qeq","用户在其它手机上登录，请确认是否是本人操作。","161a3797c80bf9e4887");
	pushMessage("系统消息",
			"用户在其它手机上登录，请确认是否是本人操作。",
			"170976fa8aba5708d15");
		//pushMessage("系统消息");
	
	}
	public static void pushMessage(String content) {
	 JPushClient jpushClient = new JPushClient(masterSecret, appKey);
	      // JPushClient jpushClient = new JPushClient("11ed32d43d6da996fd990532", "3817014794ab0b3fb3f5b573");
	        PushPayload payload =buildPushObject_all_all_alert(content);// buildPushObject_android_and_ios(title,content,registration_id);
	        try {
	            PushResult result = jpushClient.sendPush(payload);
	            System.out.println(result);
	           // System.out.println("q=");
	        } catch (APIConnectionException e) {
	        	e.printStackTrace();
	        } catch (APIRequestException e) {
	           e.printStackTrace();
	        }
		}
	public static void pushMessage(String title,String content,String registration_id) {
	       JPushClient jpushClient = new JPushClient(masterSecret, appKey);
//	        PushPayload payload =buildPushObject_all_all_alert();//
	       PushPayload payload =  buildPushObject_android_and_ios(title,content,registration_id);
	        try {
	            PushResult result = jpushClient.sendPush(payload);
	            System.out.println(result);
	        } catch (APIConnectionException e) {
	        	e.printStackTrace();
	            
	        } catch (APIRequestException e) {
	           e.printStackTrace();
	        }
		}

	
	public static void pushMessageCap(String title,String content,String registration_id) {
       // JPushClient jpushClient = new JPushClient(masterSecretCap, appKeyCap);
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        PushPayload payload = buildPushObject_android_and_ios(title,content,registration_id);
        
        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println(result);
        } catch (APIConnectionException e) {
        	e.printStackTrace();
            
        } catch (APIRequestException e) {
           e.printStackTrace();
        }
	}
	
	
//	public static void pushMessageCap(String title,String content,String registration_id) {
//        JPushClient jpushClient = new JPushClient(masterSecretCap, appKeyCap);
//        
//        PushPayload payload = buildPushObject_android_and_ios(title,content,registration_id);
//        
//        try {
//            PushResult result = jpushClient.sendPush(payload);
//            System.out.println(result);
//        } catch (APIConnectionException e) {
//        	e.printStackTrace();
//            
//        } catch (APIRequestException e) {
//           e.printStackTrace();
//        }
//	}
	public static PushPayload buildPushObject_all_all_alert(String content) {
	    return PushPayload.alertAll(content);
	}
	
    public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias("alias1"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }
    
    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }
    
    public static PushPayload buildPushObject_android_and_ios(String title,String content,String registration_id) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())//Platform.android_ios()
                .setAudience(Audience.registrationId(registration_id))
                .setNotification(Notification.newBuilder()
                		.setAlert(content)
                		.addPlatformNotification(AndroidNotification.newBuilder()
                				.setTitle(title).build())
                		.addPlatformNotification(IosNotification.newBuilder()
                				.setSound("happy")
                				.incrBadge(1)
                				//.addExtra("extra_key", "extra_value")
                				.build())
                		.build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        //.addExtra("from", "JPush")
                        .build())
                .setOptions(Options.newBuilder()
                         .setApnsProduction(false)//.setApnsProduction(false)
                         .build())
                .build();
    }
    
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                 .setMessage(Message.content(MSG_CONTENT))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }
    
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
    
    
}

