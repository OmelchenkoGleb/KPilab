public class ex1 {
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
        int count = 0;
        Link first;

        public LinkList() {
            first = null;
        }

        public void insertFirst(int iD, double dD) {
            Link newLink = new Link(iD, dD);
            newLink.next = first;
            first = newLink;
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
        public Link find(int key){
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

        public Link delete(int key) {
            Link current = first;
            Link previous = first;


            while (current.iData != key) {
                if (current.next == null) {
                    System.out.println("Такого елемента немає !");
                    return null;
                } else {
                    previous = current;
                    current = current.next;
                }
            }
            if (current == first) {
                first = first.next;
                System.out.println("Видалення пройшло успішно !");
                count--;


                if (first == null || first.next == null) {
                    System.out.println("Не можливо провести заміну елементів !");
                    return  null;
                }


                Link swap = new Link(first.iData,first.dData);
                Link swap2 = new Link(first.next.iData,first.next.dData);
                first.iData = swap2.iData;
                first.dData = swap2.dData;
                first.next.iData = swap.iData;
                first.next.dData = swap.dData;
                System.out.println("Заміна пройшла успішно !");
                return null;
            } else {
                previous.next = current.next;
                System.out.println("Видалення пройшло успішно !");
                count--;

                Link previous1 = previous.next;
                if (previous1 == null) {
                    System.out.println("Не можливо провести заміну елементів !");
                    return  null;
                }
                Link previous2 = previous1.next;
                if (previous2 == null) {
                    System.out.println("Не можливо провести заміну елементів !");
                    return  null;
                }


                Link swap  = new Link(previous1.iData,previous1.dData);
                Link swap2 = new Link(previous2.iData,previous2.dData);
                previous1.iData = swap2.iData;
                previous1.dData = swap2.dData;
                previous2.iData = swap.iData;
                previous2.dData = swap.dData;
                System.out.println("Заміна пройшла успішно !");
                return null;
            }


        }
        public int length(){return count;}


    }
    void ex1() {
        System.out.println("");
        LinkList newList = new LinkList();
        newList.insertFirst(1, 0.1);
        newList.insertFirst(2, 0.2);
        newList.insertFirst(3, 0.3);
        newList.insertFirst(4, 0.4);
        newList.insertFirst(5, 0.5);
        newList.displayList();
        System.out.println("Всього елементів: "+newList.length());
        System.out.println("");
        newList.delete(1);
        newList.displayList();
        System.out.println("Всього елементів: "+newList.length());
        System.out.println("");
        newList.delete(4);
        newList.displayList();
        System.out.println("Всього елементів: "+newList.length());
        System.out.println("");
        newList.delete(5);
        newList.displayList();
        System.out.println("Всього елементів: "+newList.length());
        System.out.println("");
        newList.delete(3);
        newList.displayList();
        System.out.println("Всього елементів: "+newList.length());
        System.out.println("");
        newList.delete(2);
        newList.displayList();
        System.out.println("Всього елементів: "+newList.length());

    }
}
