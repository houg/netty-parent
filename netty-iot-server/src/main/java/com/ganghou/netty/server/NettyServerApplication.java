package com.ganghou.netty.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ganghou.netty.core.server.NettyServer;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan("com.ganghou.netty")
@Slf4j
public class NettyServerApplication implements CommandLineRunner{

	public static void main(String[] args) {

		SpringApplication.run(NettyServerApplication.class, args);

	}
	@Autowired
	private NettyServer nettyServer;

	@Override
	public void run(String... args) throws Exception {
		try {
			nettyServer.start();
		} catch (Throwable e) {
			log.error("启动异常",e);
		}finally {
			try {
				nettyServer.shutdown();
			} catch (Throwable e) {
				log.error("关闭异常",e);
			}
		}
		
	}

}
