package com.ganghou.netty.core.configuration;

import java.net.InetAddress;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ganghou.netty.common.model.URL;
import com.ganghou.netty.core.codec.NettyCodecFactory;
import com.ganghou.netty.core.codec.NettyStringCodecFactory;
import com.ganghou.netty.core.server.NettyServer;

@Configuration
@EnableConfigurationProperties(NettyServerProperties.class)
//@ConditionalOnProperty(prefix = "spring.data.redis.repositories", name = "enabled", havingValue = "true",matchIfMissing = true)
public class NettyServerAutoConfiguration {
	

	
	@Bean
	@ConditionalOnMissingBean
	public NettyServer initServer(NettyServerProperties nettyServerProperties,NettyCodecFactory nettyCodecFactory) throws Exception {
		String ip = InetAddress.getLocalHost().getHostAddress();
		URL url = new URL(nettyCodecFactory.protocol().name(),ip,nettyServerProperties.getPort());
		NettyServer nettyServer =  new NettyServer(url,nettyServerProperties.getWorkerThreadNum(),nettyCodecFactory);
		return nettyServer;
		
	}
	
	@Bean
	@ConditionalOnMissingBean
	public NettyCodecFactory initDefaultNettyCodecFactory() {
		return new NettyStringCodecFactory();
	}

}
