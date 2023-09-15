package org.weixiao;


import org.weixiao.exceptions.UnAuthException;
import org.weixiao.struct.RedisDatabase;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RedisDatabase database = new RedisDatabase();
        Boolean isAuth = false;
        Boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("使用前需登录，密码:123456");
        while (true) {
            System.out.print(">>>>");
            input = scanner.nextLine();
            String[] split = input.split(" ");
            String command = split[0];
            String[] arguments = new String[split.length - 1];
            System.arraycopy(split, 1, arguments, 0, arguments.length);
            try {
                switch (command) {
                    case "test" -> {
                        if (isAuth) {
                            System.out.println("test");
                        } else {
                            throw new UnAuthException();
                        }
                    }
                    case "auth" -> {
                        if (arguments.length != 1) {
                            System.out.println("参数个数有误...");
                            break;
                        }
                        if (Objects.equals(arguments[0], "123456")) {
                            isAuth = true;
                            System.out.println("认证成功");
                        } else {
                            System.out.println("认证失败");
                        }
                    }
                    case "exit" -> {
                        System.out.println("bye");
                        exit = true;
                    }
                    default -> System.out.println("未知命令");
                }
            } catch (UnAuthException e) {
                System.out.println(e.getMessage());
            }
            if (exit) {
                break;
            }
        }
        scanner.close();
    }
}