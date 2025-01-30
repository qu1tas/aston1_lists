/*
 *
 *   Класс, реализующий коллекцию LinkedList
 *   Подходит под любой тип данных
 *   Реализован в виде двухсвязного списка
 *
 *
 * */
public class MyLinkedList<T> {
    private int size;                                   //размерность коллекции
    private Node<T> head;                               //узел головы
    private Node<T> tail;                               //узел хвоста

    MyLinkedList() {                                    //конструктор, создающий список с 1 элементом
        this.size = 1;
        this.head = null;
        this.tail = null;
    }

    void add(T element) {                               //метод добавления элементов в конец коллекции
        Node<T> node = new Node<>(element);
        if (isEmpty()) {                                //если коллекция пустая, то первый элемент
            tail = node;                                //является как головой, так и хвостом
            head = node;
            head.setNext(tail);
        } else {                                        //иначе разширяем коллекцию, сохраняем ссылки
            size++;                                     //назначаем новый элемент хвостом
            node.setPrev(tail);
            tail.setNext(node);
            tail = node;
        }
    }

    void addFront(T element) throws NullPointerException{          //метод добавления элементов в начало коллекции
        try{
            Node<T> node = new Node<>(element);         //если коллекция пустая, то первый элемент
            if (isEmpty()) {                            //является как головой, так и хвостом
                tail = node;
                head = node;
                tail.setPrev(head);
            } else {                                    //иначе разширяем коллекцию, сохраняем ссылки
                size++;                                 //назначаем новый элемент головой
                node.setNext(head);
                head.setPrev(node);
                head = node;
            }
        }catch (NullPointerException e){                //ловим ошибку нулевого указателя
            System.out.println("Empty list");
        }
    }

    void addAt(T element, int index) throws NullPointerException{ //метод добавления элемента по индексу
        try{
            if (index == 0) addFront(element);          //если индекс соответствует началу коллекции, то переходим в addFront()
            else if (index == size) add(element);       //если индекс соответствует концу коллекции, то переходим в add()
            else {
                Node<T> node = head;                    //иначе проходим от начала коллекции до нужного индекса,
                for (int i = 0; i < index; i++) {       //расширяем коллекцию, переназначяем ссылки
                    node = node.next;
                }
                size++;
                Node<T> newnode = new Node<>(element);
                node.prev.setNext(newnode);
                newnode.setPrev(node.prev);
                newnode.setNext(node);
                node.setPrev(newnode);
            }
        }catch(NullPointerException e){                 //ловим ошибку нулевого указателя
            System.out.println("Empty list");
        }
    }

    void removeAt(int index) {                          //метод удаления элемента по индексу
        if (isEmpty()) return;
        if (index == 0) {                               //если элемент является головой, то переназначем ссылки,
            Node<T> temp = head.next;                   //удаляем старые значения из памяти, новый элемент становится головой
            head = null;
            head = temp;
            head.setNext(temp.next);
            head.setPrev(null);
            size--;
        } else if (index == size - 1) {                 //если элемент является хвостом, то переназначем ссылки,
            Node<T> temp = tail.prev;                   //удаляем старые значения из памяти, новый элемент становится хвостом
            tail = null;
            tail = temp;
            tail.setNext(null);
            tail.setPrev(temp.prev);
            size--;
        } else {                                         //иначе проходим от начала коллекции до нужного индекса,
            Node<T> node = head;                         //переназначаем ссылки, удаляем старые данные из памяти
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            node.next.setPrev(node.prev);
            node.prev.setNext(node.next);
            node = null;
            size--;
        }
    }

    T get(int index) {                                    //метод получения элемента по индексу
        if (isEmpty()) return null;
        Node<T> node = head;
        for (int i = 0; i < index; i++) {                  //проходим от начала до индекса
            node = node.next;
        }
        return node.data;
    }

    void set(T element, int index) {                    //метод подмены значения существующего звена списка,
        if (isEmpty()) return;
        Node<T> node = head;
        for (int i = 0; i < index; i++) {               //проходим от начала коллекции до индекса
            node = node.next;
        }
        node.setData(element);                          //устанавливаем новое значения, ссылки не меняются
    }

    private Node<T> split(Node<T> head) {
        Node<T> fast = head;
        Node<T> slow = head;

        // Move fast pointer two steps and slow pointer one
        // step until fast reaches the end
        while (fast != null && fast.next != null
                && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Split the list into two halves
        Node<T> temp = slow.next;
        slow.next = null;
        if (temp != null) {
            temp.prev = null;
        }
        return temp;
    }           //вспомогательные методы сортировки

    private Node<T> merge(Node<T> first, Node<T> second) {

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
        } else {
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

    private Node<T> MergeSort(Node<T> head) {

        // Base case: if the list is empty or has only one
        // node, it's already sorted
        if (head == null || head.next == null) {
            return head;
        }

        // Split the list into two halves
        Node<T> second = split(head);

        // Recursively sort each half
        head = MergeSort(head);
        second = MergeSort(second);

        // Merge the two sorted halves
        return merge(head, second);
    }

    MyLinkedList<T> sort() {                            //метод сортировки коллекции по возрастанию, при желании в новый экземпляр
        if (isEmpty()) return null;
        MyLinkedList<T> newList = new MyLinkedList<>();
        newList.head = MergeSort(this.head);
        newList.size = this.size;
        Node<T> temp = newList.head;
        for (int i = 0; i < size - 1; i++) {
            temp = temp.next;
        }
        newList.tail = temp;
        return newList;
    }

    void print() {                                      //метод печати элементов
        if (isEmpty()) return;
        Node<T> node = head;
        while (node.next != null){
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.print(node.data);
        System.out.println();
    }

    void reversePrint() {                               //метод печати элементов в обратном порядке
        if (isEmpty()) return;
        Node<T> node = tail;
        while (node.prev != null){
            System.out.print(node.data + " ");
            node = node.prev;
        }
        System.out.print(node.data);
        System.out.println();
    }

    boolean isEmpty() {                                 //булевый метод, проверяющий есть ли в коллекции элементы
        return head == null || tail == null;
    }

    void clear() {                                      //метод очищения всех элементов коллекции и удаления их из памяти
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            while (node.next != null) {                 //обнуляем все ссылки и значения
                node = node.next;
                node.prev = null;
            }
        }
        this.size = 1;                                  //дополнительно сбрасываем голову, хвост и длину коллекции
        this.head = null;
        this.tail = null;
    }

    int length() {                                      //метод, возвращающий количество элементов коллекции
        return size;
    }
/*
*
*  Приватный класс, реализующий узлы списка
*
*/
    private static class Node<T> {
        Node<T> prev;                                   //узел-ссылка на предыдущий элемент списка
        Node<T> next;                                   //узел-ссылка на следующий элемент списка
        T data;                                         //данные элемента

        Node(T data) {                                  //конструктор узла списка, по умолчанию ссылки нулевые
            this.data = data;
            this.prev = null;
            this.next = null;
        }

        void setData(T data) {                          //метод замены значения данных элемента
            this.data = data;
        }

        void setPrev(Node<T> node) {                    //метод замены ссылки на элемент до данного
            this.prev = node;
        }

        void setNext(Node<T> node) {                    //метод замены ссылки на элемент после данного
            this.next = node;
        }
    }
}

