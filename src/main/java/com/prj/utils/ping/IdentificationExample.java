package com.prj.utils.ping;

import java.util.HashMap;
import java.util.Map;

import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.RateLimitException;
import com.pingplusplus.model.Identification;

/**
 *
 * 身份证银行卡信息认证接口参考文档：https://www.pingxx.com/api#身份证银行卡信息认证接口
 *
 * 该实例演示如何调用身份证银行卡信息认证接口
 *
 */
public class IdentificationExample {


    public static void runDemos() {
        IdentificationExample eventExample = new IdentificationExample();
        System.out.println("------- 认证身份证信息 -------");
        eventExample.identifyIdCard();

        System.out.println("------- 认证银行卡信息 -------");
      // String msg= eventExample.identifyBankCard();
    }

    /**
     * 认证身份证信息
     *
     * 参考文档：https://www.pingxx.com/api#请求认证接口
     */
    public void identifyIdCard() {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("app", PayConfiger.appId);
            params.put("type", "id_card"); // 身份证信息或者银行卡信息串，取值范围: "id_card"（身份证信息串）；"bank_card"（银行卡信息串）。
            Map<String, String> data = new HashMap<String, String>();
            data.put("id_name", "张三");
            data.put("id_number", "320291198811110000");
            params.put("data", data);
            Identification result = Identification.identify(params);
            if (result.getResultCode() == 0) {
                System.out.println("身份认证通过");
            } else {
                System.out.println(result.getResultCode());
                System.out.println(result.getMessage());
            }
            System.out.println(result);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
    }

    /**
     * 认证银行卡信息
     *
     * 参考文档：https://www.pingxx.com/api#请求认证接口
     */
    public String identifyBankCard(String bankName,String bankNum,String idCard) {
    	String msg="";
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("app", PayConfiger.appId);
            params.put("type", "bank_card"); // 身份证信息或者银行卡信息串，取值范围: "id_card"（身份证信息串）；"bank_card"（银行卡信息串）。
            Map<String, String> data = new HashMap<String, String>();
            data.put("id_name", bankName);
            data.put("id_number", idCard);
            data.put("card_number", bankNum);
            params.put("data", data);
            Identification result = Identification.identify(params);
            if (result.getResultCode() == 0) {
                /*System.out.println("银行卡信息认证通过");*/
            	msg="0";
            } else {
                msg="1";
            }
            System.out.println(result);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
