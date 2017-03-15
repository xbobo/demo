package com.demo.app.conf;


/**
 * 数据库常量类
 * <br/>
 * 该类存放数据库字段值映射常量
 */
public class DBConstant {
	
	/**
	 * es相关配置
	 */
	public static class EsConfig {
		public static final String INDEX = "eduserver";
		public static final String TYPE = "eduinfo";
	}
	
	/**
	 * es 载体
	 */
	public static class Vector {
		public static final String WECHAT = "微信";
		public static final String WEBO = "微博";
		public static final String QQGROUP = "QQ群";
		public static final String NEWS = "新闻";
	}
	
	/**
	 * es 情感
	 */
	public static class Emotion {
		/**正面*/
		public static final String POSITIVE = "positive";
		
		/**负面*/
		public static final String NEGATIVE = "negative";
		
		/**中性*/
		public static final String NEUTRAL = "neutral";
	}
	
	/**
	 * 预警规则状态
	 */
	public static class WarnStatus {
		/**预警中*/
		public static final int ON = 0;
		
		/**取消预警*/
		public static final int OFF = 1;
	}
	
	/**
	 * 预警信息是否显示
	 */
	public static class IsShow {
		/**显示*/
		public static final int ON = 0;
		
		/**不显示*/
		public static final int OFF = 1;
	}
	
	/**
	 * 认证状态
	 */
	public static class AuthcStatus {
		/** 未认证 */
		public static final String NO = "0";

		/** 已认证 */
		public static final String YES = "1";

		/** 未认证 */
		public static final String NO_ZN = "未认证";

		/** 已认证 */
		public static final String YES_ZN = "已认证";

		/**
		 * 解析状态 返回 0,1.
		 * 
		 * @param status
		 * @return
		 */
		public static String parseStatus(String status) {
			if (status.equals(AuthcStatus.YES_ZN)) {
				status = AuthcStatus.YES;
			} else if (status.equals(AuthcStatus.NO_ZN)) {
				status = AuthcStatus.NO;
			} else {
				status = AuthcStatus.NO;
			}
			return status;
		}
	}

}
