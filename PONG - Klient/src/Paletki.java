import java.awt.*;
//import com.sun.javafx.geom.Rectangle;
/**
 * Created by Marek Greczek on 2016-03-14.
 */

public class Paletki{

    public static int wsp1_x = 20;
    public static int wsp1_y = 180;
    public static int wsp2_x = 560;
    public static int wsp2_y = 180;

    public static int wymiar_x = 10;
    public static int wymiar_y = 80;

    public static Plansza plansza;

    Rectangle paletka;
    public Paletki(int x, int y, int dl, int sz, Plansza plansza){

        this.plansza = plansza;
        paletka = new Rectangle(x, y, dl, sz);
    }

    public void rysuj(java.awt.Graphics graphics){
        graphics.setColor(new Color(200, 10, 20));
        graphics.fillRect(paletka.x, paletka.y, paletka.width, paletka.height);
        graphics.drawRect(paletka.x, paletka.y, paletka.width, paletka.height);

    }

    public boolean Kolizja (Rectangle p){
        return paletka.intersects(p);
    }

}

