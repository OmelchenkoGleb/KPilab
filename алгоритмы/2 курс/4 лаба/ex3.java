public class ex3 {
    class Link {
        public  int iData;
        public double dData;
        public Link next;
        public Link previous;


        public Link(int iData, double dData) {
            this.iData = iData;
            this.dData = dData;
        }

        public void displayLink() {
            System.out.print(" {" + iData + ", " + dData + "}");
        }
    }

    class LinkList {
        Link first;
        Link last;
        int count = 0;
        public LinkList() {
            first = null;
        }

        public void insertFirst(int iD, double dD) {
            Link newLink = new Link(iD, dD);
            Link temp = first;
            first = newLink;
            first.next = temp;
            if (count == 0) {
                last = first;
            }
            else {
                temp.previous = first;
            }
            count++;
        }
        public void insertLast(int iD, double dD) {
            Link newLink = new Link(iD, dD);
            if (count == 0){
                first = newLink;
            }
            else {
                last.next = newLink;
                newLink.previous = last;
            }
            last = newLink;
            count++;
        }



        public void displayList() {
            Link current = first;
            if (current == null){
                System.out.println("Немає елементів !");
                return;
            }
            System.out.print("Список: ");
            while(current != null) {
                current.displayLink();
                current = current.next;
            }
            System.out.println("");
        }


        public Link find(int key) {
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
            Link previous = first;
            Link current = first;
            while (current.iData != key) {
                if (current.next == null) {
                    System.out.println("Такого елемента немає !");
                } else {
                    previous = current;
                    current = current.next;
                }
            }

            if (current == first) {
                first = first.next;
                System.out.println("Видалення пройшло успішно !");
                count--;
            }
            else {
                previous.next = current.next;
                Link newCurrentLas = current.next;
                newCurrentLas.previous = previous.next;
                System.out.println("Видалення пройшло успішно !");
                count--;
            }
        }
        public int length(){return count;}

    }
    void ex3() {
        LinkList newList = new LinkList();
        newList.insertFirst(1, 0.1);
        newList.insertFirst(2, 0.2);
        newList.insertLast(3, 0.3);
        newList.insertLast(4, 0.4);
        newList.insertLast(5, 0.5);
        System.out.println("");
        System.out.println("Всього елементів: "+newList.length());
        newList.displayList();
        newList.delete(2);
        System.out.println("");
        System.out.println("Всього елементів: "+newList.length());
        newList.displayList();
        newList.delete(3);
        System.out.println("");
        System.out.println("Всього елементів: "+newList.length());
        newList.displayList();
        newList.delete(1);
        System.out.println("");
        System.out.println("Всього елементів: "+newList.length());
        newList.displayList();
        newList.delete(4);
        System.out.println("");
        System.out.println("Всього елементів: "+newList.length());
        newList.displayList();
        newList.delete(5);
        System.out.println("");
        System.out.println("Всього елементів: "+newList.length());
        newList.displayList();
    }
}
