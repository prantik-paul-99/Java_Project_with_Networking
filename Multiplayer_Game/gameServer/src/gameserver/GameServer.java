
package gameserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class GameServer {

static HashMap<String,String> login_hm = new HashMap<>();
static HashMap<String,String> signup_hm = new HashMap<>();
static HashMap<String, WorkerThread> threads = new HashMap<String, WorkerThread>();
static HashMap<WorkerThread, WorkerThread> pairs = new HashMap<WorkerThread, WorkerThread>();
static HashMap<Integer,String> usernames = new HashMap<Integer, String>();
static String[] pairing = new String[2];
static int player1_score_snake = 0, player2_score_snake= 0;
static int player1_score_bird = 0, player2_score_bird= 0;
static int player1_score_word = 0, player2_score_word= 0;
static int player1_score =0, player2_score= 0;

    
    public static void main(String argv[]) throws Exception
    {
        // Open the file
        FileInputStream fstream = new FileInputStream("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\gameServer\\src\\gameserver\\SignUpList.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console
            
            String[] line  = strLine.split("-");
            signup_hm.put(line[1], line[2]);
        }

        //Close the input stream
        fstream.close();
        
	int workerThreadCount = 0;
        ServerSocket welcomeSocket = new ServerSocket(7179);
        int id=1;
        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            WorkerThread wt = new WorkerThread(connectionSocket, id);
            Thread t = new Thread(wt);
            t.start();
            //System.out.println("hello");
            workerThreadCount++;
            System.out.println("Client [" + id + "] is now connected. No. of worker threads = " + workerThreadCount);
            id++;
        }		
   
    }
}
        // TODO code application logic here
    
    
