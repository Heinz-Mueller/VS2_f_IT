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

    boolean serverConnect = false;
    boolean shutdown = false;

    LoginInformation logininfo = null;
    UserData userDATA = null;
    RegisterLogin dbLogin;
    StartClient clientTest;

    Scanner scan;

    //Array-Liste für die User
    public static ArrayList<UserData> userArray=new ArrayList<UserData>();

    //Server und Port fest eingetragen
    public static ConnectInformationData serDaten_localhost = new ConnectInformationData("127.0.0.1",6000);
    public static ConnectInformationData serDaten1 = new ConnectInformationData("192.168.178.52",6000);
    public static ConnectInformationData serDaten2 = new ConnectInformationData("192.168.0.105",6000);


    static int userNr = 0;

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
        if ( testClient.deleteMessage(messageID) )
        {
            return true;
        }else
            return false;

    }

    public boolean nachrichtAendern(String nachricht, String nachrichtID)
    {
        if ( testClient.setMessage(nachricht, nachrichtID) )
        {
            return true;
        }else
            return false;
    }

    public boolean userCheck(String user, String pw)
    {
        for(int j = 0; j< userArray.size(); ++j)
        {
            if((userArray.get(j).userName.equals(user))&&(userArray.get(j).password.equals(pw)))
            {
                userNr = j;
                return true;
            }
        }
        return false;
    }


    public void startClient(String user, int serverNr)
    {
        LoginInformation loginInfos = null;

        if (serverNr == 0){
            loginInfos = new LoginInformation (userArray.get(userNr).isAdmin, serDaten_localhost);
        }else if(serverNr == 1){
            loginInfos = new LoginInformation (userArray.get(userNr).isAdmin, serDaten1);
        }else if(serverNr == 2){
            loginInfos = new LoginInformation (userArray.get(userNr).isAdmin, serDaten2);
        }

        testClient = new StartClient(loginInfos, userArray.get(userNr));
    }


   public static void main(String args[])
   {
        ArrayList<MessageData> messageListtest = new ArrayList<MessageData>();

       //Daten fuer Loginserver zu ersetzten
        //ArrayList<UserData> userArray=new ArrayList<UserData>();

       //User anlegen und in Array Liste packen
       UserData user1 = new UserData(1001,"Salva","pw",true);
       UserData user2 = new UserData(1002,"Patzek","pw",false);
       UserData user3 = new UserData(1005,"Marco","pw",true);
       UserData user4 = new UserData(1002,"Sadri","pw",false);
       UserData user5 = new UserData(1007,"Eugen","pw",true);

       userArray.add(user1);
       userArray.add(user2);
       userArray.add(user3);
       userArray.add(user4);
       userArray.add(user5);

       //Login-Fenster anlegen
       Login loginFenster = new Login();
       loginFenster.sichtbar(true);


       cDialog dialog = new cDialog();

    }


}
