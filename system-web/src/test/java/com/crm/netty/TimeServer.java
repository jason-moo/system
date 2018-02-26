package com.crm.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.Date;


/**
 * Created by jason_moo on 2018/1/24.
 */
public class TimeServer {

    public void bind(int port) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG,1024).childHandler(new ChildChannelHandler());
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new TimeServer().bind(10083);
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new TimeEncoder());
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static class TimeServerHandler extends ChannelInboundHandlerAdapter{

        private int counter;

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuffer = (ByteBuf) msg;

            byte[] bytes = new byte[byteBuffer.readableBytes()];

            byteBuffer.readBytes(bytes);

            String word = new String(bytes);

            System.out.println("接收到客户端的请求：" + word + " " + ++counter);

            ByteBuf resp = Unpooled.copiedBuffer("服务端响应".getBytes());

            ctx.writeAndFlush(resp);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
           ctx.close();
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {

            ByteBuf time = ctx.alloc().buffer(4);

            time.writeInt((int)(System.currentTimeMillis() / 1000l + 2208988800l));

            ChannelFuture channelFuture = ctx.writeAndFlush(time);
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    assert channelFuture == future;
                    ctx.close();
                }
            });
        }
    }

    public static class TimeServerOutHandler extends ChannelOutboundHandlerAdapter{

        @Override
        public void read(ChannelHandlerContext ctx) throws Exception {
            super.read(ctx);
        }

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            super.write(ctx, msg, promise);
        }

        @Override
        public void flush(ChannelHandlerContext ctx) throws Exception {
            super.flush(ctx);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

    public static class TimeEncoder extends MessageToByteEncoder<UnixTime> {

        @Override
        protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) {
            out.writeInt((int)msg.value());
        }

    }

}