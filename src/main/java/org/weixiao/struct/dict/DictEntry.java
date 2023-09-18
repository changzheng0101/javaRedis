package org.weixiao.struct.dict;

/**
 * @Date 2023/9/15 9:00
 * @Created by weixiao
 */
public class DictEntry<K, V> {
    private K key;
    private V value;
    private DictEntry<K, V> next;

    public DictEntry(K key, V value, DictEntry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public DictEntry<K, V> getNext() {
        return next;
    }

    public void setNext(DictEntry<K, V> next) {
        this.next = next;
    }
}
