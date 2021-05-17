package com.ganghou.netty.common.channel;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;

public class ChannelCache implements Serializable{
	
	private static final long serialVersionUID = -6664665455113540317L;
	
	private final static Map<String, Channel> channels = new ConcurrentHashMap<String, Channel>(); // <dId, channel>
	
	public static void put(String dId, Channel channel) {

		channels.put(dId, channel);

	}

	public static void remove(String dId) {
		if (!channels.containsKey(dId)) {
			return;
		}
		channels.remove(dId);
	}
	
	public static Map<String, Channel> getChannels(){
		return channels;
	}
	
	public static Channel getChannel(String dId){
		return channels.get(dId);
	}
	
	public static void clear(){
    	channels.clear();
	}

}
