import javax.swing.JFrame;
import java.io.IOException;

/**
 * Created by Marek Greczek on 2016-03-09.
 */

public class Formatka extends JFrame {

    public static final int sz = 675, dl = 550;

    public static JFrame frame;
    public static Plansza plansza;

    public static void main(String[] args) throws IOException {
        frame = new JFrame("PONG KLIENT");
        frame.setSize(sz, dl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        Plansza plansza = new Plansza(frame, frame);
        frame.add(plansza);
        frame.setVisible(true);

    }

}
