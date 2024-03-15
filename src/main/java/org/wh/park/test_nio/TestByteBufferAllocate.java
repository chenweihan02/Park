package org.wh.park.test_nio;

import java.nio.ByteBuffer;

/**
 * @author ChenWeihan
 * @description TODO
 */
public class TestByteBufferAllocate {

    public static void main(String[] args) {

        /**
         * class java.nio.HeapByteBuffer    Java 堆内存
         *      读写效率较低
         *      会受GC影响【系统分配给JVM的内存】
         *
         * class java.nio.DirectByteBuffer  直接内存
         *      读写效率较高（少一次拷贝）
         *      不受GC影响【系统内存 - 分配的速度较慢 - 使用不当会造成内存泄露】
         *
         */

        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());


    }
}
