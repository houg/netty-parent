package com.ganghou.netty.common.utils;

import java.net.InetSocketAddress;

/**
 * @Description: 工具类
 * @author hougang_ahut@163.com
 * @date 2021年5月7日
 * @version V1.0
 */
public class NetUtils {

	public static String toAddressString(InetSocketAddress address) {
		return address.getAddress().getHostAddress() + ":" + address.getPort();
	}

	public static InetSocketAddress toAddress(String address) {
		int i = address.indexOf(':');
		String host;
		int port;
		if (i > -1) {
			host = address.substring(0, i);
			port = Integer.parseInt(address.substring(i + 1));
		} else {
			host = address;
			port = 0;
		}
		return new InetSocketAddress(host, port);
	}

}
