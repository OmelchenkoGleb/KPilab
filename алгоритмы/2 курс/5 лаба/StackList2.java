

public class StackList2 {
    class Link {
        public  int iData;
        public int dData;
        public Link next;


        public Link(int iData, int dData) {
            this.iData = iData;
            this.dData = dData;
        }

        public void displayLink() {
            System.out.print(dData + ", ");

        }
    }

    class LinkList {
        Link first;
        Link last;
        int count;

        public LinkList() {
            first = null;
            last = null;
        }

        public void insert(int iD, int dD) {
            Link newLink = new Link(iD, dD);
            if (first == null) {
                first = newLink;
                last = newLink;
                last.next = first;
                count++;
            } else {
                newLink.next = first;
                last.next = newLink;
                last = newLink;
                count++;
            }

        }

        public void displayList() {
            Link current = first;
            if (current == null) {
                System.out.println("Немає елементів !");
                return;
            }

            do {
                current.displayLink();
                current = current.next;
            }
            while (current != last.next);
            {
                current = current.next;

            }
            System.out.println();
        }


        public Link get(int key) {
            Link current = first;
            while (current.iData != key) {
                if (current.next == null) {
                    System.out.println("Такого елемента немає !");
                    return null;
                } else {
                    current = current.next;
                }
            }

            return current;

        }

        public void delete(int key) {
            Link current = first;
            Link previous = first;
            while (current.iData != key) {
                if (current.next == null) {
                    System.out.println("Такого елемента немає !");
                    return;
                } else {
                    previous = current;
                    current = current.next;
                }
            }
            if (current == first) {
                first = first.next;
                count--;
            } else {
                previous.next = current.next;
                count--;
            }
        }
    }
    LinkList list;
    private int top;
    private int mSize;

    public StackList2(int m) {
        list = new LinkList();
        top=-1;
        this.mSize = m;
    }
    public void push(int element) {
        for (int i = top; i>=0; i--) {
            if ((int) list.get(i).dData == element) {
                System.out.println("Такой елемент уже существует!");
            }
        }
        if (isFull() == true){
            System.out.println("Стек полный!");
            return;
        }
        ++top;
        list.insert(top,element);

    }

    public int pop() {
        int a = (int) list.get(top).dData;
        list.delete(top);
        --top;
        return a;
    }


    public int peek() {
        return (int) list.get(top).dData;

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
        list.displayList();
    }

    public void ex(){
        if (isEmpty()==true){
            System.out.println("Стек пустой");
            return;
        }
//        вивести на екран всі парні елементи стеку, та знайти суму непарних.
        int sum = 0;
        int a = 0;
        System.out.print("Парные елементы стека: ");
        for (int i = top; i>=0; i--) {
            a = (int) list.get(i).dData;
            if(a % 2 == 0 ) {
                System.out.print(a + ", ");

            }

            else { sum += a;}
        }
        System.out.println("\nСума непарных елементов = "+sum);

    }
}
