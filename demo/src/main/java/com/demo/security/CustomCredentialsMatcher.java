package com.demo.security;

import java.security.interfaces.RSAPrivateKey;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.subject.Subject;

import com.demo.entity.UserBase;
import com.demo.repository.UserBaseRepository;

/**
 * 自定义校验密码类
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	
	protected CustomCredentialsMatcher(UserBaseRepository userRespository) {
		this.userRespository = userRespository;
	}
	
	private UserBaseRepository userRespository;

	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Object tokenCredentials = getUserPassword(token);
		Object accountCredentials = getCredentials(info);
		return equals(tokenCredentials, accountCredentials);
	}

	public String getUserPassword(UsernamePasswordToken token) {
		String pass = String.valueOf(token.getPassword());
		Subject currentUser = SecurityUtils.getSubject();
		String password = null;
		try {
			// 获取当前用户的私钥
			Object priKey = currentUser.getSession().getAttribute("privateKey");
			if (priKey == null) {
				return null;
			}
			String descrypedPwd = RSAUtils.decryptByPrivateKey(pass, (RSAPrivateKey) priKey);
			descrypedPwd = new StringBuilder(descrypedPwd).reverse().toString();
			UserBase user = userRespository.findByUserAccount(token.getUsername());
			byte[] salt = Encodes.decodeHex(user.getSalt());
			byte[] hashPassword = Digests.sha1(descrypedPwd.getBytes(), salt,Encodes.HASH_INTERATIONS);
			password = Encodes.encodeHex(hashPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return password;
	}

}
