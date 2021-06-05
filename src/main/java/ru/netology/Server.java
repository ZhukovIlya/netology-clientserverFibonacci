package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 34543;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        ExecutorService threadPool = Executors.newFixedThreadPool(4);


        while (true) {
            Socket socket = serverSocket.accept();
            threadPool.submit(calculation(socket));
        }
    }

    public static int fibinacci(int n) {
        int a = 0;
        int b = 1;
        int c = 0;
        for (int i = 0; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
    public static Runnable calculation(Socket socket) {
        return () -> {
            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
                String line;
                while((line = in.readLine()) != null){
                    try {
                        out.println(fibinacci(Integer.parseInt(line)));
                    } catch (NumberFormatException e) {
                        e.printStackTrace(System.out);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        };
    }

}
