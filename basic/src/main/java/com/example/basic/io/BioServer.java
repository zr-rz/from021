package com.example.basic.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        for (; ; ) {
            System.out.println("等待连接中");
            // 监听，等待客户端连接
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    public static void handler(Socket socket) {
        byte[] bytes = new byte[128];
        System.out.println("当前线程信息：" + Thread.currentThread().getName());
        try {
            // 通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            while (inputStream.read(bytes) != -1) {
                System.out.println(Thread.currentThread().getName() + "：发送信息为：" + new String(bytes, 0, bytes.length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            System.out.println("关闭连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
