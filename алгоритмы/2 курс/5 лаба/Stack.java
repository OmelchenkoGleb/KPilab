class Stack {
    private int mSize;
    private int[] stackArray;
    private int top;

    public Stack(int m) {
        this.mSize = m;
        stackArray = new int[mSize];
        top = -1;
    }

    public void push(int element) {
        for (int i = top; i>=0; i--) {
            if ( stackArray[i] == element) {
                System.out.println("Такой елемент уже существует!");
            }
        }
        if (isFull() == true){
            System.out.println("Стек полный!");
            return;
        }
        stackArray[++top] = element;
    }

    public int pop() {
        return stackArray[top--];
    }

    public int peek() {
        return stackArray[top];

    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == mSize - 1);
    }

    public void print(){
        if (isEmpty()==true){
            System.out.println("Стек пустой");
            return;
        }
        System.out.print("Елементы стека: ");
        for (int i = top; i>=0; i--) {
            if (i == 0){
                System.out.println(stackArray[i]+".");
                continue;
            }
            System.out.print(stackArray[i]+", ");
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
            if(stackArray[i] % 2 == 0 ) {
                System.out.print(stackArray[i] + ", ");
            }
            else sum += stackArray[i];
        }
        System.out.println("\nСума непарных елементом = "+sum);

    }
}
