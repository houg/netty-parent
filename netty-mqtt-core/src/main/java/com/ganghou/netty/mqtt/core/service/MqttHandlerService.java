package com.ganghou.netty.mqtt.core.service;

import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttSubscribeMessage;
import io.netty.handler.codec.mqtt.MqttUnsubscribeMessage;
import io.netty.handler.timeout.IdleStateEvent;

public interface MqttHandlerService {
	
	boolean login(Channel channel, MqttConnectMessage mqttConnectMessage);

    void  publish(Channel channel, MqttPublishMessage mqttPublishMessage);

    void subscribe(Channel channel, MqttSubscribeMessage mqttSubscribeMessage);

    void pong(Channel channel);

    void unsubscribe(Channel channel, MqttUnsubscribeMessage mqttMessage);

    void disconnect(Channel channel);

    void doTimeOut(Channel channel, IdleStateEvent evt);

}
