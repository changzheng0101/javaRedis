package org.weixiao.struct.dict;

/**
 * @Date 2023/9/15 9:00
 * @Created by weixiao
 */
public class DictEntry<V> {
    private String key;
    private V value;
    private DictEntry<V> next;

    public DictEntry(String key, V value, DictEntry<V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public DictEntry<V> getNext() {
        return next;
    }

    public void setNext(DictEntry<V> next) {
        this.next = next;
    }
}
