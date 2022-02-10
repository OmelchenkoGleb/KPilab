public class Vizhinera {
    private String text;
    private String key;
    private String alf = alfavit();

    public Vizhinera(String text, String key, int choose) {
        this.text = text.toLowerCase();
        this.key = key;
        String bigkey = bigkey();
        int [] arrtextnumber = number(this.text);
        int [] arrkeynumber = number(bigkey);
        if (choose == 1) for (int i = 0; i<arrtextnumber.length; i++) arrtextnumber[i] = arrtextnumber[i] + arrkeynumber[i]; // encryption
        if (choose == 2) for (int i = 0; i<arrtextnumber.length; i++) arrtextnumber[i] = arrtextnumber[i] - arrkeynumber[i]; // decryption
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<arrtextnumber.length; i++) {
            int value = arrtextnumber[i];
            if (choose == 1) { if (value >= alf.length()) value -= alf.length(); } // encryption
            if (choose == 2) { if (value < 0) value = alf.length() - Math.abs(value); } // decryption
            sb.append(alf.charAt(value));
        }
        System.out.println(sb);
    }

    private String alfavit() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (int i = 0; i<26; i++) sb.append((char) ('a' + i));
        return sb.toString();
    }

    private String bigkey(){
        StringBuilder keyt = new StringBuilder();
        int x = (int)Math.ceil((double)text.length()/key.length());
        for (int i = 0; i<x; i++) keyt.append(key);
        return keyt.toString();
    }

    private int [] number(String string){
        int [] arrnumber = new int[text.length()];
        for (int i = 0; i<text.length(); i++) for (int j = 0; j<alf.length(); j++) if (string.charAt(i) == alf.charAt(j)) arrnumber[i] = j;
        return arrnumber;
    }


}
