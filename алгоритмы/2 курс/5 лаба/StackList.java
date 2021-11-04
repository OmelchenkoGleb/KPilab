import java.util.LinkedList;

public class StackList {
    LinkedList<Integer> list;
    private int top;
    private int mSize;

    public StackList(int m) {
        list = new LinkedList<>();
        top=-1;
        this.mSize = m;
    }
    public void push(int element) {
        for (int i = top; i>=0; i--) {
            if ( list.get(i) == element) {
                System.out.println("Такой елемент уже существует!");
            }
        }
        if (isFull() == true){
            System.out.println("Стек полный!");
            return;
        }
        ++top;
        list.add(top,element);

    }

    public int pop() {
        int a = list.get(top);
        list.remove(top);
        --top;
        return a;
    }


    public int peek() {
        return list.get(top);

    }

    public void deleteElement(int element) {
        for (int i = top; i>=0; i--) {
            if (list.get(i) == element) {
                list.remove(i);
                --top;
            }
        }
        System.out.println("Удалён елемент: "+element);
    }
    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == mSize);
    }

    public void print(){
        if (isEmpty()==true){
            System.out.println("Стек пустой");
            return;
        }
        System.out.print("Елементы стека: ");
        for (int i = top; i>=0; i--) {
            if (i == 0){
                System.out.println(list.get(i)+".");
                continue;
            }
            System.out.print(list.get(i)+", ");
        }
    }

    public void ex(){
        if (isEmpty()==true){
            System.out.println("Стек пустой");
            return;
        }
//        вивести на екран всі парні елементи стеку, та знайти суму непарних.
        int sum = 0;
        System.out.print("Парные елементы стека: ");
        for (int i = top; i>=0; i--) {
            if(list.get(i) % 2 == 0 ) {
                System.out.print(list.get(i) + ", ");
            }
            else sum += list.get(i);
        }
        System.out.println("\nСума непарных елементов = "+sum);

    }
}
