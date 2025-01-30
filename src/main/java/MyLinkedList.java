public class MyLinkedList<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;
    MyLinkedList(){
        this.size = 1;
        this.head = null;
        this.tail = null;
    }
    void add(T element){
        Node<T> node = new Node<>(element);
        if (isEmpty()){
            tail = node;
            head = node;
            head.setNext(tail);
        }
        else {
            size++;
            node.setPrev(tail);
            tail.setNext(node);
            tail = node;
        }
    }

    void addFront(T element){
        Node<T> node = new Node<>(element);
        if (isEmpty()){
            tail = node;
            head = node;
            tail.setPrev(head);
        }
        else{
            size++;
            node.setNext(head);
            head.setPrev(node);
            head = node;
        }
    }

    void addAt(T element, int index){
        if (index == 0) addFront(element);
        else if (index == size) add(element);
        else {
            Node<T> node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            size++;
            Node<T> newnode = new Node<>(element);
            node.prev.setNext(newnode);
            newnode.setPrev(node.prev);
            newnode.setNext(node);
            node.setPrev(newnode);
        }
    }

    void removeAt(int index){
        if (index == 0) {
            Node<T> temp = head.next;
           head = null;
           head = temp;
           head.setNext(temp.next);
           head.setPrev(null);
        }
        else if (index == size-1){
            Node<T> temp = tail.prev;
            tail = null;
            tail = temp;
            tail.setNext(null);
            tail.setPrev(temp.prev);
        }
        else {
            Node<T> node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            node.next.setPrev(node.prev);
            node.prev.setNext(node.next);
            node = null;
        }
    }

    T get(int index){
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            if (i == index) break;
            node = node.next;
        }
        return node.data;
    }

    void set(T element, int index){
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            if (i == index){
                node.setData(element);
            }
            else node = node.next;
        }
    }

    static Node split(Node head) {
        Node fast = head;
        Node slow = head;

        // Move fast pointer two steps and slow pointer one
        // step until fast reaches the end
        while (fast != null && fast.next != null
                && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Split the list into two halves
        Node temp = slow.next;
        slow.next = null;
        if (temp != null) {
            temp.prev = null;
        }
        return temp;
    }

    static Node merge(Node first, Node second) {

        // If either list is empty, return the other list
        if (first == null)
            return second;
        if (second == null)
            return first;

        // Pick the smaller value between first and second
        // nodes
        if (first.data.hashCode() < second.data.hashCode()) {

            // Recursively merge the rest of the lists and
            // link the result to the current node
            first.next = merge(first.next, second);
            if (first.next != null) {
                first.next.prev = first;
            }
            first.prev = null;
            return first;
        }
        else {
            // Recursively merge the rest of the lists and
            // link the result to the current node
            second.next = merge(first, second.next);
            if (second.next != null) {
                second.next.prev = second;
            }
            second.prev = null;
            return second;
        }
    }

    static Node MergeSort(Node head) {

        // Base case: if the list is empty or has only one
        // node, it's already sorted
        if (head == null || head.next == null) {
            return head;
        }

        // Split the list into two halves
        Node second = split(head);

        // Recursively sort each half
        head = MergeSort(head);
        second = MergeSort(second);

        // Merge the two sorted halves
        return merge(head, second);
    }

    MyLinkedList<T> sort(){
        MyLinkedList<T> newlist = new MyLinkedList<>();
        newlist.head = MergeSort(this.head);
        newlist.size = this.size;
        Node<T> temp = newlist.head;
        for (int i = 0; i < size-1; i++) {
            temp = temp.next;
        }
        newlist.tail = temp;
        return newlist;
    }

    void print(){
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            if (node == null) break;
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }
    void reversePrint(){
        Node<T> node = tail;
        for (int i = size-1; i >= 0; i--) {
            if (node == null) break;
            System.out.print(node.data + " ");
            node = node.prev;
        }
        System.out.println();
    }

    boolean isEmpty(){
        return head == null && tail == null;
    }

    void clear(){
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            while (node.next != null){
            node = node.next;
            node.prev = null;
            }
        }
        this.size = 1;
        this.head = null;
        this.tail = null;
    }

    int length(){
        return size;
    }

    private static class Node<T>{
        Node<T> prev;
        Node<T> next;
        T data;
        Node(T data){
            this.data = data;
            this.prev = null;
            this.next = null;
        }

        void setData(T data){
            this.data = data;
        }

        void setPrev(Node<T> node){
            this.prev = node;
        }

        void setNext(Node<T> node){
            this.next = node;
        }
    }
}

