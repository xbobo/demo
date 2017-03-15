package com.demo.common.util;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.app.conf.SMSConfig;
/** 
* 类说明:短信验证码工具类
*/
public class SMSUtils {
	private static Logger logger = LoggerFactory.getLogger(SMSUtils.class);

	/**
	 * 短信发送信息
	 * 
	 * @return String
	 */
	public static String getContent(String code) {
		String content = MessageFormat.format(SMSConfig.CONTENT, code);
		return content;
	}

	/**
	 * 生成短信验证码
	 * 
	 * @return code
	 */
	public static String getCode() {
		int msgCode = (int) (Math.random() * 9000) + 1000;
		return String.valueOf(msgCode);
	}

	/**
	 * 发送短信
	 * 
	 * @param phone
	 *            短信接收人
	 * @param content
	 *            短信内容
	 * @return boolean true:成功;false:失败
	 */
	public static String sendSMS(String phone, String code) {
		String content = getContent(code);
		String ex = SMSConfig.EX;
		ex = StringUtils.isBlank(ex) ? null : ex;
		try {
			String result = HttpSender.batchSend(SMSConfig.URL, SMSConfig.USER, SMSConfig.PASSWORD, phone, content,
					SMSConfig.RD, ex);
			result = result.split(",")[1];
			result = result.split("\n")[0];
			logger.info("===  send sms result {}  ===", result);
			if ("0".equals(result)) {
				return "短信发送成功";
			} else if ("101".equals(result)) {
				return "无此用户";
			} else if ("102".equals(result)) {
				return "密码错误";
			} else if ("103".equals(result)) {
				return "提交过快（提交速度超过流速限制）";
			} else if ("104".equals(result)) {
				return "系统忙（因平台侧原因，暂时无法处理提交的短信）";
			} else if ("105".equals(result)) {
				return "敏感短信（短信内容包含敏感词）";
			} else if ("106".equals(result)) {
				return "消息长度错（>536或<=0）";
			} else if ("107".equals(result)) {
				return "包含错误的手机号码";
			} else if ("108".equals(result)) {
				return "手机号码个数错（群发>50000或<=0）";
			} else if ("109".equals(result)) {
				return "无发送额度（该用户可用短信数已使用完）";
			} else if ("110".equals(result)) {
				return "不在发送时间内";
			} else if ("113".equals(result)) {
				return "extno格式错（非数字或者长度不对）";
			} else if ("116".equals(result)) {
				return "签名不合法或未带签名（用户必须带签名的前提下）";
			} else if ("117".equals(result)) {
				return "IP地址认证错,请求调用的IP地址不是系统登记的IP地址";
			} else if ("118".equals(result)) {
				return "用户没有相应的发送权限（账号被禁止发送）";
			} else if ("119".equals(result)) {
				return "用户已过期";
			} else if ("120".equals(result)) {
				return "违反放盗用策略(日发限制) --自定义添加";
			} else if ("121".equals(result)) {
				return "必填参数。是否需要状态报告，取值true或false";
			} else if ("122".equals(result)) {
				return "5分钟内相同账号提交相同消息内容过多";
			} else if ("123".equals(result)) {
				return "发送类型错误";
			} else if ("124".equals(result)) {
				return "白模板匹配错误";
			} else if ("125".equals(result)) {
				return "匹配驳回模板，提交失败";
			} else if ("126".equals(result)) {
				return "审核通过模板匹配错误";
			}
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
		return null;
	}
}
