package com.example.netty.class1_bytebuffer;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author ryan
 * @date 2023/6/25 23:17
 */
public class TestByteBufferThreePart {
    public static void main(String[] args) {

    }

    public static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    byte b = source.get();
                    target.put(b);
                }
                System.out.println(Arrays.toString(target.array()));
            }
        }
        source.compact();
    }
}
