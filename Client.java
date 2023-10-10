import java.io.PrintWriter;
import java.net.*;
import java.io.*;

public class Client {
    Socket socket;

    BufferedReader br;
    PrintWriter out;
    public Client(){
        try{
            System.out.println("sending request to server");
            socket=new Socket("192.168.29.228",7777);
            System.out.println("connection done.");

            
       br=new BufferedReader(new InputStreamReader(socket.getInputStream()));

       out=new PrintWriter(socket.getOutputStream());

       startReading();
       startWriting();



        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void startReading(){
        //Thread-read the data and will kept the data
     
        Runnable r1=()->{
          System.out.println("reader started...");

          while(true){

              try{
                 String msg=br.readLine();
                 if(msg.equals("exit")){
                    System.out.println("server terminated the task");
                    break;
                 }
                System.out.println("server :"+msg);
             }catch(Exception e){
              e.printStackTrace();
            }
          }

      };

new Thread(r1).start();
}

public void startWriting(){
    //Thread-user will take data and send data to client
            Runnable r2=()->{
                System.out.println("writer started....");
    
                while(true){
    
                    try{
                        BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                      String content= br1.readLine();
                       out.println(content);
                       out.flush();
    
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
    
            };
            new Thread(r2).start();
        }






    public static void main(String[] args) {
        System.out.println("thiss is client application");
       new Client();
    }
}
