import java.awt.*;
import java.awt.Shape;
/**
 * Created by Maek Greczek on 2016-03-09.
 */

public class Pileczka{

    public static int wsp_x = 300;
    public static int wsp_y = 170;
    public static final int promien = 15;
    int pr;

    Plansza plansza;
    Paletki paletki;
    Klient klient;

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



    public void Ruch() {

        pilka.move(pilka.x + wektor.width, pilka.y + wektor.height);
        pila = new Rectangle(pilka.x - pr + wektor.width, pilka.y - pr + wektor.height, pr * 2, pr * 2);
        if (pilka.x >= plansza.wymiar_x) {
            Zmien_kier_X();
        }
        if (pilka.x <= plansza.wsp_x) {
            Zmien_kier_X();
        }
        if (pilka.y >= plansza.wymiar_y) {
            Zmien_kier_Y();
        }
        if (pilka.y <= plansza.wsp_y) {
            Zmien_kier_Y();
        }
        if (plansza.Zwroc_paletke_1().Kolizja(pila)) {
            Zmien_kier_X();
        }
        if (plansza.Zwroc_paletke_2().Kolizja(pila)) {
            Zmien_kier_X();
        }
    }
    public void Ruch_klient(int x, int y){
        pilka.move(x, y);
        pila = new Rectangle(x, y , pr * 2, pr * 2);
    }


    void Zmien_kier_X(){
        wektor.width = -wektor.width;
    }
    void Zmien_kier_Y(){
        wektor.height = -wektor.height;
    }




    public void rysuj(Graphics graphics){

        graphics.setColor(new Color(0, 0, 0));
        graphics.fillOval(pilka.x, pilka.y, pr, pr);
        graphics.drawOval(pilka.x, pilka.y, pr, pr);
    }

}
