public class ex2 {
    class Link {
        public  int iData;
        public double dData;
        public Link next;


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
        int count;

        public LinkList() {
            first = null;
            last = null;
        }

        public void insert(int iD, double dD) {
            Link newLink = new Link(iD, dD);
            if (first == null)
            {
                first = newLink;
                last = newLink;
                last.next = first;
                count++;
            }
            else
            {
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
            System.out.print("Список: ");

            do {
                current.displayLink();
                current = current.next;
            }
            while (current != last.next); {
                current = current.next;

            }
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
                System.out.println("Видалення пройшло успішно !");
                count--;
            }
            else {
                previous.next = current.next;
                System.out.println("Видалення пройшло успішно !");
                count--;
            }
        }

        public LinkList newLinkList (int a, int b) {
            LinkList newLinkList = new LinkList();
            if (a == 0 || b > count){
                System.out.println("Некоректно задані данні !");
                return null;
            }
            if (find(a).iData == first.iData && find(b).iData == last.iData){
                for (; a<=b; a++) {
                    newLinkList.insert(a,find(a).dData);
                }
                first = null;
                last = null;
                return newLinkList;
            }
            if (a<b){} else {
                int t = a;
                a = b ;
                b = t ;
            }
            if (a==b) {
                newLinkList.insert(a,find(a).dData);
                delete(a);
            }
            else {
                for (; a<=b; a++) {
                    newLinkList.insert(a,find(a).dData);
                    delete(a);
                }
            }
            return newLinkList;
        }
        public int length(){return count;}

    }
    void ex2() {
        LinkList newList = new LinkList();
        newList.insert(1, 0.1);
        newList.insert(2, 0.2);
        newList.insert(3, 0.3);
        newList.insert(4, 0.4);
        newList.insert(5, 0.5);
        System.out.println("");
        newList.displayList();
        System.out.println("");
        System.out.println("Всього елементів: "+newList.length());
        System.out.println("");
        LinkList newList2 = newList.newLinkList(2,5);
        newList.displayList();
        System.out.println("");
        System.out.println("Всього елементів: "+newList.length());
        newList2.displayList();
        System.out.println("");
        System.out.println("Всього елементів: "+newList2.length());

    }
}
