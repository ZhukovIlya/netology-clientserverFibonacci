package ru.netology;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String IP = "127.0.0.1";
    private static final int PORT = 34543;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(IP, PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        Scanner scanner = new Scanner(System.in);
        String line;
        while (true) {
            System.out.println("Введите число для вычисление N-го члена ряда Фибоначчи");
            line = scanner.nextLine();
            out.println(line);
            String answer = in.readLine();
            if (answer.contains("Ошибка")) {
                System.out.println(answer);
            } else {
                System.out.printf("%s член ряда Фибоначчи равен: %s \n", line, answer);
            }
        }

    }


}

