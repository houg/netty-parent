package com.ganghou.netty.common.constants;

/**
 * @Description: 错误编码
 * @author hougang_ahut@163.com
 * @date 2021年5月12日
 * @version V1.0
 */
public enum ErrorCode {
	

    OK(0, "success"),
    FAIL(1000, "fail"),
    ERROR(5000, "error");
	
	private int code;
    private String message;

    ErrorCode() {
    }

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorCode getResultEnum(int code) {
        for (ErrorCode type : ErrorCode.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return ERROR;
    }

    public static ErrorCode getResultEnum(String message) {
        for (ErrorCode type : ErrorCode.values()) {
            if (type.getMessage().equals(message)) {
                return type;
            }
        }
        return ERROR;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
