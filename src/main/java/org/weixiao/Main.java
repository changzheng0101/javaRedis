package org.weixiao;


import org.weixiao.common.RedisObject;
import org.weixiao.exceptions.UnAuthException;
import org.weixiao.struct.RedisDatabase;
import org.weixiao.struct.dict.DictHt;

import java.time.Instant;
import java.util.HashSet;
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
                            databaseData.put(wrapStringRedisObject(key), wrapStringRedisObject(value));
                        }
                        case "get" -> {
                            if (!checkArgumentsNum(arguments, 1)) break;
                            String key = arguments[0];
                            System.out.println(parseStringRedisObject(databaseData.get(wrapStringRedisObject(key))));
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
                            database.hset(wrapStringRedisObject(hashName), wrapHashRedisObject(key, value));
                        }
                        case "hget" -> {
                            if (!checkArgumentsNum(arguments, 2)) break;
                            String hashName = arguments[0];
                            String key = arguments[1];
                            System.out.println(parseStringRedisObject(database.hget(wrapStringRedisObject(hashName), wrapStringRedisObject(key))));
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
                            String key = arguments[1];
                            if (database.hdel(wrapStringRedisObject(hashName), wrapStringRedisObject(key))) {
                                System.out.println("true");
                            } else {
                                System.out.println("false");
                            }
                        }
                        case "hexists" -> {
                            if (!checkArgumentsNum(arguments, 2)) break;
                            String hashName = arguments[0];
                            String key = arguments[1];
                            System.out.println(database.hexists(wrapStringRedisObject(hashName), wrapStringRedisObject(key)));
                        }
                        // list command
                        case "lpush" -> {
                            if (!checkArgumentsNum(arguments, 2)) break;
                            String arrayName = arguments[0];
                            String key = arguments[1];
                            database.lpush(wrapStringRedisObject(arrayName), wrapStringRedisObject(key));
                            System.out.println("ok");
                        }
                        case "rpush" -> {
                            if (!checkArgumentsNum(arguments, 2)) break;
                            String arrayName = arguments[0];
                            String key = arguments[1];
                            database.rpush(wrapStringRedisObject(arrayName), wrapStringRedisObject(key));
                            System.out.println("ok");
                        }
                        case "lpop" -> {
                            if (!checkArgumentsNum(arguments, 1)) break;
                            String arrayName = arguments[0];
                            RedisObject popValue = database.lpop(wrapStringRedisObject(arrayName));
                            if (popValue != null) {
                                System.out.println(parseStringRedisObject(popValue));
                            } else {
                                System.out.println("(nil)");
                            }
                        }
                        case "rpop" -> {
                            if (!checkArgumentsNum(arguments, 1)) break;
                            String arrayName = arguments[0];
                            RedisObject popValue = database.rpop(wrapStringRedisObject(arrayName));
                            if (popValue != null) {
                                System.out.println(parseStringRedisObject(popValue));
                            } else {
                                System.out.println("(nil)");
                            }
                        }
                        // set command
                        case "sadd" -> {
                            if (!checkArgumentsNum(arguments, 2)) break;
                            String setName = arguments[0];
                            String value = arguments[1];
                            database.sadd(wrapStringRedisObject(setName), wrapStringRedisObject(value));
                        }
                        case "smembers" -> {
                            if (!checkArgumentsNum(arguments, 1)) break;
                            String setName = arguments[0];
                            RedisObject smembers = database.smembers(wrapStringRedisObject(setName));
                            if (smembers == null) {
                                System.out.println("(nil)");
                            } else {
                                HashSet<RedisObject> setData = (HashSet<RedisObject>) smembers.getData();
                                for (RedisObject data : setData) {
                                    System.out.println(parseStringRedisObject(data));
                                }
                            }
                        }
                        case "srem" -> {
                            if (!checkArgumentsNum(arguments, 2)) break;
                            String setName = arguments[0];
                            String value = arguments[1];
                            database.srem(wrapStringRedisObject(setName), wrapStringRedisObject(value));
                        }
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