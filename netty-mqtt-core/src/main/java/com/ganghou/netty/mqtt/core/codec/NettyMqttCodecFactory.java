package com.ganghou.netty.mqtt.core.codec;

import com.ganghou.netty.common.codec.NettyCodecFactory;
import com.ganghou.netty.common.constants.ProtocolType;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;

/**
 * @Description: mqtt编解码器工厂类
 * @author hougang_ahut@163.com
 * @date 2021年5月15日
 * @version V1.0
 */
public class NettyMqttCodecFactory implements NettyCodecFactory {

	@Override
	public ProtocolType protocol() {
		return ProtocolType.mqtt;
	}

	@Override
	public ChannelHandler decoder() {
		
		return new MqttDecoder();
	}

	@Override
	public ChannelHandler encoder() {
	
		return MqttEncoder.INSTANCE;
	}

}
