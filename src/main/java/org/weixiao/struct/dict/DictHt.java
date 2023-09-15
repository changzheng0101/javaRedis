package org.weixiao.struct.dict;

import java.util.List;

/**
 * dict hashtable
 *
 * @Date 2023/9/15 9:01
 * @Created by weixiao
 */
public class DictHt<K, V> {
    private List<DictEntry<K, V>> hashtable;
    private int size;
    private int used;
}
