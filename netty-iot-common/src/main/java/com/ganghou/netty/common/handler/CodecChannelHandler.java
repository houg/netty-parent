package com.ganghou.netty.common.handler;

import com.ganghou.netty.common.model.Msg;

import io.netty.channel.ChannelHandlerContext;

/**
 * @Description: 服务端处理器
 * @author hougang_ahut@163.com
 * @date 2021年5月15日
 * @version V1.0
 */
public interface CodecChannelHandler {
	
	/**
	 * 连接
	 */
	void connected(ChannelHandlerContext ctx) throws Exception;
	/**
	 * 断开连接
	 */
	void disconnected(ChannelHandlerContext ctx) throws Exception;
    /*
     * 接收数据
     */
	Msg received(ChannelHandlerContext ctx, Object message) throws Exception;
    /*
     * 异常发生
     */
	void caught(ChannelHandlerContext ctx, Throwable exception) throws Exception;
	
	

}
