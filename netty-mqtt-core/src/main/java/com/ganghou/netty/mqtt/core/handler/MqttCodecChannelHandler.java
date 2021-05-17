package com.ganghou.netty.mqtt.core.handler;

import com.ganghou.netty.common.handler.CodecChannelHandler;
import com.ganghou.netty.common.model.Msg;
import com.ganghou.netty.mqtt.core.service.MqttHandlerService;
import com.ganghou.netty.mqtt.core.service.ServerMqttHandlerServiceimpl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttConnAckMessage;
import io.netty.handler.codec.mqtt.MqttConnAckVariableHeader;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttConnectPayload;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;

public class MqttCodecChannelHandler implements CodecChannelHandler {
	
	private MqttHandlerService mqttHandlerService = new ServerMqttHandlerServiceimpl();

	@Override
	public void connected(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnected(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Msg received(ChannelHandlerContext ctx, Object message) throws Exception {
		MqttMessage mqttMessage  = (MqttMessage) message;
		MqttFixedHeader mqttFixedHeader = mqttMessage.fixedHeader();
		 switch (mqttFixedHeader.messageType()){
		 case CONNECT:
			 MqttConnectMessage mqttConnectMessage = (MqttConnectMessage) mqttMessage;
			 mqttHandlerService.login(ctx.channel(), mqttConnectMessage);
             break;
         case PUBLISH:
            
             break;
         case SUBSCRIBE:
           
             break;
         case PINGREQ:
           
             break;
         case DISCONNECT:
       
             break;
         case UNSUBSCRIBE:
           
             break;
         case PUBACK:
           
             break;
         case PUBREC:
            
             break;
         case PUBREL:
            
             break;
         case PUBCOMP:
            
             break;
         default:
             break;
     }
		return null;
	}

	@Override
	public void caught(ChannelHandlerContext ctx, Throwable exception) throws Exception {
		// TODO Auto-generated method stub

	}
	
	private  void  connectBack(Channel channel,  MqttConnectReturnCode connectReturnCode){
        MqttConnAckVariableHeader mqttConnAckVariableHeader = new MqttConnAckVariableHeader(connectReturnCode, true);
        MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(
                MqttMessageType.CONNACK,false, MqttQoS.AT_MOST_ONCE, false, 0x02);
        MqttConnAckMessage connAck = new MqttConnAckMessage(mqttFixedHeader, mqttConnAckVariableHeader);
        channel.writeAndFlush(connAck);
    }

}
