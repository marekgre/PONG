import javax.swing.JFrame;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Created by Marek Greczek on 2016-03-09.
 */

public class Formatka extends JFrame {

    public static final int sz = 675, dl = 550;

    public static JFrame frame;
    public static Plansza plansza;
    public static Serwer serwer;
    public static String gracz1;
    public static String gracz2;

    public static void main(String[] args) throws IOException {

        gracz1=JOptionPane.showInputDialog("Gracz 1, Podaj imie:");
        gracz2=JOptionPane.showInputDialog("Gracz 2, Podaj imie:");
        frame = new JFrame("PONG");
        frame.setSize(sz, dl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        Plansza plansza1 = new Plansza(frame, frame);
        frame.add(plansza1);
        frame.setVisible(true);






    }

}
