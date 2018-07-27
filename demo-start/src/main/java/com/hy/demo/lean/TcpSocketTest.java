package com.hy.demo.lean;

import com.hy.demo.common.utils.ExecutorUtils;
import org.apache.catalina.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * TcpSocketTest
 *
 * @author silent
 * @date 2018/4/20
 */
public class TcpSocketTest {

    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(System.in);
            byte[] bytes = new byte[1024];
            int len = in.read(bytes);

            String address = "localhost";
            int port = 11234;
            //client
            ExecutorUtils.execute(() -> {
                String s = SocketClient(address, port, new String(bytes, 0, len));
                System.err.println(s);
            });

            //server
            ExecutorUtils.execute(() -> {
                SocketServer(port);
            });


        } catch (Exception e) {

        }


    }


    private static String SocketClient(String address, Integer port, String content) {
        try {
            Socket socket = new Socket(address, port);
            socket.getOutputStream().write(content.getBytes());
            socket.shutdownOutput();
            byte[] bytes = new byte[1024];
            int len = socket.getInputStream().read(bytes);
            socket.close();

            return new String(bytes, 0, len);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }


    private static String SocketServer(Integer port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket accept = serverSocket.accept();

            byte[] bytes = new byte[1024];
            int len;
            InputStream inputStream = accept.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while ((len = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }

            OutputStream outputStream = accept.getOutputStream();
            byte[] b = out.toByteArray();
            byte[] preResp = "server response: ".getBytes();
            int length = preResp.length;
            preResp = Arrays.copyOf(preResp, length + b.length);
            System.arraycopy(b, 0, preResp, length, b.length);
            outputStream.write(preResp);

            inputStream.close();
            outputStream.close();
            accept.close();
            serverSocket.close();

            return new String(b);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

}
