package com.ganghou.netty.common.model;

import java.io.Serializable;

import com.ganghou.netty.common.constants.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultBody<T> implements Serializable {

	private static final long serialVersionUID = -6037985598898113791L;
	/*
	 * 响应编码
	 */
	private int code;
	/*
	 * 提示消息
	 */
	private String message;
	/*
	 * 响应时间
	 */
	private long timestamp = System.currentTimeMillis();
	/*
	 * 响应数据
	 */
	private T data;

	public ResultBody() {
		super();
	}

	public ResultBody(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public static ResultBody ok() {
        return new ResultBody().code(ErrorCode.OK.getCode()).msg(ErrorCode.OK.getMessage());
    }

    public static ResultBody failed() {
        return new ResultBody().code(ErrorCode.FAIL.getCode()).msg(ErrorCode.FAIL.getMessage());
    }
    
    public ResultBody code(int code) {
        this.code = code;
        return this;
    }

    public ResultBody msg(String message) {
        this.message = message;
        return this;
    }

    public ResultBody data(T data) {
        this.data = data;
        return this;
    }

}
