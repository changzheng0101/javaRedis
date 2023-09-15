package org.weixiao.struct.linkedlist;


/**
 * @Date 2023/9/14 22:02
 * @Created by weixiao
 */
public class ListNode<E> {
    private ListNode<E> prev;
    private ListNode<E> next;
    private E value;

    public ListNode(ListNode prev, ListNode next, E value) {
        this.prev = prev;
        this.next = next;
        this.value = value;
    }

    public ListNode getPrev() {
        return prev;
    }

    public void setPrev(ListNode prev) {
        this.prev = prev;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
