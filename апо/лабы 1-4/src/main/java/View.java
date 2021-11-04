public class View {
    public void Task1(Object object){
        System.out.println("Task 1 : ");
        System.out.println(object.toString());
    }
    public void Task2(double AnnotationMethodInvoke){
        System.out.println("Task 2 : ");
        System.out.println(AnnotationMethodInvoke);
    }
    public void Task3(String NameClass){
        System.out.println("Task 3 : ");
        System.out.println("Имя класса : " + NameClass);
    }
    public void Exercise4(StringBuffer ListFieldsNameTypeModifierClass){
        System.out.println("Exercise 4 : ");
        System.out.println("Список полей с именам, тип и модификатор доступа класса:");
        System.out.println(ListFieldsNameTypeModifierClass.toString());
    }
    public void Exercise6(String chekModifierClass){
        System.out.println("Exercise 6 : ");
        System.out.print("Модификатор доступа класса: ");
        System.out.println(chekModifierClass);
    }
    public void Exercise1(StringBuffer ListConstructOptions){
        System.out.println("Exercise 1:");
        System.out.println("Список конструкторів з параметрами класса: ");
        System.out.println(ListConstructOptions.toString());
    }
    public void Exercise5(StringBuffer ListInterface){
        System.out.println("Exercise 5:");
        System.out.println("Список інтерфейсів, реалізованих класом: ");
        System.out.println(ListInterface.toString());
    }
    public void Task4(Virtual proxy){
        System.out.println("Task 4 : ");
        proxy.getInterface();
//        proxy.setInterface();
    }
}
