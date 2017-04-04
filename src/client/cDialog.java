package client;

import VS2.ConnectInformationData;
import VS2.LoginInformation;
import VS2.MessageData;
import VS2.UserData;
import View_GUI.Login;


import java.util.ArrayList;


/**
 * Created by heinz on 26.03.17.
 */
public class cDialog
{
    private static StartClient testClient;


    //Array-Liste für die User
    public static ArrayList<UserData> userArray=new ArrayList<UserData>();

    ///////////Hier Server und Port fest eingetragen ////////////////////////
    public static ConnectInformationData serDaten_localhost = new ConnectInformationData("127.0.0.1",6000);
    public static ConnectInformationData serDaten1 = new ConnectInformationData("192.168.178.52",6000);
    public static ConnectInformationData serDaten2 = new ConnectInformationData("192.168.0.105",6000);


    static int userNr = 0;

    /*Zieht und schreibt alle Nachrichten in Liste*/
    public ArrayList<MessageData> ausgabeNachrichten()
    {
        ArrayList<MessageData> messageListtest = new ArrayList<MessageData>();
        messageListtest = testClient.getMessage();
        return messageListtest;
    }

    /*Schreibt eine Nachricht*/
    public void nachrichtSchreiben(String nachricht)
    {
        testClient.schreibeMessage(nachricht);
    }

    /*Liefert Informationen*/
    public StartClient infos()
    {
        StartClient infos = testClient;
        return infos;
    }

    /*Löscht eine Nachricht*/
    public boolean delete(String messageID)
    {
        if ( testClient.deleteMessage(messageID) )
        {
            return true;
        }else
            return false;

    }

    /*Ändert eine Nachricht durch neue Nachricht*/
    public boolean nachrichtAendern(String nachricht, String nachrichtID)
    {
        if ( testClient.setMessage(nachricht, nachrichtID) )
        {
            return true;
        }else
            return false;
    }

    /*Prüft ob User mit diesem Password verhanden ist*/
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

    /*Startet Client nach Serverwahl*/
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
