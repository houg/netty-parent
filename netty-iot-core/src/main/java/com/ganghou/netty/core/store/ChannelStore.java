package com.ganghou.netty.core.store;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ganghou.netty.common.utils.NetUtils;

import io.netty.channel.Channel;

/**
 * @Description: 存储设备绑定通道
 * @author hougang_ahut@163.com
 * @date 2021年5月7日
 * @version V1.0
 */
public class ChannelStore implements Serializable {

	private static final long serialVersionUID = 518157017007372664L;

	private final static Map<String, Channel> channels = new ConcurrentHashMap<String, Channel>(); // <ip:port, channel>

	private final static Map<String, String> bindChannels = new ConcurrentHashMap<String, String>(); // <dId, ip:port>

	public static void put(InetSocketAddress socketAddress, Channel channel) {

		channels.put(NetUtils.toAddressString(socketAddress), channel);

	}

	public static void remove(InetSocketAddress socketAddress) {
		String address = NetUtils.toAddressString(socketAddress);
		if (!channels.containsKey(address)) {
			return;
		}
		channels.remove(address);
		//移除绑定关系
		bindChannels.entrySet().removeIf(bind->bind.getValue().equals(address));
	}
	
	public static void bind(String dId,InetSocketAddress socketAddress){
		
		bindChannels.put(dId, NetUtils.toAddressString(socketAddress));
	}
	
	public static Map<String, Channel> getChannels(){
		return channels;
	}
	
	public static Channel getChannel(InetSocketAddress socketAddress){
		return channels.get(NetUtils.toAddressString(socketAddress));
	}
	
	public static Channel getChannel(String ip, int port){
		StringBuffer address = new StringBuffer(ip).append(":").append(port);
		return channels.get(address.toString());
	}
	
	public static Channel getChannel(String dId){
		String address = bindChannels.get(dId);
		if(address==null) {
			return null;
		}
		return channels.get(address);
	}
	
    public static void clear(){
    	channels.clear();
		bindChannels.clear();
	}

}
