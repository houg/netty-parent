package com.ganghou.netty.common.codec;

import com.ganghou.netty.common.constants.ProtocolType;

import io.netty.channel.ChannelHandler;

public interface NettyCodecFactory {
	
	ProtocolType protocol();
	
	ChannelHandler decoder();
	
	ChannelHandler encoder();

}
