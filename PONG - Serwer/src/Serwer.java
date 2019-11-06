import java.io.*;
import java.net.*;
/**
 * Created by Marek Greczek on 2016-05-21.
 */
public class Serwer extends Thread {

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private ServerSocket server;
    private Socket connection;
    private String odbior = "";

    Pileczka pileczka;
    Plansza plansza;

    public Serwer(Plansza plansza) throws IOException{

        this.plansza = plansza;


    }

    public void Start(int port) throws IOException {

        try {
            server = new ServerSocket(port);

            while (true){
                Polaczenie();
                UstawStrumienie();
                //Odbieranie();
            }
        }catch (EOFException e){
            System.out.println("Polaczenie zerwane!!!");
        }
    }


    private void Polaczenie() throws IOException{

            System.out.println("Czekam na polaczenie...");
            connection = server.accept();
            System.err.println("Polaczono z " +connection.getInetAddress().getHostName());

    }

    private void UstawStrumienie() throws IOException {

        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();

        input = new ObjectInputStream(connection.getInputStream());

        System.out.println("Strumienie ustawione!");
    }

    public void Wysylanie(String string) throws IOException{



        try {
            output.writeObject(string);
            output.flush();
            System.out.println("Serwer wyslano " +string);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Odbieranie() throws IOException{

            try {

                odbior = (String) input.readObject();
                System.out.println("serwer: " +odbior);

            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
    public int Odbior(){
        int x = Integer.parseInt(odbior);
        return x;
    }
}
