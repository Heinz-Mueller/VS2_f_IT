package client;

import VS2.ConnectInformationData;
import VS2.LoginInformation;
import VS2.MessageData;
import VS2.UserData;
import View_GUI.Login;
import View_GUI.Tafel;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by heinz on 26.03.17.
 */
public class cDialog
{
    private static StartClient testClient;
    String benutzterName = null;
    String passWord = null;
    String dbIP = null;
    int dbPORT = 0;

    String ip = null;
    int port = 0;

    boolean serverConnect = false;
    boolean shutdown = false;

    LoginInformation logininfo = null;
    UserData userDATA = null;
    RegisterLogin dbLogin;
    StartClient clientTest;
    //StartClient testClient = new StartClient(args[0],Integer.parseInt(args[1]));

    Scanner scan;

    private boolean connDatenB() {
        // Login Daten eigben
        System.out.println("Datenb Daten eigben \n" + "IP: ");
        scan = new Scanner(System.in);
        dbIP = scan.nextLine();

        System.out.println("Port: ");
        scan = new Scanner(System.in);
        dbPORT = scan.nextInt();

        // Login klasse starten
        dbLogin = new RegisterLogin(dbPORT, dbIP);
        // login Datenbank verbinden
        if (!dbLogin.connectLoginServer()) {
            serverConnect = false;
            return false;

        }
        serverConnect = true;
        return true;

    }

