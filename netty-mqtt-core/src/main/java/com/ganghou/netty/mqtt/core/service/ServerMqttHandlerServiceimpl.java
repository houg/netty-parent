package com.ganghou.netty.mqtt.core.service;

import com.ganghou.netty.common.channel.ChannelCache;
import com.ganghou.netty.common.utils.StringUtils;

import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttConnAckMessage;
import io.netty.handler.codec.mqtt.MqttConnAckVariableHeader;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttConnectPayload;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.handler.codec.mqtt.MqttSubscribeMessage;
import io.netty.handler.codec.mqtt.MqttUnsubscribeMessage;
import io.netty.handler.timeout.IdleStateEvent;

public class ServerMqttHandlerServiceimpl implements MqttHandlerService {

	@Override
	public boolean login(Channel channel, MqttConnectMessage mqttConnectMessage) {
		MqttConnectPayload payload = mqttConnectMessage.payload();
		String dId = payload.clientIdentifier();
		if (StringUtils.isBlank(dId)) {
			MqttConnectReturnCode connectReturnCode = MqttConnectReturnCode.CONNECTION_REFUSED_IDENTIFIER_REJECTED;
			connectBack(channel, connectReturnCode);
			return false;
		}
		// 缓存设备与通道绑定关系
		ChannelCache.put(dId, channel);
		return false;
	}

	private void connectBack(Channel channel, MqttConnectReturnCode connectReturnCode) {
		MqttConnAckVariableHeader mqttConnAckVariableHeader = new MqttConnAckVariableHeader(connectReturnCode, true);
		MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE,
				false, 0x02);
		MqttConnAckMessage connAck = new MqttConnAckMessage(mqttFixedHeader, mqttConnAckVariableHeader);
		channel.writeAndFlush(connAck);
	}

	@Override
	public void publish(Channel channel, MqttPublishMessage mqttPublishMessage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void subscribe(Channel channel, MqttSubscribeMessage mqttSubscribeMessage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pong(Channel channel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unsubscribe(Channel channel, MqttUnsubscribeMessage mqttMessage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnect(Channel channel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doTimeOut(Channel channel, IdleStateEvent evt) {
		// TODO Auto-generated method stub

	}

}
