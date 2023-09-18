package org.weixiao;


import org.weixiao.common.RedisObjectEncoding;
import org.weixiao.common.RedisObjectType;
import org.weixiao.exceptions.UnAuthException;
import org.weixiao.struct.RedisDatabase;
import org.weixiao.struct.dict.DictHt;

import java.time.Instant;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

import static org.weixiao.util.RedisObjectUtil.*;

public class Main {
    public static void main(String[] args) {
        RedisDatabase database = new RedisDatabase();
        DictHt<RedisObject, RedisObject> databaseData = database.getData();
        DictHt<RedisObject, Instant> expireKeys = database.getExpireKeys();
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
                    if (!checkArgumentsNum(arguments, 1)) continue;
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
                        // base command
                        case "set" -> {
                            if (!checkArgumentsNum(arguments, 2)) break;
                            String key = arguments[0];
                            String value = arguments[1];
                            RedisObject stringRedisObject = new RedisObject(
                                    RedisObjectType.REDIS_STRING,
                                    RedisObjectEncoding.REDIS_ENCODING_STRING,
                                    Instant.now(),
                                    value
                            );
                            databaseData.put(wrapStringRedisObject(key), stringRedisObject);
                        }
                        case "get" -> {
                            if (!checkArgumentsNum(arguments, 1)) break;
                            String key = arguments[0];
                            System.out.println(
                                    parseStringRedisObject(databaseData.get(wrapStringRedisObject(key)))
                            );
                        }
                        case "del" -> {
                            if (!checkArgumentsNum(arguments, 1)) break;
                            String key = arguments[0];
                            System.out.println(databaseData.del(wrapStringRedisObject(key)));
                        }
                        case "exists" -> {
                            if (!checkArgumentsNum(arguments, 1)) break;
                            String key = arguments[0];
                            System.out.println(databaseData.exists(wrapStringRedisObject(key)));
                        }
                        case "flushall" -> {
                            if (!checkArgumentsNum(arguments, 0)) break;
                            database.setData(new DictHt<>());
                            database.setExpireKeys(new DictHt<>());
                        }
                        // hash command
                        case "hset" -> {
                            if (!checkArgumentsNum(arguments, 3)) break;
                            String hashName = arguments[0];
                            String key = arguments[1];
                            String value = arguments[2];
                            database.hset(
                                    wrapStringRedisObject(hashName),
                                    wrapHashRedisObject(key, value)
                            );
                        }
                        case "hget" -> {
                            if (!checkArgumentsNum(arguments, 2)) break;
                            String hashName = arguments[0];
                            String key = arguments[1];
                            System.out.println(parseStringRedisObject(
                                    database.hget(wrapStringRedisObject(hashName), wrapStringRedisObject(key))
                            ));
                        }
                        case "hgetall" -> {
                            if (!checkArgumentsNum(arguments, 1)) break;
                            String hashName = arguments[0];
                            RedisObject redisObject = database.hgetall(wrapStringRedisObject(hashName));
                            DictHt<RedisObject, RedisObject> hashData = (DictHt<RedisObject, RedisObject>) redisObject.getData();
                            Set<RedisObject> keys = hashData.keys();
                            for (RedisObject key : keys) {
                                System.out.println("key:" + parseStringRedisObject(key) + " value:" + parseStringRedisObject(hashData.get(key)));
                            }
                        }
                        case "hdel" -> {
                            if (!checkArgumentsNum(arguments, 2)) break;
                            String hashName = arguments[0];
                            String key = arguments[0];
                            RedisObject stringRedisObject = new RedisObject(
                                    RedisObjectType.REDIS_STRING,
                                    RedisObjectEncoding.REDIS_ENCODING_STRING,
                                    Instant.now(),
                                    key
                            );
                            databaseData.hdel(hashName, stringRedisObject);
                        }
                        case "hexists" -> {
                            if (!checkArgumentsNum(arguments, 2)) break;
                            String hashName = arguments[0];
                            String key = arguments[0];
                            RedisObject stringRedisObject = new RedisObject(
                                    RedisObjectType.REDIS_STRING,
                                    RedisObjectEncoding.REDIS_ENCODING_STRING,
                                    Instant.now(),
                                    key
                            );
                            databaseData.hexists(hashName, stringRedisObject);
                        }
                        // list command
                        // set command
                        // zset command
                        default -> System.out.println("未知命令");
                    }
                }
            } catch (UnAuthException e) {
                System.out.println(e.getMessage());
            }
        } while (!exit);
        scanner.close();
    }

    private static boolean checkArgumentsNum(String[] arguments, int length) {
        if (arguments.length != length) {
            System.out.println("参数个数有误...");
            return false;
        }
        return true;
    }


}