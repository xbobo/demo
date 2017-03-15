package com.demo.controller;

import com.demo.vo.AjaxResult;

public abstract class BaseController {
	
	public AjaxResult success(Object data) {
		return new AjaxResult().setData(data).setSuccess(true).setStatus(0);
	}
	
	public AjaxResult error(String message) {
		return new AjaxResult().setMessage(message).setSuccess(false).setStatus(1);
	}
}
