public class MyArrayList<T> {
    private T[] list;
    @SuppressWarnings("unchecked")
    MyArrayList(){
        this.list = (T[]) new Object[10];
    }

    void add(T element){
        if (full()){
            int temp = list.length;
            increaseSize();
            list[temp-1] = element;
        }
        else {
            for (int i = 0; i < list.length; i++) {
                if (this.list[i] == null) {
                    this.list[i] = element;
                    break;
                }
            }
        }
    }

    void addAt(T element, int index){
        if (index >= this.list.length){
            add(element);
        }
        else {
            for (int i = this.list.length-1; i >= index; i--) {
                if (list[i] == null) continue;
                list[i+1] = list[i];
            }
            this.list[index] = element;
        }
    }

    void removeAt(int index){
        for (int i = index; i < list.length-1; i++) {
            if (list[i] == null) break;
            list[i] = list[i+1];
        }
    }

    void set(T element, int index){
        list[index] = element;
    }

    T get(int index){
        return list[index];
    }

    @SuppressWarnings("unchecked")
    private void increaseSize(){
        T[] newArray = (T[]) new Object[list.length + list.length/5]; // Новый массив
        System.arraycopy(list, 0, newArray, 0, list.length); // Копирование данных
        list = newArray; // Переназначение
    }

    MyArrayList<T> sort(){
        int count = size();
        for (int i = count-1; i >= 1; i--) {
            for (int j = 0; j < count-1; j++) {
                if(list[j] == null) return this;
                if (list[j].hashCode() > list[j+1].hashCode()){
                    T dummy = list[j];
                    list[j] = list[j+1];
                    list[j+1] = dummy;
                }
            }
        }
        return this;
    }
    @SuppressWarnings("unchecked")
    void clear(){
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) break;
            list[i] = null;
        }
        System.out.println();
    }

    private boolean full(){
        for (int i = 0; i < list.length-1; i++) {
            if (list[i] == null) return false;
        }
        return true;
    }

    boolean isEmpty(){
        return list[0] == null;
    }

    void print(){
        for (T t : list) {
            if (t == null) break;
            System.out.print(t + " ");
        }
        System.out.println();
    }
    int size(){
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) return i;
        }
        return list.length;
    }
    int trueSize(){
        return list.length;
    }
}
