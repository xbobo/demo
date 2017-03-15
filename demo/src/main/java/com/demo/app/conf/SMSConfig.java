package com.demo.app.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SMSConfig {
	@Value("${sms.un}")
	public static String USER;
	@Value("${sms.pw}")
	public static String PASSWORD;
	@Value("${sms.url}")
	public static String URL;
	@Value("${sms.content}")
	public static String CONTENT;
	@Value("${sms.rd}")
	public static String RD;
	@Value("${sms.ex}")
	public static String EX;

}
