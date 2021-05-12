package com.ganghou.netty.core.server;

import java.net.InetSocketAddress;

import com.ganghou.netty.common.model.URL;
import com.ganghou.netty.core.codec.NettyCodecFactory;
import com.ganghou.netty.core.codec.NettyStringCodecFactory;
import com.ganghou.netty.core.handler.NettyServerHandler;
import com.ganghou.netty.core.store.ChannelStore;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/*
 * netty服务端
 */
@Slf4j
@Data
public class NettyServer extends AbstractServer{

	private ServerBootstrap bootstrap = null;

	private Channel channel = null;

	private EventLoopGroup bossGroup = null;

	private EventLoopGroup workerGroup = null;

	private int workerThreadNum = 3;

	private URL url;
	
	private NettyCodecFactory nettyCodecFactory;

	private InetSocketAddress bindAddress;

	public NettyServer(URL url,NettyCodecFactory nettyCodecFactory) {
		this(url,3,null);
	}
	
	public NettyServer(URL url,int workerThreadNum,NettyCodecFactory nettyCodecFactory) {
		super();
		this.url = url;
		this.workerThreadNum = workerThreadNum;
		this.bindAddress = new InetSocketAddress(url.getHost(), url.getPort());
		this.nettyCodecFactory = nettyCodecFactory;
	}
    
	@Override
	public void start() throws Throwable {
		bootstrap = new ServerBootstrap();

		bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("NettyServerBoss", true));
		workerGroup = new NioEventLoopGroup(this.workerThreadNum, new DefaultThreadFactory("NettyServerWorker", true));

		bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
				.childOption(ChannelOption.SO_REUSEADDR, Boolean.TRUE)
				.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel ch) throws Exception {
						
						NettyServerHandler nettyServerHandler = new NettyServerHandler(getUrl());
						ch.pipeline().addLast("decoder", nettyCodecFactory.decoder()).addLast("encoder", nettyCodecFactory.encoder())
								.addLast("handler", nettyServerHandler);
					}
				});
		// bind
		ChannelFuture channelFuture = bootstrap.bind(getBindAddress()).syncUninterruptibly();
		this.channel = channelFuture.channel();
		log.info("netty服务端[{},{}:{}]启动成功",url.getProtocol(),url.getHost(),url.getPort());
		channelFuture.channel().closeFuture().syncUninterruptibly();
		
	}
    
	@Override
	public void shutdown() throws Throwable {
		try {
			if (channel != null&&channel.isActive()) {
				channel.close();
			}
		} catch (Throwable e) {
			log.warn(e.getMessage(), e);
		}
		//关闭通道
		ChannelStore.getChannels().forEach((key, channel) -> {
			try {
				if(channel.isActive()) {
					channel.close();
				}
			} catch (Throwable e) {
				log.warn(e.getMessage(), e);
			}
		});
		try {
			if (bootstrap != null) {
				bossGroup.shutdownGracefully();
				workerGroup.shutdownGracefully();
			}
		} catch (Throwable e) {
			log.warn(e.getMessage(), e);
		}
		ChannelStore.clear();
	}

}
