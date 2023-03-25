import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Pole extends JFrame implements ActionListener{
    Wykres wykres=new Wykres();
    ArrayList<JTextArea> do_wprowadzania=new ArrayList<>();
    ArrayList<JCheckBox> cheki=new ArrayList<>();
    ArrayList<Integer> wprowadzone=new ArrayList<>();
   static ArrayList<Slupek> slupki=new ArrayList<>();
    ArrayList<Color> kolory = new ArrayList<>();
    static float max=0;

    Pole(){
        Random los= new Random();
        for(int i=0;i<10;i++){

            kolory.add(new Color(los.nextInt(256),los.nextInt(256),los.nextInt(256),los.nextInt(256)));
        }

        super.setTitle("Wykres slupkowy");
        super.setLayout(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(new Dimension(700, 600));
        super.setLocationRelativeTo(null);

//WPROWADZANIE - tekst i CHEKI

        for(int i=0;i<10;i++){
            //text
            JTextArea x = new JTextArea(1,20);
            x.setBounds(10,80+30*i,100,20);
            do_wprowadzania.add(x);
            super.add(do_wprowadzania.get(i));


            //CHEKI
            JCheckBox box = new JCheckBox();
            box.setBounds(120,70+30*i,40,40);
            cheki.add(box);
            cheki.get(i).addActionListener(this);
            super.add(cheki.get(i));

            //WPROWADZONE INICJACJA
            wprowadzone.add(0);
        }

        super.add(wykres);
        super.setVisible(true);
        //AUTOZMIANA
        for(int i=0;i<do_wprowadzania.size();i++){
do_wprowadzania.get(i).getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        x();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        x();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        x();
    }
});
        }

    }

    public void x(){
        System.out.println("\nZaznaczonone i wartosci:");
        for (int i = 0; i < cheki.size(); i++) {
            if(cheki.get(i).isSelected()){
                if(do_wprowadzania.get(i).getText().equals("")){
                    System.out.println(i + "  " + "0");
                }
             else{
                 System.out.println(i + "  " + do_wprowadzania.get(i).getText());
                }
            }

        }
        rysuj();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
x();
    }
    public void rysuj(){
        try {
                slupki = new ArrayList<>();
                for (int i = 0; i < cheki.size(); i++) {
                    if (cheki.get(i).isSelected()) {
                        if ((do_wprowadzania.get(i).getText()).compareTo("") != 0) {
                            try {
                                wprowadzone.set(i, Integer.parseInt(do_wprowadzania.get(i).getText()));

                            } catch (NumberFormatException x) {
                                System.out.println("To nie jest liczba!");
                            }

                        }
                        else{
                            wprowadzone.set(i, 0);
                        }
                    }
                    else{wprowadzone.set(i, 0);}
                }
                max = 0;
                for (int i = 0; i < 10; i++) {
                    if (wprowadzone.get(i) > 0) {
                        slupki.add(new Slupek(wprowadzone.get(i), kolory.get(i)));
                    }
                    if (wprowadzone.get(i) > max) {
                        max = wprowadzone.get(i);
                    }
                }

            wykres = new Wykres();
            super.add(wykres);
            wykres.repaint();
        }catch (Exception e){};

    }
}
