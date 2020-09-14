import java.util.List;

public class LinkedListDeque<T>{

    private ListNode sentinel;
    private ListNode recursiveMarker = null;
    private int size;

    public class ListNode{
        ListNode prev;
        T item;
        ListNode next;

        ListNode(ListNode p,T i,ListNode n){
            prev=p;
            item=i;
            next=n;
            }
        }

    //creat an empty deque//
    public LinkedListDeque(){
        sentinel = new ListNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    //deep copy
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new ListNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        ListNode pointer = other.sentinel.next;
        while (pointer != other.sentinel) {
            addFirst(pointer.item);
            pointer = pointer.next;
        }
    }
    //*Adds an item of type T to the front of the deque*//
    public void addFirst(T item){
        if (sentinel.next == sentinel) {
            sentinel.next = new ListNode(sentinel, item, sentinel.next);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next.prev = new ListNode(sentinel, item, sentinel.next);
            sentinel.next = sentinel.next.prev;

        }
        size += 1;
    }
    /*Adds an item of type T to the back of the deque*/
    public void addLast(T item){
        if (sentinel.prev == sentinel) {
            sentinel.next = new ListNode(sentinel, item, sentinel.next);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.prev.next = new ListNode(sentinel.prev, item, sentinel);
            sentinel.prev = sentinel.prev.next;
        }
        size += 1;
    }
    // Returns true if deque is empty, false otherwise.//
    public boolean isEmpty(){
        return size==0;
    }
    //Returns the number of items in the deque//
    public int size(){
        return size;
    }
    //Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.//
    public void printDeque(){
        ListNode pointer = sentinel.next;
        while (pointer != sentinel) {
            System.out.println(pointer.item);
            pointer = pointer.next;
        }
    }
    //Removes and returns the item at the front of the deque. If no such item exists, returns null.//
    public T removeFirst(){
        T item = null;
        if (sentinel.next != sentinel) {
            item = sentinel.next.item;
            if (size == 1) {
                sentinel.next.prev = null;
                sentinel.prev.next = null;
                sentinel.next = sentinel;
                sentinel.prev = sentinel;
            } else {
                sentinel.next.next.prev = sentinel;
                sentinel.next.next = null;
                sentinel.next.prev = null;
                sentinel.next = sentinel.next.next;
            }
            size -= 1;
        }
        return item;
    }

    // Removes and returns the item at the back of the deque. If no such item exists, returns null.//
    public T removeLast(){
        T item = null;
        if (sentinel.prev != sentinel) {
            item = sentinel.prev.item;
            if (size == 1) {
                sentinel.next.prev = null;
                sentinel.prev.next = null;
                sentinel.next = sentinel;
                sentinel.prev = sentinel;
            } else {
                sentinel.prev.prev.next = sentinel;
                sentinel.prev.prev = null;
                sentinel.prev.next =null;
                sentinel.prev = sentinel.prev.prev;
            }
            size -= 1;
        }
        return item;
    }

    //get item at the given index//
    public T get(int index){
        if (index >= size) {
            return null;
        }
        ListNode pointer = sentinel.next;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.item;
    }

    //same as get ,but users recursion//
    public T getRecursive(int index){
        if (index >= size) {
            return null;
        }
        if (recursiveMarker == null) {
            recursiveMarker = sentinel.next;
        }
        if (index == 0) {
            T item = recursiveMarker.item;
            recursiveMarker = null;
            return item;
        } else {
            recursiveMarker = recursiveMarker.next;
            return getRecursive(index - 1);
        }
    }



}
