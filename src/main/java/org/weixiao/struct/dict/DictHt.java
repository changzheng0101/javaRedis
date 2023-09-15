package org.weixiao.struct.dict;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * dict hashtable
 *
 * @Date 2023/9/15 9:01
 * @Created by weixiao
 */
public class DictHt<V> {
    private List<DictEntry<V>> hashtable;
    private int size;
    private int used;

    public DictHt() {
        this.hashtable = new ArrayList<>(Collections.nCopies(10, null));
        // ArrayList --> DEFAULT_CAPACITY
        this.size = 10;
        this.used = 0;
    }

    public boolean put(String key, V value) {
        int index = getHashIndex(key);
        hashtable.set(index, new DictEntry<>(key, value, null));
        return true;
    }

    private int getHashIndex(String key) {
        return key.hashCode() % this.size;
    }

    public V get(String key) {
        DictEntry<V> dictEntry = hashtable.get(getHashIndex(key));
        if (dictEntry == null) return null;
        return dictEntry.getValue();
    }
}
