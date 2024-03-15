package org.wh.park.test_nio;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ChenWeihan
 * @description TODO
 */
public class TestByteBuffer {
    public static void main(String[] args) {
        //FileChannel
        //1.输入输出流   RandomAccessFile
        try {
            FileChannel channel = new FileInputStream("D:\\桌面\\data.txt").getChannel();

            //准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10); // 10B

            //从channel读取数据，向buffer写入
            while (true) {

                int len = channel.read(buffer);
                System.out.println("len = " + len);
                if (len == -1) {
                    break;
                }

                //打印buffer内容
                buffer.flip(); //切换读模式（默认是写模式）
                while (buffer.hasRemaining()) { //检查是否还有未读的数据
                    byte b = buffer.get();
                    System.out.println("(char)b = " + (char) b);

                }
                buffer.clear(); ///切换写模式
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
