import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;
/**
 * Created by Marek Greczek on 2016-03-14.
 */

public class Plansza extends JPanel implements KeyListener {

    public static int wsp_x = 20;
    public static int wsp_y = 20;
    public static final int wymiar_x = 600;
    public static final int wymiar_y = 350;

    public static int pilka_ruch_x = 3;
    public static int pilka_ruch_y = 2;
    boolean gra = false;

    private Paletki gracz1;
    private Paletki gracz2;
    private Pileczka pilka;
    private Klient klpilkax;
    private Klient klpilkay;
    private Klient klpaly;

    public int odebrana_pilka_x;
    public int odebrana_pilka_y;
    public int odebrana_paletka_y;


    public Plansza(Frame frame1, Frame frame2){

        gracz1 = new Paletki(dimension.width - gracz1.wsp1_x, dimension.height - gracz1.wsp1_y, gracz1.wymiar_x, gracz1.wymiar_y, this );
        gracz2 = new Paletki(dimension.width - gracz2.wsp2_x, dimension.height - gracz2.wsp2_y, gracz2.wymiar_x, gracz2.wymiar_y, this );
        pilka = new Pileczka(dimension.width - pilka.wsp_x, dimension.height - pilka.wsp_y, pilka.promien, this);
        klpilkax = new Klient(this);
        klpilkay = new Klient(this);
        klpaly = new Klient(this);
        frame1.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode()==KeyEvent.VK_DOWN){
                    if(gracz1.paletka.y < (wymiar_y + wsp_y) - gracz1.wymiar_y)
                        gracz1.paletka.y  = gracz1.paletka.y + 5;
                }
                if (e.getKeyCode()==KeyEvent.VK_UP){
                    if(gracz1.paletka.y > wsp_y)
                        gracz1.paletka.y  = gracz1.paletka.y - 5;
                }
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    odbior_pilka_x.start();
                    odbior_pilka_y.start();
                    odbior_paletka_y.start();
                    wyslij.start();

                    start();
                }
                if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    stop();
                }
                if (e.getKeyCode()==KeyEvent.VK_1){

                }
                if (e.getKeyCode()==KeyEvent.VK_2){
                    Polacz_pilka_x();
                    Polacz_pilka_y();
                    Polacz_pal_y();
                }
                repaint();
            }
        });

        frame2.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent a) {

                if (a.getKeyCode()==KeyEvent.VK_S){
                    if(gracz2.paletka.y < (wymiar_y + wsp_y) - gracz2.wymiar_y)
                        gracz2.paletka.y  = gracz2.paletka.y + 5;
                }
                if (a.getKeyCode()==KeyEvent.VK_W) {
                    if (gracz2.paletka.y > wsp_y)
                        gracz2.paletka.y = gracz2.paletka.y - 5;
                }
                repaint();
            }
        });
    }

    Dimension dimension = new Dimension(wymiar_x, wymiar_y);
    public void setSize(Dimension p) {

        super.setSize(p);
        dimension = new Dimension(p.width-100, p.height-100);
    }
    
    public void start()
    {
    	if(gra == false){

    		gram.start();
    	}
    }

    public void stop(){
        if (gra == true){

            gram.stop();
        }
    }

    public void Polacz_pilka_x(){
        try {
            klpilkax.Start("127.0.0.1", 6000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Polacz_pilka_y(){
        try {
            klpilkay.Start("127.0.0.1", 6001);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Polacz_pal_y(){
        try {
            klpaly.Start("127.0.0.1", 6002);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   /* public Dimension getPlansza(){
        return dimension;
    }*/

    Thread gram = new Thread(new Runnable() {
        public void run() {

            while (true){
                pilka.Ruch_klient(odebrana_pilka_x, odebrana_pilka_y);
                repaint();
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    System.out.println("blad, cos sie wywalilo");
                }
                repaint();

            }

        }
    });
    Thread odbior_pilka_x = new Thread(new Runnable() {
        public void run() {
            while (true){
                try {
                    klpilkax.Odbieranie();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                odebrana_pilka_x = klpilkax.Odbior();
                repaint();
                try{
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    System.out.println("watek odbior_pilka_x sie wywalil");
                }
                repaint();
            }
        }
    });
    Thread odbior_pilka_y = new Thread(new Runnable() {
        public void run() {
            while (true){
                try {
                    klpilkay.Odbieranie();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                odebrana_pilka_y = klpilkay.Odbior();
                repaint();
                try{
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    System.out.println("watek odbior_pilka_y sie wywalil");
                }
                repaint();
            }
        }
    });
    Thread odbior_paletka_y = new Thread(new Runnable() {
        public void run() {
            while (true){
                try {
                    klpaly.Odbieranie();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                odebrana_paletka_y = klpaly.Odbior();
                repaint();
                try{
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    System.out.println("watek odbior_paletka_y sie wywalil");
                }
                gracz2.paletka.y = odebrana_paletka_y;
                repaint();
            }
        }
    });
    Thread wyslij = new Thread(new Runnable() {
        public void run() {
            String paletka_y= "";
            while(true){
                paletka_y=Integer.toString(Zwroc_y_paletki1());
                try{
                    klpaly.Wysylanie(paletka_y);
                }catch (IOException e){
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    System.out.println("watek wysylanie sie popieprzyl");
                }
            }
        }
    });

    public Paletki Zwroc_paletke_1(){
        return gracz1;
    }
    public Paletki Zwroc_paletke_2() { return gracz2; }
    public int Zwroc_y_paletki1() {return gracz1.paletka.y;}

    public void rysuj(Graphics graphics){
        graphics.setColor(new Color(0, 150, 70));
        graphics.fillRect(wsp_x, wsp_y, wymiar_x, wymiar_y);
        graphics.drawRect(wsp_x, wsp_y, wymiar_x, wymiar_y);
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        rysuj(graphics);
        gracz1.rysuj(graphics);
        gracz2.rysuj(graphics);
        pilka.rysuj(graphics);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}