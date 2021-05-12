package com.ganghou.netty.common.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * 服务注册信息
 */
@Data
@AllArgsConstructor
public class URL implements Serializable {

	private static final long serialVersionUID = -5941048620159963117L;
	
	 private String protocol;

	 private  String host;
	 
	 private  int port;

	 private  String path;

	public URL(String protocol, String host, int port) {
		super();
		this.protocol = protocol;
		this.host = host;
		this.port = port;
	}
	 
	 

}
