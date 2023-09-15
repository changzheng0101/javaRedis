package org.weixiao;


import org.weixiao.exceptions.UnAuthException;
import org.weixiao.struct.RedisDatabase;
import org.weixiao.struct.dict.DictHt;

import java.time.Instant;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RedisDatabase database = new RedisDatabase();
        DictHt<Object> databaseData = database.getData();
        DictHt<Instant> databaseExpireKeys = database.getExpireKeys();
        boolean isAuth = false;
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("使用前需登录，密码:123456");
        do {
            System.out.print(">>>>");
            input = scanner.nextLine();
            String[] split = input.split(" ");
            String command = split[0];
            String[] arguments = new String[split.length - 1];
            System.arraycopy(split, 1, arguments, 0, arguments.length);
            try {
                if (Objects.equals(command, "auth")) {
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
                } else if (Objects.equals(command, "exit")) {
                    System.out.println("bye");
                    exit = true;
                } else if (!isAuth) {
                    throw new UnAuthException();
                } else {
                    switch (command) {
                        case "set" -> {
                            if (arguments.length != 2) {
                                System.out.println("参数个数有误...");
                                break;
                            }
                            String key = arguments[0];
                            String value = arguments[1];
                            databaseData.put(key, value);
                        }
                        case "get" -> {
                            if (arguments.length != 1) {
                                System.out.println("参数个数有误...");
                                break;
                            }
                            String key = arguments[0];
                            System.out.println(databaseData.get(key));
                        }
                        case "del" -> {
                            if (arguments.length != 1) {
                                System.out.println("参数个数有误...");
                                break;
                            }
                            String key = arguments[0];
                            System.out.println(databaseData.del(key));
                        }
                        case "exists" -> {
                            if (arguments.length != 1) {
                                System.out.println("参数个数有误...");
                                break;
                            }
                            String key = arguments[0];
                            System.out.println(databaseData.exists(key));
                        }
                        default -> System.out.println("未知命令");
                    }
                }
            } catch (UnAuthException e) {
                System.out.println(e.getMessage());
            }
        } while (!exit);
        scanner.close();
    }
}