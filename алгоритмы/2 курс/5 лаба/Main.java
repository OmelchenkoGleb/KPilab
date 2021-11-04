public class Main {
    public static void main(String[] args) {
        Stack stack1 = new Stack(20);
        for (int i=1; i<=21; i++){
            stack1.push(i);
        }
        stack1.push(18);
        System.out.println("Попаем елемент: "+stack1.pop());
        System.out.println("Пикаем елемент: "+stack1.peek());
        stack1.print();
        stack1.ex();

        System.out.println("");
        StackList stack2 = new StackList(20);
        for (int i=21; i<=42; i++){
            stack2.push(i);
        }
        stack2.push(38);
        System.out.println("Попаем елемент: "+stack2.pop());
        System.out.println("Пикаем елемент: "+stack2.peek());
        stack2.print();
        stack2.deleteElement(40);
        stack2.ex();

        System.out.println("");
        StackList2 stack3 = new StackList2(20);
        for (int i=42; i<=63; i++){
            stack3.push(i);
        }
        stack3.push(51);
        stack3.print();
        System.out.println("Попаем елемент: "+stack3.pop());
        System.out.println("Пикаем елемент: "+stack3.peek());
        stack3.print();
        stack3.ex();
    }
}
