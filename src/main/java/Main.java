public class Main {
    public static void main(String[] args) {
        MyArrayList<Object> list = new MyArrayList<>();
        for (int i = 1; i <=10 ; i++) {
            list.add((int)(Math.random() * 80) + 1);
        }
        list.print();
        MyArrayList<Object> list1 = list.sort();
        list1.print();
        list.clear();
        /*for (int i = 1; i <=10 ; i++) {
            list.add((int)(Math.random() * 80) + 1);
        }
        list.print();
        System.out.println(list.get(0));*/


        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        for (int i = 1; i <=5 ; i++) {
            linkedList.add((int)(Math.random() * 80) + 10);
        }
        linkedList.print();
        linkedList.removeAt(4);
        linkedList.print();
        /*System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(4));
        linkedList.set(10000,1);
        linkedList.addAt(20000,2);
        linkedList.print();
        MyLinkedList<Integer> linkedList1 = linkedList.sort();
        linkedList1.print();
        linkedList1.add(1000);
        linkedList1.print();*/
    }
}
