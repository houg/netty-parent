package com.ganghou.netty.core.codec;

import com.ganghou.netty.common.codec.NettyCodecFactory;
import com.ganghou.netty.common.constants.ProtocolType;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyDefaultCodecFactory implements NettyCodecFactory {

	@Override
	public ChannelHandler decoder() {
		return new StringDecoder();
	}

	@Override
	public ChannelHandler encoder() {
		return new StringEncoder();
	}

	@Override
	public ProtocolType protocol() {
		return ProtocolType.string;
	}

}
