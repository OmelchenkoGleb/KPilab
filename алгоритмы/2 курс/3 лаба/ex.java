//Вивести знаки арифметичних операцій, які входять в рядок символів, у зворотному порядку.

public class ex {
    StringBuilder sb = new StringBuilder();

    ex(String str){
//        iter(str);
       rec(str,0);
    }
    void iter (String str){
        for (int i =0; i<str.length(); i++) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '=' || str.charAt(i) == '*' || str.charAt(i) == '/' || str.charAt(i) == '%' ){
                sb.append(str.charAt(i));
            }
        }
        sb.reverse();
        System.out.println(sb);
    }
    void rec (String str, int index){
        if (index != str.length()) {
            if (str.charAt(index) == '+' || str.charAt(index) == '-' || str.charAt(index) == '=' || str.charAt(index) == '*' || str.charAt(index) == '/' || str.charAt(index) == '%') {
                sb.append(str.charAt(index));
            }
            index++;
            rec(str, index);
        }
        else {
            sb.reverse();
            System.out.println(sb);
        }
    }
}

