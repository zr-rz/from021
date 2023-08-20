package com.example.netty.class1_bytebuffer;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ryan
 * @date 2023/6/21 21:12
 */
public class TestByteBuffer {
    @Test
    public void test() {
        try {
            FileChannel channel = new FileInputStream("data.txt").getChannel();
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                // 从channel读数据，向buffer写入
                int len = channel.read(buffer);
                if (len == -1) { // 没有内容了
                    break;
                }
                // 打印buffer的内容
                buffer.flip(); // 切换至读模式
                while (buffer.hasRemaining()) { // 是否还有剩余未读数据
                    byte b = buffer.get();
                    System.out.println((char) b);
                }
                buffer.clear(); // 切换为写模式
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
