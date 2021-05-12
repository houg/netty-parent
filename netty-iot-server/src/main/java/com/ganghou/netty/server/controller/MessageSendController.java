package com.ganghou.netty.server.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ganghou.netty.common.model.Msg;
import com.ganghou.netty.common.model.ResultBody;
import com.ganghou.netty.core.store.ChannelStore;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/msg/send")
@Slf4j
public class MessageSendController {
	
	@RequestMapping(value="/one",method= {RequestMethod.POST})
	public ResultBody<?> send(@RequestBody Msg msg) {
		Channel channel = ChannelStore.getChannel(msg.getDid());
		if(channel==null) {
			log.error("设备离线状态，{}",msg.getDid());
			return ResultBody.failed().msg("设备离线状态");
		}
		channel.writeAndFlush(msg);
		return ResultBody.ok();
	}
	
	@RequestMapping(value="/all",method= {RequestMethod.POST})
	public ResultBody<?> sendAll(@RequestBody Msg msg) {
		ChannelStore.getChannels().forEach((key, channel) -> {
			if(channel.isActive()) {
				channel.writeAndFlush(msg.toString());
			}
		});
		return ResultBody.ok();
	}

}