class WorkerThread implements Runnable
{
    private Socket connectionSocket;
    private InputStream is;
    private OutputStream os;
    private int id = 0;
    private String msgFrmClnt;
    BufferedReader inFromClient;
    PrintWriter outToClient;
    Writer output;
    public WorkerThread(Socket s, int id)
    {
        this.connectionSocket = s;
        try {
            this.is = this.connectionSocket.getInputStream();
            this.os = this.connectionSocket.getOutputStream();
            output = new BufferedWriter(new FileWriter("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\gameServer\\src\\gameserver\\SignUpList.txt", true));
        } catch (Exception e) {
            System.err.println("Sorry. Cannot manage client [" + id + "] properly.");
        }
	this.id = id;
    }
    @Override
    public void run()
    {
        while (true) {
            
            
            
            try {
                inFromClient = new BufferedReader(new InputStreamReader(this.is));
                outToClient = new PrintWriter(this.os,true);
                
                msgFrmClnt = inFromClient.readLine();
                if(msgFrmClnt.equals("L-null"))
                {
                    outToClient.println("L-invalid");
                }
                else if(msgFrmClnt.equals("S-null"))
                {
                    outToClient.println("S-invalid");
                }
                else if(msgFrmClnt.charAt(0) == 'L'){
                    String[] user = msgFrmClnt.split("-");
                    if(GameServer.signup_hm.containsKey(user[1]))
                    {
                        if (GameServer.login_hm.containsKey(user[1]))
                        {
                            outToClient.println("L-in");
                        }
                        // checking the user's name and pass in the directory of the players that already signed up
                        else if (user[2].equals(GameServer.signup_hm.get(user[1]))){
                            GameServer.login_hm.put(user[1], user[2]); // if login is successful, user is enrolled in login list
                            GameServer.threads.put(user[1],this);
                            GameServer.usernames.put(this.id, user[1]);
                            outToClient.println("L-yes");
                        }
                        
                        else
                            outToClient.println("L-no");
                        
                    }
                    else if (user[2].equals("out"))
                        {
                            String[] userinfo = user[1].split("Z");
                            if(GameServer.login_hm.remove(userinfo[0],userinfo[1]))
                            {
                                System.out.println("log outdone");
                            }
                        }
                    else
                            outToClient.println("L-no");
                }
                
                else if (msgFrmClnt.charAt(0) == 'R')
                {
                    String opnt_name;
//                    GameServer.pairing[0] = GameServer.usernames.get(this.id);
                    String[] pair = msgFrmClnt.split("-");
                    if(pair[1].equals("request"))
                    {
                        opnt_name = pair[2];
//                        GameServer.pairs.put(this, GameServer.threads.get(pair[2]));
                        GameServer.threads.get(opnt_name).outToClient.println("R-request-"+GameServer.usernames.get(this.id));
                    }
                }
                else if (msgFrmClnt.charAt(0) == 'V') {
                    String[] snk_scr = msgFrmClnt.split("-");
                    if (GameServer.pairs.containsKey(this)) {
                        GameServer.player1_score_snake = Integer.parseInt(snk_scr[2]);
                        System.out.println("p1 "+GameServer.player1_score_snake);
                        GameServer.pairs.get(this).outToClient.println(msgFrmClnt);
                    } else if (GameServer.pairs.containsValue(this)) {
                        for (HashMap.Entry<WorkerThread, WorkerThread> entry : GameServer.pairs.entrySet()) {
                            if (this.equals(entry.getValue())) {
                                GameServer.player2_score_snake = Integer.parseInt(snk_scr[2]);
                                System.out.println("p2 "+GameServer.player2_score_snake);
                                entry.getKey().outToClient.println(msgFrmClnt);
                                break;
                            }
                        }
                    }
                }
            
                else if (msgFrmClnt.charAt(0) == 'A') //  request sender thread
                {
                    String[] pair = msgFrmClnt.split("-");
                    if(pair[2].equals("ok"))
                    {
                        GameServer.threads.get(pair[1]).outToClient.println("accepted");
                        
                        GameServer.pairs.put(this, GameServer.threads.get(pair[1]));
                        
                        
                        this.outToClient.println("#-YourTurn");
                        GameServer.threads.get(pair[1]).outToClient.println("#-OpponentsTurn");
                        
                    }
                    if(pair[2].equals("no"))
                    {
                        GameServer.threads.get(pair[1]).outToClient.println("denied");
                    }
                }
               
                else if (msgFrmClnt.charAt(0)=='S')
                {
                    
                    String[] info = msgFrmClnt.split("-");
                    if (GameServer.signup_hm.containsKey(info[1]))
                    {
                        outToClient.println("S-no");
                    }
                    else if (!info[1].equals("null") && !info[1].equals("null"))
                    {
                        GameServer.signup_hm.put(info[1], info[2]);
                        outToClient.println("S-yes");
                        output.append(msgFrmClnt+"\n");
                    }
                    else
                    {
                        outToClient.println("S-invalid");
                    }
                }
                else if(msgFrmClnt.equals("showlist")){
                    String listmsg = "P-"; //P for playerlist
                    for (HashMap.Entry<String,String> entry : GameServer.login_hm.entrySet())
                    {                            
                        listmsg=listmsg+entry.getKey()+"-";
                    }
                    outToClient.println(listmsg);
                }
                
                else if (msgFrmClnt.charAt(0)=='#')
                {
                    outToClient.println(msgFrmClnt);
                }
                
                else if (msgFrmClnt.charAt(0) == 'B')
                {
                    String[] brd_scr = msgFrmClnt.split("/");
                    if (GameServer.pairs.containsKey(this)) {
                        GameServer.player1_score_bird = Integer.parseInt(brd_scr[1]);
                        System.out.println("p1 bird "+GameServer.player1_score_bird);
                    } else if (GameServer.pairs.containsValue(this)) {
                        for (HashMap.Entry<WorkerThread, WorkerThread> entry : GameServer.pairs.entrySet()) {
                            if (this.equals(entry.getValue())) {
                                GameServer.player2_score_bird += Integer.parseInt(brd_scr[1]);
                                System.out.println("p2 bird "+GameServer.player2_score_bird);
                                break;
                            }
                        }
                    }
                }
                
                else if (msgFrmClnt.charAt(0) == 'T')
                {
                    String[] wrd_scr = msgFrmClnt.split("/");
                    if (GameServer.pairs.containsKey(this)) {
                        GameServer.player1_score_word = Integer.parseInt(wrd_scr[1]);
                        System.out.println("p1 word "+GameServer.player1_score_word);
                    } else if (GameServer.pairs.containsValue(this)) {
                        for (HashMap.Entry<WorkerThread, WorkerThread> entry : GameServer.pairs.entrySet()) {
                            if (this.equals(entry.getValue())) {
                                GameServer.player2_score_word = Integer.parseInt(wrd_scr[1]);
                                System.out.println("p2 word "+GameServer.player2_score_word);
                                break;
                            }
                        }
                    }
                }
                
                else if (msgFrmClnt.charAt(0)=='E')
                {
                    GameServer.player1_score = GameServer.player1_score_bird + GameServer.player1_score_snake + GameServer.player1_score_word;
                    GameServer.player2_score = GameServer.player2_score_bird + GameServer.player2_score_snake + GameServer.player2_score_word;
                    System.out.println("p1 "+GameServer.player1_score);
                    System.out.println("p2 "+GameServer.player2_score);
                    if (GameServer.pairs.containsKey(this)) {
                        this.outToClient.println("E-"+Integer.toString(GameServer.player2_score));
                    } else if (GameServer.pairs.containsValue(this)) {
                        for (HashMap.Entry<WorkerThread, WorkerThread> entry : GameServer.pairs.entrySet()) {
                            if (this.equals(entry.getValue())) {
                                this.outToClient.println("E-"+GameServer.player1_score);
                                break;
                            }
                        }
                    }
                }
                else if (msgFrmClnt.charAt(0)=='W')
                {
                    if (GameServer.pairs.containsKey(this)) {
                        GameServer.pairs.get(this).outToClient.println(msgFrmClnt);
                    } else if (GameServer.pairs.containsValue(this)) {
                        for (HashMap.Entry<WorkerThread, WorkerThread> entry : GameServer.pairs.entrySet()) {
                            if (this.equals(entry.getValue())) {
                                entry.getKey().outToClient.println(msgFrmClnt);
                                break;
                            }
                        }
                    }
                }
                else if (msgFrmClnt.equals("K"))
                {
                     if (GameServer.pairs.containsKey(this)) {
                        GameServer.pairs.get(this).outToClient.println(msgFrmClnt);
                    } else if (GameServer.pairs.containsValue(this)) {
                        for (HashMap.Entry<WorkerThread, WorkerThread> entry : GameServer.pairs.entrySet()) {
                            if (this.equals(entry.getValue())) {
                                entry.getKey().outToClient.println(msgFrmClnt);
                                break;
                            }
                        }
                    }
                }
                
                
                else if (msgFrmClnt.charAt(0)=='X')
                {
                    if (GameServer.pairs.containsKey(this)) {
                        GameServer.pairs.get(this).outToClient.println(msgFrmClnt);
                        GameServer.pairs.remove(this);
                    } else if (GameServer.pairs.containsValue(this)) {
                        for (HashMap.Entry<WorkerThread, WorkerThread> entry : GameServer.pairs.entrySet()) {
                            if (this.equals(entry.getValue())) {
                                entry.getKey().outToClient.println(msgFrmClnt);
                                GameServer.pairs.remove(entry.getKey());
                                break;
                            }
                        }
                    }
                }
                
                
                System.out.println(msgFrmClnt);
//                outToClient.println(msgFrmClnt);
            } catch (Exception e) {
                if (GameServer.pairs.containsKey(this)) {
                        GameServer.pairs.get(this).outToClient.println(msgFrmClnt);
                        GameServer.pairs.remove(this);
                    } else if (GameServer.pairs.containsValue(this)) {
                        for (HashMap.Entry<WorkerThread, WorkerThread> entry : GameServer.pairs.entrySet()) {
                            if (this.equals(entry.getValue())) {
                                entry.getKey().outToClient.println(msgFrmClnt);
                                GameServer.pairs.remove(entry.getKey());
                                break;
                            }
                        }
                    }
                System.out.println("cannot connect");
                break;
            }
            try {
                output.close();
            } catch (IOException ex) {
            }
        }		
    }
}