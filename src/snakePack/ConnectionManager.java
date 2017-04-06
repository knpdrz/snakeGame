package snakePack;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionManager {
    public static final int PORT = 31336;
    String hostName = "localhost";
    private Socket link;
    private Scanner streamFromServer;
    private PrintWriter streamToServer;
    
    public ConnectionManager(){
        accessServer();
    }
    
    private void accessServer(){
        /*link = null;
        try{
            link = new Socket(hostName, PORT);

            //info from server
            streamFromServer = new Scanner(link.getInputStream()); 
            
            //to send info to server
            streamToServer = new PrintWriter(
                        link.getOutputStream(),true); 
            
        }catch(IOException ex){
            System.out.println("E: error in accessServer");
        }*/
    }
    
    public void closeConnection(){
        /*streamToServer.println("theend");
        try{
            System.out.println("zamykam polaczenie");
            link.close(); 
        }catch(IOException ioEx){
            System.out.println("nie moge rozlaczyc");
            System.exit(1);
        }*/
    }
    
    public void sendDataToServer(String str){
       /* String response = "none";
        streamToServer.println(str);
        
        response = streamFromServer.nextLine();
        System.out.println("odpowiedz serwera: "+ response);
     */   
    }
}
