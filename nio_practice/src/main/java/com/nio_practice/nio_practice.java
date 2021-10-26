package com.nio_practice;
import java.nio.*;
import java.nio.charset.*;
import java.nio.channels.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class nio_practice {

    private static Object IOException;

    public static void main(String[] args){
        final int SIZE_OF_BUFFER = 1024;
        ByteBuffer byteBuf_1 = ByteBuffer.allocate(SIZE_OF_BUFFER);//initially, write mode.
        ByteBuffer byteBuf_2 = ByteBuffer.allocate(SIZE_OF_BUFFER*2); //
        nio_buffer_util buffer_util = new nio_buffer_util();


        try{

            byteBuf_1.put("hello, world!".getBytes(StandardCharsets.UTF_8));
            byteBuf_1.flip();//initially mode of ByteBuffer is set to write mode. to read, ByteBuffer flip() is necessary for read operations.
            System.out.println(Charset.forName("UTF-8").decode(byteBuf_1).toString());
            byteBuf_1.rewind();// any kind of 'reading' will change the offset. without rewind(), nothing will be prompted on next println
            buffer_util.print_buffer_status(byteBuf_1);
            byteBuf_2.put(byteBuf_1);
            byteBuf_2.flip();
            buffer_util.print_buffer_status(byteBuf_2);
            String bb2 = Charset.forName("UTF-8").decode(byteBuf_2).toString();
            System.out.println(bb2);

        }
        catch(BufferOverflowException e){
            e.printStackTrace();
        }

    }
}
