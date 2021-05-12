package com.ganghou.netty.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "netty.iot")
@Data
public class NettyServerProperties {

	private int port;

	private String path;

	private int workerThreadNum;
}
