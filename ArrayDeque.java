public class ArrayDeque<T> {
    //declare variables;
    //the array that stores elements for the deque.
    private T[] elements;

    // the index of the element at the head of the deque.
    private int head;

    //the index of the element at the end of the deque.
    private int tail;

    //set the initial capacity the element array.
    private static final int INITIAL_CAPACITY = 8;

    private int n = 0;



    //creat a empty //
    public ArrayDeque() {
        elements = (T[]) new Object[INITIAL_CAPACITY];
        head = 0;
        tail = 0;

    }
    /**
    //creat a deep copy of other//
    public ArrayDeque(ArrayDeque other){
        elements = (T[]) new Object[other.elements.length];
        copyAll(other);
        head = other.head;
        tail = other.tail;
    }
     */

    private void copyAll(ArrayDeque other) {
        for (int i = 0; i < other.elements.length; i++) {
            elements[i] = (T) other.elements[i];
        }

    }
    //return how many items in constant time//
    public int size() {
        return (tail - head) & (elements.length - 1);
    }

    //check if deque is empty,if size=0,return true.otherwise false//
    public boolean isEmpty() {
        return head == tail;
    }

    //print the item of the deque from first to last//
    public void printDeque() {
        int h = head;
        int t = n;
        while (elements[h] != null) {
            System.out.println(elements[h] + " ");
            h += 1;
            if (h == elements.length) {
                break;
            }
        }
        while (elements[t] != null) {
            System.out.println(elements[t] + " ");
            t += 1;
            if (t == head) {
                break;
            }
        }
    }



    //add item of the type item to the front of the deque//
    public void addFirst(T x) {
        head = (head - 1) & (elements.length - 1);
        elements[head] = x;
        if (head == tail) {
            doubleCapacity();
        }
    }

    //add an item of type item to the back of deque//
    public void addLast(T x) {
        elements[tail] = x;
        tail = (tail + 1) & (elements.length - 1);
        if (tail == head) {
            doubleCapacity();
        }
    }


    //remove and return item at the front of the deque//
    public T removeFirst() {
        int h = head;
        T result = elements[h];
        if (result == null) {
            return null;
        }
        elements[h] = null;
        head = (h + 1) & (elements.length - 1);
        checkSize();
        return result;
    }

    //remove and return item at the back of the deque//
    public T removeLast() {
        int t = (tail - 1) & (elements.length - 1);
        T result = elements[t];
        if (result == null) {
            return null;
        }
        elements[t] = null;
        tail = t;
        checkSize();
        return result;
    }

    //get the item at the given index//
    public T get(int index) {
        if (index > elements.length) {
            return null;
        } else if (elements[(head + index) & (elements.length - 1)] == null) {
            return null;
        } else {
            return elements[(head + index) & (elements.length - 1)];
        }
    }




    private void doubleCapacity() {
        n = elements.length;
        int r = n - head;
        int newCapacity = n << 1;
        Object[] a = new Object[newCapacity];
        System.arraycopy(elements, head, a, 0, r);
        System.arraycopy(elements, 0, a, r, head);
        elements = (T[]) a;
        head = 0;
        tail = n;
    }

    private void checkSize() {
        int length = elements.length;
        if (length > INITIAL_CAPACITY && size() <= length / 4) {
            int newCapacity = length >> 1;
            Object[] a = new Object[newCapacity];
            if (head < tail) {
                System.arraycopy(elements, head, a, 0, tail - head);

                tail = tail - head;
                head = 0;
            } else {
                System.arraycopy(elements, head, a, 0, length - head);
                System.arraycopy(elements, 0, a, length - head, tail);
                head = 0;
                tail += 1;
            }
            elements = (T[]) a;

        }
    }
}
