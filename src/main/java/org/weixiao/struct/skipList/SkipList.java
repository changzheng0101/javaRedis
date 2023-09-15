package org.weixiao.struct.skipList;


/**
 * @Date 2023/9/15 9:09
 * @Created by weixiao
 */
public class SkipList<E> {
    private SkipListNode<E> header;
    private SkipListNode<E> tail;
    // 表中节点的数量
    private int length;
    // 表中层数最大的节点的层数
    private int level;
}
