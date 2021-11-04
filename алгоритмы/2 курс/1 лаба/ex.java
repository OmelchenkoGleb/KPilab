public class ex {

    public String test;
    public String delimeter = ",";
    public StringBuilder sb;
    public char[] charArray;
    public String[] Test;

    ex (String test) {
        this.test = test;
        Testing();
    }
    public void Testing () {
        // удаляю точку вконце строки
        test = test.substring(0, test.length()-1);
        // делю строку на массив строк по распределителю запятой
        Test = test.split(delimeter);
        //использую цикл для выделения отдельных строк
        for (int i=0; i<Test.length; i++){
            sb = new StringBuilder("");
            //перевожу слова в одинаковый регистр
            Test[i] = Test[i].toLowerCase();
            //создаю массив символов для сравнения в слове букв с последней и их удаление
            charArray = Test[i].toCharArray();
            for (int j=0; j<charArray.length; j++){
                if (charArray[j] != charArray[charArray.length-1]){
                    sb.append(charArray[j]);
                }
            }
            //добавляю последнюю букву с которой сравнивал и перезаписываю обратно в массив строк
            sb.append(charArray[charArray.length-1]);
            Test[i] = sb.toString();
        }
        //сравниваю все слова которые вышли после удаления в них букв с последним словом
        for (int i=0; i<Test.length-1; i++) {
            if (!Test[i].equals(Test[Test.length-1])) {
                System.out.println(Test[i]);
            }
        }
        System.out.println(Test[Test.length-1]);
        System.out.println();
    }
}
