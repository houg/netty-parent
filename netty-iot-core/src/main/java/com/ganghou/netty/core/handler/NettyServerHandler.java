package com.ganghou.netty.core.handler;

import java.net.InetSocketAddress;

import com.ganghou.netty.common.constants.MsgType;
import com.ganghou.netty.common.handler.CodecChannelHandler;
import com.ganghou.netty.common.model.Msg;
import com.ganghou.netty.common.model.URL;
import com.ganghou.netty.core.store.ChannelStore;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
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
public class NettyServerHandler extends ChannelInboundHandlerAdapter{

	private final URL url;
	
	private CodecChannelHandler codecChannelHandler;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
		ChannelStore.put(socketAddress, ctx.channel());
		
		codecChannelHandler.connected(ctx);
		log.info("客户端{}:{}连接成功",socketAddress.getAddress().getHostAddress(),socketAddress.getPort());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
		ChannelStore.remove(socketAddress);
		
		codecChannelHandler.disconnected(ctx);
		log.info("客户端{}:{}连接关闭",socketAddress.getAddress().getHostAddress(),socketAddress.getPort());
		
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
		Msg msg = codecChannelHandler.received(ctx, obj);
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
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	
	}
	
   
	
	

}
