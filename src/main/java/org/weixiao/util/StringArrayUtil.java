package org.weixiao.util;

import java.util.Objects;

/**
 * @Date 2023/9/19 16:50
 * @Created by weixiao
 */
public class StringArrayUtil {
    public static int find(String[] array, String target) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], target)) return i;
        }
        return -1;
    }


}
