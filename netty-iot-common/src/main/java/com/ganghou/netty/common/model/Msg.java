package com.ganghou.netty.common.model;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @Description: 基础消息
 * @author hougang_ahut@163.com
 * @date 2021年5月7日
 * @version V1.0
 */
@Data
@ToString
public class Msg implements Serializable {

	private static final long serialVersionUID = 5238734724027095504L;
	
	/**
	 * 设备ID
	 */
	private String did;
	
	/**
	 * 消息协议
	 */
	private String protocol;
	
	/*
	 * 消息类型  0-上线消息  1-心跳消息  2-下线消息 3-
	 */
	private String type;
	
	/**
	 * 时间
	 */
	private long timestamp;
	
	/*
	 * 消息体
	 */
	private Object body;

}
