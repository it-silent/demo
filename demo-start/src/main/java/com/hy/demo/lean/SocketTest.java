package com.hy.demo.lean;

import com.hy.demo.common.utils.ExecutorUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author silent
 * @date 2018/4/19
 */
public class SocketTest {

    private static ExecutorService executorService;

    static {
        executorService = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MINUTES, new LinkedBlockingDeque<>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setDaemon(false);
                        thread.setPriority(Thread.MAX_PRIORITY);
                        thread.setUncaughtExceptionHandler((t, e) -> {
                            System.err.println(String.format("%s, %s", t.getName(), e.getMessage()));
                        });
                        return thread;
                    }
                });
    }

    public static void main(String[] args) {
        try {
//            System.err.println(InetAddress.getByName("www.google.com"));
//            System.err.println(InetAddress.getLocalHost().getHostAddress());


//            executorService.execute(() -> {
//                try {
//                    DatagramSocket socket = new DatagramSocket();
//                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//                    String len;
//                    while ((len = in.readLine()) != null) {
//                        byte[] bytes = len.getBytes();
//                        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 12345);
//                        socket.send(packet);
//                    }
//                } catch (Exception e) {
//                    System.err.println(e);
//                }
//            });

            ExecutorUtils.execute(() -> {
                try {
                    DatagramSocket socket = new DatagramSocket();
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    String len;
                    while ((len = in.readLine()) != null) {
                        byte[] bytes = len.getBytes();
                        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 12345);
                        socket.send(packet);
                    }
                } catch (Exception e) {
                    System.err.println(e);
                }

            });


//            executorService.execute(() -> {
//                try {
//                    DatagramSocket datagramSocket = new DatagramSocket(12345);
//                    while (true) {
//                        byte[] bytes = new byte[1024];
//                        DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
//                        datagramSocket.receive(packet);
//                        String data = new String(packet.getData(), 0, packet.getLength());
//                        System.err.println(String.format("receive : %s", data));
//                    }
//                } catch (Exception e) {
//                    System.err.println(e);
//                }
//            });

            ExecutorUtils.execute(() -> {
                try {
                    DatagramSocket datagramSocket = new DatagramSocket(12345);
                    while (true) {
                        byte[] bytes = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
                        datagramSocket.receive(packet);
                        String data = new String(packet.getData(), 0, packet.getLength());
                        System.err.println(String.format("receive : %s", data));
                    }
                } catch (Exception e) {
                    System.err.println(e);
                }

            });


        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
