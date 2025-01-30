/*
*
*   Класс, реализующий коллекцию ArrayList
*   Подходит под любой тип данных
*
* */
public class MyArrayList<T> {
    private T[] list; //массив для хранения данных

    @SuppressWarnings("unchecked")
    MyArrayList() {                         //конструктор для создания экземпляра класса, любой тип
        this.list = (T[]) new Object[10];   //данных, размер 10 элементов
    }

    void add(T element) {                   //метод для добавления элемента в конец массива
        if (full()) {                       //проверяем на заполненность, в случае переполнения увеличиваем размер
            int temp = list.length;
            increaseSize();
            list[temp - 1] = element;
        } else {                            //если место есть, кладем элемент в конец массива
            for (int i = 0; i < list.length; i++) {
                if (this.list[i] == null) {
                    this.list[i] = element;
                    break;
                }
            }
        }
    }

    void addAt(T element, int index) {      //метод для добавления элемента по индексу
        if (index >= this.list.length) {    //если указали конец массива, перенаправляем в add()
            add(element);
        } else {                            //иначе ищем указанный индекс, двигаем массив, добавляем элемент
            for (int i = this.list.length - 1; i >= index; i--) {
                if (list[i] == null) continue;
                list[i + 1] = list[i];
            }
            this.list[index] = element;
        }
    }

    void removeAt(int index) {              //метод для удаления элемента по индексу
        for (int i = index; i < list.length - 1; i++) {
            if (list[i] == null) break;
            list[i] = list[i + 1];
        }
    }

    void set(T element, int index) {        //замена значения у существующего элемента
        list[index] = element;
    }

    T get(int index) {                      //получить элемент по индексу
        return list[index];
    }

    MyArrayList<T> sort() {                 //метод сортировки коллекции по возрастанию, при желании в новый экземпляр
        int count = size();
        for (int i = count - 1; i >= 1; i--) {
            for (int j = 0; j < count - 1; j++) {
                if (list[j] == null) return this;
                if (list[j].hashCode() > list[j + 1].hashCode()) {
                    T dummy = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = dummy;
                }
            }
        }
        return this;
    }

    void clear() {                          //метод очищения коллекции
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) break;
            list[i] = null;
        }
        System.out.println();
    }

    void print() {                          //метод для печати всех непустых элементов
        for (T t : list) {
            if (t == null) break;           //если прошли все заполненные элементы, выходим из цикла
            System.out.print(t + " ");
        }
        System.out.println();
    }

    int size() {                            //метод для получения количества заполненных элементов
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) return i;  //если прошли все заполненные элементы, выходим из цикла
        }
        return list.length;
    }

    int trueSize() {                        //метод для получения реальной размерности массива, включая пустые места
        return list.length;
    }

    boolean isEmpty() {                     //булевый метод, проверяющий есть ли в коллекции элементы
        return list[0] == null;
    }

    private boolean full() {                //приватный метод проверки на заполненность
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i] == null) return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private void increaseSize() {           //приватный метод для увелечения размерности коллекции
        T[] newArray = (T[]) new Object[list.length + list.length / 5]; // Новый массив
        System.arraycopy(list, 0, newArray, 0, list.length); // Копирование данных
        list = newArray; // Переназначение
    }
}