    private boolean loginDB() {
        System.out.println("Bitte Login Daten eingben:\n" + "Benutzer: ");
        scan = new Scanner(System.in);
        benutzterName = scan.nextLine();

        System.out.println("Password: ");
        scan = new Scanner(System.in);
        passWord = scan.nextLine();
        userDATA = new UserData();
        userDATA.userID = 0;
        userDATA.userName = benutzterName;
        userDATA.password = passWord;
        this.logininfo = dbLogin.login(this.userDATA);

        if (!benutzterName.isEmpty() && !passWord.isEmpty()) {

            if (logininfo != null && this.serverConnect) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    private boolean registerDB() {
        System.out.println("Bitte Login Daten eingben:\n" + "Benutzer: ");
        scan = new Scanner(System.in);
        benutzterName = scan.nextLine();

        System.out.println("Password: ");
        scan = new Scanner(System.in);
        passWord = scan.nextLine();
        userDATA = new UserData();
        userDATA.userID = 0;
        userDATA.userName = benutzterName;
        userDATA.password = passWord;
        // was macht regData
        String regData = "TEST";
        if (!benutzterName.isEmpty() && !passWord.isEmpty()) {
            if (dbLogin.register(this.userDATA, regData) && this.serverConnect) {
                this.logininfo = dbLogin.login(this.userDATA);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public ArrayList<MessageData> ausgabeNachrichten()
    {
        ArrayList<MessageData> messageListtest = new ArrayList<MessageData>();
        messageListtest = testClient.getMessage();
        return messageListtest;
    }


    public void nachrichtSchreiben(String nachricht)
    {
        testClient.schreibeMessage(nachricht);
    }


    public StartClient infos()
    {
        StartClient infos = testClient;
        return infos;
    }

    public boolean delete(String messageID)
    {
        testClient.deleteMessage(messageID);
        return true;
    }



   public static void main(String args[]) {
        ArrayList<MessageData> messageListtest = new ArrayList<MessageData>();

       //Daten fuer Loginserver zu ersetzten
       ArrayList<UserData> userArray=new ArrayList<UserData>();

       UserData user1=new UserData(1001,"Salva","pw",true);
       UserData user2=new UserData(1002,"Patzek","pw",false);
       UserData user3=new UserData(1005,"Marco","pw",true);
       UserData user4=new UserData(1002,"Sadri","pw",false);
       UserData user5=new UserData(1007,"Eugen","pw",true);

       userArray.add(user1);
       userArray.add(user2);
       userArray.add(user3);
       userArray.add(user4);
       userArray.add(user5);

       //server IP und Port
       ConnectInformationData serDaten1=new ConnectInformationData("192.168.178.52",6000);
       ConnectInformationData serDaten2=new ConnectInformationData("192.168.0.105",6000);
       //ConnectInformationData serDaten2=new ConnectInformationData("127.0.0.1",6666);

       int userPos = 0; //User Nr
       int auswahl=0;

       LoginInformation loginInfo=null;

       loginInfo = new LoginInformation (userArray.get(userPos).isAdmin,serDaten2);

        //testClient = new StartClient(args[0],Integer.parseInt(args[1]));
       testClient = new StartClient(loginInfo,userArray.get(userPos));

        Login loginFenster = new Login();
        loginFenster.sichtbar(true);

        //Tafel a = new Tafel();
        //a.sichtbar(true);



        String messagea;
        String messageID;
        String bName;
        MessageData msgData;
        int loeschuid;

        cDialog dialog = new cDialog();

        Scanner scanMain = new Scanner(System.in);


        //dialog.clientTest=new StartClient(args[0],Integer.parseInt(args[1]));
        //dialog.clientTest=new StartClient("192.168.178.52",6000);
       dialog.clientTest=new StartClient(loginInfo,userArray.get(userPos));

        int i;
        boolean beenden = false;

       while (!beenden) {
           System.out.println("\n Was moechten Sie machen?" + "\nBeenden: 0 " + "\nNachricht schreiben: 1"
                   + "\nNachricht loeschen: 2" + "\nNachricht ersetzen: 3" + "\nNachricht Ausgabe: 4"
                   + "\nServer wechseln: 5 (automatisch): " + "\nGebe Vater und kinder Infos: 6" +
                   "\nNachricht wird zum Vater gesendet: 7"+" \nNachricht an Kinder senden: 8 "+"\nGeänderte Nachricht an alle Childs weiter senden 9  -----> ");
           scanMain = new Scanner(System.in);
           i = scanMain.nextInt();

           switch (i) {
               case 1:

                   System.out.println("Gib deine Nachricht ein:");
                   scanMain = new Scanner(System.in);
                   messagea = scanMain.nextLine();

                   System.out.println(dialog.clientTest.schreibeMessage(messagea));

                   break;

               case 2:

                   System.out.println("Loeschen einer Nachricht:");
                   System.out.println("ID:");
                   scanMain = new Scanner(System.in);
                   messageID = scanMain.nextLine();

                   if (!dialog.clientTest.deleteMessage( messageID)) {
                       System.out.println("ID: " + messageID + " sind falsch ");
                   }

                   break;

               case 3:

                   System.out.println("Ersetzen : \n ");
                   System.out.println("neue Nachricht eingeben : ");

                   Scanner scan11 = new Scanner(System.in);
                   messagea = scan11.nextLine();

                   System.out.println("MessageID : \n ");
                   scanMain = new Scanner(System.in);
                   String mId = scanMain.nextLine();

                   if (!dialog.clientTest.setMessage(messagea, mId)) {
                       System.out.println("UID: " + mId + " und/oder " + "Message: " + messagea + " Bname: bName"
                               + " ist falsch ");
                   }
                   break;
               case 4:

                   System.out.println("\n Ausgabe von alle Message auf server:\n");
                   messageListtest = dialog.clientTest.getMessage();
                   for (int y = 0; y < messageListtest.size(); y++) {
                       System.out.println("Nachricht: " + messageListtest.get(y).text + " -BNAME:" + messageListtest.get(y).uName
                               + " -UID:" + messageListtest.get(y).uid + " -ID:" + messageListtest.get(y).id);
                   }
                   break;
               case 5:
                   System.out.println("Server wird gewechselt");

                   do{

                       System.out.println("Mit welchen Server moechten sie verbinden\n");
                       System.out.println("IP "+serDaten1.ip+"  Port"+serDaten1.port+" - 0\n");
                       System.out.println("IP "+serDaten2.ip+"  Port"+serDaten2.port+" - 1 "
                               + "\nAbbrechen 2 ->");

                       scanMain = new Scanner(System.in);
                       auswahl = scanMain.nextInt();
                       System.out.println(auswahl);

                   }while(auswahl<0||auswahl>2);


                   if(auswahl==0){
                       loginInfo=new LoginInformation (userArray.get(userPos).isAdmin,serDaten1);
                   }else if(auswahl ==1){
                       loginInfo=new LoginInformation (userArray.get(userPos).isAdmin,serDaten2);
                   }else{
                       System.out.println("Abbrechen");
                       break;
                   }

                   dialog.clientTest.disconnectToServer();
                   dialog.clientTest = new StartClient(loginInfo,userArray.get(userPos));

                   break;
               case 6:
                   for(int r=0;r<dialog.clientTest.getChildNames().size();r++){
                       String childname = dialog.clientTest.getChildNames().get(r);
                       System.out.println("Kindname: " + childname + " IP: " + dialog.clientTest.getChildIP(childname)
                               + " Port" + dialog.clientTest.getChildPort(childname));
                   }


                   System.out.println(dialog.clientTest.getFatherName());
                   System.out.println(dialog.clientTest.getFatherIP());
                   System.out.println(dialog.clientTest.getFatherPort());
                   break;
               case 7:

                   System.out.println(dialog.clientTest.getFatherName());
                   System.out.println(dialog.clientTest.getFatherIP());
                   System.out.println(dialog.clientTest.getFatherPort());

                   messageListtest = dialog.clientTest.getMessage();
                   System.out.println(messageListtest.get(messageListtest.size()-1).text+" wird zu vater geschickt");
                   dialog.clientTest.publishOnFather(messageListtest.get(messageListtest.size()-1));
                   break;
               case 8:

                   messageListtest = dialog.clientTest.getMessage();
                   System.out.println(messageListtest.get(0).text+" wird zu kinder geschickt");

                   dialog.clientTest.publishOnChilds(messageListtest.get(messageListtest.size()-1));
                   break;
               case 9:
                   System.out.println("Erstezen an alles CHILDS: entwerder selbst ändern und an alle childs senden oder das geänderte von andere Child an alle Senden  ");
                   messageListtest = dialog.clientTest.getMessage();
                   for(int y =0; y<messageListtest.size();++y){
                       System.out.println("NR "+y+ " Nachricht "+messageListtest.get(y).text);

                   }
                   scanMain = new Scanner(System.in);
                   auswahl = scanMain.nextInt();
                   System.out.println(auswahl);
                   // wenn selbst text geändert dann bitte messageListtest.get(auswahl) und text ändern und fkt aufrufen

                   //messageListtest.get(auswahl).text="test";


                   if (!dialog.clientTest.setMessageChild(messageListtest.get(auswahl))) {
                       System.out.println("UID: " + messageListtest.get(auswahl).uid + " und/oder " + "Message: " + messageListtest.get(auswahl).text );
                   }
                   //	System.out.println(messageListtest.get(0).text+" wird zu kinder geschickt");

                   break;
               case 0:
                   System.out.println("Beendet");
                   beenden = true;
                   break;
               default:
                   System.out.println("bitte gib eine richtige Eingabe!\n");
                   break;

           }
       }
    }


/*    public void gui(String ip, int port)
    {
        ArrayList<MessageData> messageListtest = new ArrayList<MessageData>();
        System.out.println("bitte gib eine richtige Eingabe!\n");

        //testClient = new StartClient(ip, port);

        //Login loginFenster = new Login();
        //loginFenster.sichtbar(true);

        //Tafel a = new Tafel();
        //a.sichtbar(true);

        String messagea;
        String messageID;
        String bName;
        MessageData msgData;
        int loeschuid;

        cDialog dialog = new cDialog();

        Scanner scanMain = new Scanner(System.in);

        System.out.println("Bitte Ip und port eingeben ./StartClient.sh IP POrt");
        dialog.clientTest=new StartClient(ip, port);


    }*/

}
