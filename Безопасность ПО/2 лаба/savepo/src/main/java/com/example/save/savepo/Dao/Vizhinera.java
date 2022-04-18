package com.example.save.savepo.Dao;

import org.springframework.stereotype.Component;

@Component
public class Vizhinera {
    private String text;
    private String key;
    private String alf = alfavit();

    public Vizhinera() {
    }

    public Vizhinera(String text, String key, int choose) {
        StringBuilder sb;
        if(choose == 1) {
            sb = encryption(text, key);
        } else sb = decryption(text,key);
        System.out.println(sb.toString());
    }

    public StringBuilder encryption(String text, String key){
        this.text = text.toLowerCase();
        this.key = key;
        String bigkey = bigkey();
        int [] arrtextnumber = number(this.text);
        int [] arrkeynumber = number(bigkey);
        for (int i = 0; i<arrtextnumber.length; i++) arrtextnumber[i] = arrtextnumber[i] + arrkeynumber[i];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<arrtextnumber.length; i++) {
            int value = arrtextnumber[i];
            if (value >= alf.length()) value -= alf.length();
            sb.append(alf.charAt(value));
        }
        return sb;
    }

    public StringBuilder decryption(String text, String key){
        this.text = text.toLowerCase();
        this.key = key;
        String bigkey = bigkey();
        int [] arrtextnumber = number(this.text);
        int [] arrkeynumber = number(bigkey);
        for (int i = 0; i<arrtextnumber.length; i++) arrtextnumber[i] = arrtextnumber[i] - arrkeynumber[i];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<arrtextnumber.length; i++) {
            int value = arrtextnumber[i];
            if (value < 0) value = alf.length() - Math.abs(value);
            sb.append(alf.charAt(value));
        }
        return sb;
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
