package org.weixiao.struct.dict;

import java.util.*;

/**
 * dict hashtable
 *
 * @Date 2023/9/15 9:01
 * @Created by weixiao
 */
public class DictHt<V> {
    private final List<DictEntry<V>> hashtable;
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
        DictEntry<V> currentHeadDictEntry = hashtable.get(index);
        // check key and set value if key exists
        DictEntry<V> tmpEntry = currentHeadDictEntry;
        while (tmpEntry != null) {
            if (Objects.equals(tmpEntry.getKey(), key)) {
                tmpEntry.setValue(value);
                return true;
            }
            tmpEntry = tmpEntry.getNext();
        }
        // no key exist, put as a new entry
        if (hashtable.get(index) == null) {
            this.used++;
        }
        DictEntry<V> dictEntry = new DictEntry<>(key, value, null);
        dictEntry.setNext(currentHeadDictEntry);
        hashtable.set(index, dictEntry);
        return true;
    }

    private int getHashIndex(String key) {
        return key.hashCode() % this.size;
    }

    public V get(String key) {
        DictEntry<V> dictEntry = hashtable.get(getHashIndex(key));
        while (dictEntry != null) {
            if (Objects.equals(dictEntry.getKey(), key)) {
                return dictEntry.getValue();
            }
            dictEntry = dictEntry.getNext();
        }
        return null;
    }

    public boolean del(String key) {
        int index = getHashIndex(key);
        DictEntry<V> dictEntry = hashtable.get(index);
        DictEntry<V> prev = null;
        while (dictEntry != null) {
            if (Objects.equals(dictEntry.getKey(), key)) {
                DictEntry<V> next = dictEntry.getNext();
                if (prev == null) {
                    hashtable.set(index, next);
                } else {
                    prev.setNext(next);
                }
                dictEntry.setNext(null);
                if (hashtable.get(index) == null) {
                    this.used--;
                }
                return true;
            }
            prev = dictEntry;
            dictEntry = dictEntry.getNext();
        }
        return false;
    }

    public boolean exists(String key) {
        int index = getHashIndex(key);
        DictEntry<V> headEntry = hashtable.get(index);
        while (headEntry != null) {
            if (Objects.equals(headEntry.getKey(), key)) {
                return true;
            }
            headEntry = headEntry.getNext();
        }
        return false;
    }
}
