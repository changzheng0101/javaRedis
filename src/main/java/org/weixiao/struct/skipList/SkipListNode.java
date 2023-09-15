package org.weixiao.struct.skipList;

import java.util.List;

/**
 * @Date 2023/9/15 9:05
 * @Created by weixiao
 */
public class SkipListNode<E> {
    private E value;
    private double score;
    // 后退指针
    private SkipListNode<E> backward;
    private List<SkipListLevel<E>> levels;
}
