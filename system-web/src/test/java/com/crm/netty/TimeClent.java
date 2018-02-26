package com.crm.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.net.SocketAddress;
import java.util.Date;
import java.util.List;

/**
 * Created by jason_moo on 2018/1/24.
 */
public class TimeClent {

    public static void main(String[] args) throws Exception{
        new TimeClent().connect(10083,"127.0.0.1");
    }

    public void connect(int port,String host) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeDecoder(),new TimeClientHandler());
                        }
                    });

            ChannelFuture f = b.connect(host,port);
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }

    }

    static class TimeClientHandler extends ChannelInboundHandlerAdapter{

        public TimeClientHandler() {
        }


        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            UnixTime m = (UnixTime) msg;
            System.out.println(m);
            ctx.close();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
        }
    }



    public static class TimeDecoder extends ReplayingDecoder<Void> {

        @Override
        protected void decode(
                ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
            out.add(new UnixTime(in.readInt()));
        }
    }

}
