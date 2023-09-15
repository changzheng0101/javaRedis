package org.weixiao.struct.dict;

/**
 * @Date 2023/9/15 9:00
 * @Created by weixiao
 */
public class DictEntry<K, V> {
    private K key;
    private V value;
    private DictEntry<K, V> dictEntry;
}
