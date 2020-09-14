public class ArrayDeque<T> {
    //declare variables;
    private T[] Array;
    private int nextFirst;
    private int nextLast;
    private int size;

    //creat a empty //
    public ArrayDeque(){
        Array =(T[]) new Object[8];
        nextFirst=4;
        nextLast=5;
        size=0;
    }

    //return how many items in constant time//
    public int size(){
        return size;
    }

    //check if deque is empty,if size=0,return true.otherwise false//
    public boolean isEmpty(){
        return size==0;
    }

    //print the item of the deque from first to last//
    public void printDeque(){
        for (int i=0; i<Array.length;i++){
            System.out.print(Array[i]+" ");
        }
    }

    /*return true if deque if full,otherwise false"*/
    private boolean isFull(){
        return size==Array.length;
    }

    /*wheather to downsiz the deque.*/
    private boolean isSparse(){
        return Array.length>=16 && size <(Array.length/4);
    }

    /*add one circularly.*/
    private int addOne(int index){
        return (index+1)%Array.length;
    }

    /*minus one cicularly .*/
    private int minusOne (int index){
        return (index-1 +Array.length)%Array.length;
    }

    private void resize(int s) {
        //add item to the front of the array//
        T[] n = (T[]) new Object[s];
        int old=addOne(nextFirst);
        for (int i=0;i<size;i++){
            n[i]=Array[old];
            old=addOne(old);
        }
        Array=n;
        nextFirst=s-1;
        nextLast=size;
    }

    //add item of the type item to the front of the deque//
    public void addFirst(T x){
        if(isFull()){
            resize(size*2);
        }
        Array[nextFirst]=x;
        nextFirst=minusOne(nextFirst);
        size++;
    }

    //add an item of type item to the back of deque//
    public void addLast(T x){
        if(isFull()){
            resize(size*2);
        }
        Array[nextLast]=x;
        nextLast=addOne(nextLast);
        size++;
    }


    //remove and return item at the front of the deque//
    public T removeFirst(){
       if(isSparse()){
           resize(Array.length/2);
       }
       nextFirst=addOne(nextFirst);
       T remove=Array[nextFirst];
       Array[nextFirst]=null;
       size--;
       if(size!=0){
           size--;
       }
       return remove;
    }

    //remove and return item at the back of the deque//
    public T removeLast(){
        if(isSparse()){
            resize(Array.length/2);
        }
        nextLast=minusOne((nextLast));
        T remove=Array[nextLast];
        Array[nextLast]=null;
        size--;
        if(size!=0){
            size--;
        }
        return remove;
    }

    //get the item at the given index//
    public T get(int index){
        if(index>=size){
            return null;
        }
        return Array[index];
    }

    //creat a deep copy of other//
    //public ArrayDeque(ArrayDeque other){

    //}
}
