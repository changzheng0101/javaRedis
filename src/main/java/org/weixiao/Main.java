package org.weixiao;


import org.weixiao.struct.RedisDatabase;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RedisDatabase database = new RedisDatabase();
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(">>>>");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("bye");
                break;
            }
            System.out.println("你输入的内容是： " + input);
        }
        scanner.close();
    }
}