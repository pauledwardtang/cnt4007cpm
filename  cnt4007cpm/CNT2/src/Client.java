/*
 * Authors: Paul Tang, Mark Ramasco
 * CNT4007 Project 2
 */
import java.net.*;
import java.io.*;
import java.util.Random;
import java.lang.Math;

//*******************************Client*******************************
//*1 socket between authentication server

 public class Client{
//---********Variables*********---

String SERVER1_IP = "10.128.82.53";
String SERVER2_IP = "127.0.0.1";

private Socket socket;
private String rcv_email_addr, snd_email_addr;
private String subject;
private String message_body;
private String MIME;
private Random rand;

//Readers Writers

private BufferedReader in;
private PrintWriter out;


//---********Constructor*********---
/*
 * This constructor is for testing: the message is given in command line arguments
 *
 * */
    Client(String receiver_email_address,
             String sender_email_address,
             String  subject,
             String main_text){
                
        rand = new Random();
        
        System.out.println("Constructing email...");
        constructSocket();
        //System.out.println(socket.getLocalAddress().toString());
        
        construct_EMAIL(receiver_email_address, sender_email_address, subject, main_text);


    }

//---********Functions*********---

/*
 *    Construct socket. Choose a random server
 *      Server 1 = 0, Server 2 = 1
 *
 */
void constructSocket(){
        try{
            if(Math.round(rand.nextFloat()) == 0) //Set IP to server 1
                socket = new Socket(SERVER1_IP, 25);
            else
                socket = new Socket(SERVER1_IP, 25);

        } catch (MalformedURLException e){
            System.out.println("Error: " + e);
        } catch (IOException e){
            System.out.println("Error: " + e);
        }
        
        //For testing purposes, closing the socket right away (plz delete)
        //socket.close();
}


/*
 *    Display menu
 *
 */
void displayMenu(){
}

 /*
 *    Gathers the fields necessary to construct an email message using MIME
 *
 */
void get_user_email(){
}
   

 /*
  *    Construct an email message using MIME. Alternatively, could push out one line at a time to the server so we can avoid parsing
  *     a bunch of text.
  *
  */
void construct_EMAIL(String receiver_email_address, String sender_email_address, String  subject, String main_text){
    
    MIME = ("From: "   + sender_email_address  + "\n"   +
            "To: "     + receiver_email_address+ "\n"   +
            "Subject: "+ subject               + "\n"   +
            "MIME-version: 1.0\n" +
            "Content-Transfer-Encoding: 7bit\n" +
            "Content-Type: text/plain\r\n\n" +
            "Test Message."    
           );
           
    System.out.println(MIME);
    
}

void writeToSocket(String message){
            try{
            //Initializing readers/writers
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
            
            //Header information is printed to the console, but not the file.
            //Loops until the end of the post message then begins writing to the file.
            
            /*
            String temp;
            boolean parse = false;
            while((temp = in.readLine()) != null)
            {
                System.out.println(temp);
                System.out.flush();
                if(parse)
                {
                    fileOut.write(temp);
                    fileOut.flush();
                }
                else if (temp.equals(""))
                    parse = true;
            }
            in.close();
            out.close();
            socket.close();
            */
        } catch(IOException e){
            System.out.println("Error: " + e);
        }
    
    
}
public static void main(String[] args)
{
        BufferedReader writer = new BufferedReader(new InputStreamReader(System.in));
        Client test = new Client(args[0], args[1], args[2], args[3]);
        //test.writeToSocket(test.MIME);
        
         while(true){
            System.out.println("Type something\n");
            try
            {
                test.writeToSocket(writer.readLine());
            }
            catch (IOException e){
                System.out.println(e);
            }
            
        }
        
}

 }
