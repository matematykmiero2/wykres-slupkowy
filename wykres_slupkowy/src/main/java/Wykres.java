import javax.swing.*;
import java.awt.*;

class Wykres extends JPanel {

    int kat=0;
    Wykres(){
        this.setBounds(200,40,410,420);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        setVisible(true);

    }
    public void paint (Graphics g){
        super.paint(g);
        Graphics2D wykres_kolowy = (Graphics2D) g;
        int szerokosc=Pole.slupki.size();
        if(szerokosc==1){
            szerokosc=390;
        }
        else{
            szerokosc=(int)(300.0/szerokosc);
        }
        for(int i=0;i<Pole.slupki.size(); i++){
            int wysokosc=(int)(400*(Pole.slupki.get(i).wartosc/Pole.max));

            wykres_kolowy.setPaint(Pole.slupki.get(i).kolor);
            wykres_kolowy.fillRect(10+i*(szerokosc+10),420-wysokosc,szerokosc,wysokosc);
        }



    }

}