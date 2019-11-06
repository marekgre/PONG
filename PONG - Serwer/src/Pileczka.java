import java.awt.*;
import java.awt.Shape;
import javax.swing.JOptionPane;
/**
 * Created by Maek Greczek on 2016-03-09.
 */

public class Pileczka{

    public static int wsp_x = 300;
    public static int wsp_y = 170;
    public static final int promien = 15;
    int pr;
    public int pkt1=0;
    public int pkt2=0;

    Plansza plansza;
    Paletki paletki;
    Formatka formatka;

    Dimension wektor;
    Point pilka;
    Rectangle pila;

    public Pileczka(int x, int y, int pr, Plansza plansza){

        this.plansza = plansza;
        this.pr = pr;
        pilka = new Point(x,y);


    }

    public void setWektor(int x, int y){

        wektor = new Dimension(x, y);

    }

    public void setPoint(int x, int y){

        pilka = new Point(x,y);
    }

    Point getPilka(){
        return pilka;
    }



    public void Ruch(){

        pilka.move(pilka.x + wektor.width, pilka.y + wektor.height);
        pila = new Rectangle(pilka.x - pr/3 + wektor.width, pilka.y - pr + wektor.height,pr*2, pr*2 );
        if (pilka.x >= plansza.wymiar_x){
            Zmien_kier_X();
            pkt2++;
            JOptionPane.showMessageDialog(null, "Wygrał: "+formatka.gracz2 + "  " + "(PUNKTACJA: " + formatka.gracz2 +" - "+ pkt2 + " pkt,  " +  formatka.gracz1 +" - "+ pkt1 + " pkt)");
        }
        if (pilka.x <= plansza.wsp_x){
            Zmien_kier_X();
            pkt1++;
            JOptionPane.showMessageDialog(null, "Wygrał: "+formatka.gracz1 + "  " + "(PUNKTACJA: " + formatka.gracz1 +" - "+ pkt1 + " pkt,  " +  formatka.gracz2 +" - "+ pkt2 + " pkt)");
        }
        if (pilka.y >= plansza.wymiar_y){
            Zmien_kier_Y();
        }
        if (pilka.y <= plansza.wsp_y){
            Zmien_kier_Y();
        }
        if (plansza.Zwroc_paletke_1().Kolizja(pila)){
            Zmien_kier_X();
        }
        if (plansza.Zwroc_paletke_2().Kolizja(pila)){
            Zmien_kier_X();
        }
    }

    void Zmien_kier_X(){
        wektor.width = -wektor.width;
    }
    void Zmien_kier_Y() { wektor.height = -wektor.height;}
    int Zwroc_x_pilki() {
        return pilka.x;}



    public void rysuj(Graphics graphics){

        graphics.setColor(new Color(0, 0, 0));
        graphics.fillOval(pilka.x, pilka.y, pr, pr);
        graphics.drawOval(pilka.x, pilka.y, pr, pr);
    }

}
