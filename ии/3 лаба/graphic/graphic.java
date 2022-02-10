package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class graphic extends JFrame  {
    String f = "";
    double x1;
    double x2;
    double y1;
    double y2;
    TextField Tf;
    TextField Tx1;
    TextField Tx2;
    TextField Ty1;
    TextField Ty2;
    JFrame JF;
    JPanel JP;
    JButton b;
    JLabel er;
    public graphic(){
        JF = new JFrame();
        JF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JP = new JPanel();
        JF.add(JP);
        JP.setBackground(Color.yellow);
        JP.setLayout(null);
        b = new JButton("Ок");
        JF.setSize(320, 320);
        JF.setTitle("Строим графики");
        Tf = new TextField();
        Tx1 = new TextField("", 40);
        Tx2 = new TextField("", 40);
        Ty1 = new TextField("", 40);
        Ty2 = new TextField("", 40);
        Tf.setLocation(60, 60);
        Tf.setSize(200, 20);
        Tx1.setLocation(70, 110);
        Tx2.setLocation(190, 110);
        Tx1.setSize(50, 20);
        Tx2.setSize(50, 20);
        JP.add(Tx1);
        JP.add(Tx2);
        Ty1.setLocation(70, 155);
        Ty2.setLocation(190, 155);
        Ty1.setSize(50, 20);
        Ty2.setSize(50, 20);
        JP.add(Ty1);
        JP.add(Ty2);
        JP.add(Tf);
        JLabel Lf = new JLabel("Введите функцию:");
        JLabel Lfx = new JLabel("F(x) =");
        JLabel Lx1 = new JLabel("X от:");
        JLabel Lx2 = new JLabel("до:");
        JLabel Ly1 = new JLabel("Y от:");
        JLabel Ly2 = new JLabel("до:");
        Lf.setLocation(20, 20);
        Lf.setSize(140, 20);
        JP.add(Lf);
        Lfx.setLocation(10, 60);
        Lfx.setSize(40, 20);
        JP.add(Lfx);
        Lx1.setLocation(14, 100);
        Lx1.setSize(40, 20);
        JP.add(Lx1);
        Lx2.setLocation(150, 100);
        Lx2.setSize(40, 20);
        JP.add(Lx2);
        Ly1.setLocation(14, 145);
        Ly1.setSize(40, 20);
        JP.add(Ly1);
        Ly2.setLocation(150, 145);
        Ly2.setSize(40, 20);
        JP.add(Ly2);
        b.setLocation(190, 180);
        b.setSize(60, 50);
        JP.add(b);
        er = new JLabel("");
        er.setLocation(10, 210);
        er.setSize(170, 20);
        JP.add(er);
        JF.setVisible(true);
        JF.setResizable(false);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f = Tf.getText();
                if (!f.equals("")) {
                    set("");
                    x1 = Double.parseDouble(Tx1.getText());
                    x2 = Double.parseDouble(Tx2.getText());
                    try {
                        if (x1 < x2) {
                            set("");
                            y1 = Double.parseDouble(Ty1.getText());
                            y2 = Double.parseDouble(Ty2.getText());
                            try {
                                if (y1 < y2) {
                                    set("");
                                    set("УРА!");
                                    try {
                                        Paint p = new Paint(x1, x2, y1, y2, f);
                                    } catch (Exception w3) {
                                        set("класс Paint - говно!");
                                        System.out.println(w3);
                                    }
                                } else {
                                    set("некорректный интервал Y");
                                }
                            } catch (Exception w1) {
                                set("некорректные координаты");
                            }
                        } else {
                            set("некорректный интервал X");
                        }
                    } catch (Exception w2) {
                        set("некорректные координаты");
                    }
                } else {
                    set("ВВедите функцию");
                }
            }
        });
    }
    public void set(String s) {
        er.setText(s);
    }
}
