package com.example.netty.class1_bytebuffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan
 * @date 2023/7/1 18:58
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1000);
        // 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 2. 绑定监听端口
        ssc.bind(new InetSocketAddress(8080));

        // 3. 连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            // 4. accept建立与客户端连接，SocketChannel用来与客户端通信
            System.out.println("connecting...");
            SocketChannel sc = ssc.accept(); // 阻塞方法，线程停止运行
            System.out.println("connected..." + sc);
            channels.add(sc);
            for (SocketChannel channel : channels){
                channel.read(buffer); // 阻塞方法，线程停止运行
                buffer.flip();
                buffer.clear();
            }
        }
    }
}
