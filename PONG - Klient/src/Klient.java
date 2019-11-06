import java.io.*;
import java.net.*;
/**
 * Created by Marek Greczek on 2016-05-21.
 */

public class Klient {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String serverIP;
    private Socket connection;
    private String odbior = "";

    private Plansza plansza;

    public Klient(Plansza plansza){
        this.plansza = plansza;
    }

    public void Start (String host, int port) throws IOException {

        serverIP = host;
        try{
            Polaczenie(port);
            UstawStrumienie();
        }catch (EOFException e){
            e.printStackTrace();
        }


    }
    private void Polaczenie(int port) throws IOException{

        System.out.println("Laczenie z serwerem...");
        connection = new Socket(InetAddress.getByName(serverIP), port);
        System.out.println("Polaczono z: " +connection.getInetAddress().getHostName());
    }

    private void UstawStrumienie() throws IOException{

        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();

        input = new ObjectInputStream(connection.getInputStream());

        System.out.println("Strumienie ustawione!");
    }

    public void Wysylanie(String string) throws IOException{

            try {
                output.writeObject(string);
                output.flush();
                System.out.println("klient wyslano" +string);

            }
            catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void Odbieranie() throws IOException{
        try {
                odbior = (String) input.readObject();
                System.out.println("od serwera: " + odbior);
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
