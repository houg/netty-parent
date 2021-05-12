package com.ganghou.netty.core.handler;

import java.net.InetSocketAddress;

import com.ganghou.netty.common.constants.MsgType;
import com.ganghou.netty.common.model.Msg;
import com.ganghou.netty.common.model.URL;
import com.ganghou.netty.core.store.ChannelStore;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 主处理handler
 * @author hougang_ahut@163.com
 * @date 2021年5月8日
 * @version V1.0
 */
@Data
@Slf4j
public class NettyServerHandler extends ChannelDuplexHandler{

	private final URL url;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
		ChannelStore.put(socketAddress, ctx.channel());
		
		log.info("客户端{}:{}连接成功",socketAddress.getAddress().getHostAddress(),socketAddress.getPort());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
		ChannelStore.remove(socketAddress);
		
		
		log.info("客户端{}:{}连接关闭",socketAddress.getAddress().getHostAddress(),socketAddress.getPort());
		
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
		Msg msg  = (Msg) obj;
		if(msg==null) {
			log.error("读取消息为空");
		}
		//如果是注册消息
		if(msg.getType().equals(MsgType.ONLINE.name())) {
			InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
			ChannelStore.bind(msg.getDid(), socketAddress);
			log.info("绑定客户端设备:{}",msg.getDid());
		}

	}
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		
		super.write(ctx, msg, promise);

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	
	}
	
   
	
	

}
