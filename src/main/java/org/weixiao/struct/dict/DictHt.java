package org.weixiao.struct.dict;

import java.util.*;

/**
 * dict hashtable
 *
 * @Date 2023/9/15 9:01
 * @Created by weixiao
 */
public class DictHt<K, V> {
    private final List<DictEntry<K, V>> hashtable;
    private final Set<K> keySet;
    private int size;
    private int used;

    public DictHt() {
        this.hashtable = new ArrayList<>(Collections.nCopies(10, null));
        this.keySet = new HashSet<>();
        // ArrayList --> DEFAULT_CAPACITY
        this.size = 10;
        this.used = 0;
    }

    public boolean put(K key, V value) {
        int index = getHashIndex(key);
        DictEntry<K, V> currentHeadDictEntry = hashtable.get(index);
        // check key and set value if key exists
        DictEntry<K, V> tmpEntry = currentHeadDictEntry;
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
        DictEntry<K, V> dictEntry = new DictEntry<>(key, value, null);
        dictEntry.setNext(currentHeadDictEntry);
        hashtable.set(index, dictEntry);
        keySet.add(key);
        return true;
    }

    private int getHashIndex(K key) {
        return Math.abs(key.hashCode()) % this.size;
    }

    public V get(K key) {
        DictEntry<K, V> dictEntry = hashtable.get(getHashIndex(key));
        while (dictEntry != null) {
            if (Objects.equals(dictEntry.getKey(), key)) {
                return dictEntry.getValue();
            }
            dictEntry = dictEntry.getNext();
        }
        return null;
    }

    public boolean del(K key) {
        int index = getHashIndex(key);
        DictEntry<K, V> dictEntry = hashtable.get(index);
        DictEntry<K, V> prev = null;
        while (dictEntry != null) {
            if (Objects.equals(dictEntry.getKey(), key)) {
                DictEntry<K, V> next = dictEntry.getNext();
                if (prev == null) {
                    hashtable.set(index, next);
                } else {
                    prev.setNext(next);
                }
                dictEntry.setNext(null);
                if (hashtable.get(index) == null) {
                    this.used--;
                }
                keySet.remove(key);
                return true;
            }
            prev = dictEntry;
            dictEntry = dictEntry.getNext();
        }
        return false;
    }

    public boolean exists(K key) {
        int index = getHashIndex(key);
        DictEntry<K, V> headEntry = hashtable.get(index);
        while (headEntry != null) {
            if (Objects.equals(headEntry.getKey(), key)) {
                return true;
            }
            headEntry = headEntry.getNext();
        }
        return false;
    }

    public Set<K> keys() {
        return this.keySet;
    }
}
