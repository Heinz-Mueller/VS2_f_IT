package View_GUI;

import VS2.MessageData;
import client.*;

import client.StartClient;

import javax.swing.*;
import java.awt.event.*;

import java.util.ArrayList;

/**
 * Created by heinz on 10.01.2017.
 */
public class Tafel extends JDialog
{
    static JFrame frame = new JFrame("Tafel");
    private JTextArea nachrichtenArea;
    private JPanel tafelPanel;
    private JButton sendenButton;
    private JTextField nachrichtTexteingabe;
    private JLabel User;
    private JLabel aktuellerUser;
    private JButton messageBearbeitenButton;
    private JButton refreshButton;
    private JTextArea childrenTextArea;
    private JLabel Nachrichten;
    private JLabel Children;
    private JButton deleteButton;
    private JLabel info;


    cDialog test = new cDialog();

    public Tafel()
    {
       erstellen();

    }

    public Tafel(String user)
    {
        erstellen();
        aktuellerUser.setText(user);

        messageBearbeitenButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onBearbeiten();
            }
        });

        refreshButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onRefresh();
            }
        });

        sendenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onSender();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onDelete();
            }
        });
    }

    /*Eingabe für message-ID und dann löschen der Nachricht*/
    private void onDelete()
    {
        info.setText("");
        JFrame eingabe = new JFrame();
        String messageID = JOptionPane.showInputDialog(eingabe, "Bitte Nachricht-ID eingeben.", "Delete", JOptionPane.PLAIN_MESSAGE);

        if (messageID == null){
            eingabe.dispose();
        }else{
            if ( !test.delete( messageID) )
            {
                JOptionPane.showMessageDialog(null, "Nachricht konnte nicht geloescht werden!", "Delete", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Nachricht geloescht!", "Delete", JOptionPane.INFORMATION_MESSAGE);
                onRefresh();
            }
        }

    }

    public void onRefresh()
    {
        nachrichtenArea.setText("");
        ArrayList<MessageData> nachrichten = test.ausgabeNachrichten();
        for (int y = 0; y < nachrichten.size(); y++) {
            nachrichtenArea.append("TEXT: " + nachrichten.get(y).text
                    + "   UID: " + nachrichten.get(y).uid + "   ID: " + nachrichten.get(y).id);
            nachrichtenArea.append("\n");
        }
        info.setText("");
    }

    public void onSender()
    {
        String nachricht = nachrichtTexteingabe.getText();
        nachricht.trim();
        if (nachricht.equals(""))
        {
            info.setText("Bitte eine Nachricht eingeben!");
        }else{
            test.nachrichtSchreiben(nachricht);
            nachrichtTexteingabe.setText("");
            onRefresh();
            info.setText("");
        }
    }


    private void onBearbeiten()
    {
        Bearbeitung bearbeitung = new Bearbeitung();
        info.setText("");
    }

    public void knotenInfos()
    {
        StartClient info = test.infos();

        for(int r=0; r<info.getChildNames().size(); r++)
        {
            String childname = info.getChildNames().get(r);
            childrenTextArea.append("Kindname: " + childname + " IP: " + info.getChildIP(childname)
                    + " Port" + info.getChildPort(childname) + "\n\n");
        }


        childrenTextArea.append(info.getFatherName() + "\n");
        childrenTextArea.append(info.getFatherIP() + "\n");
        childrenTextArea.append(String.valueOf(info.getFatherPort()) + "\n\n");

    }


    public void erstellen()
    {
        frame.setContentPane(tafelPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 600);
        knotenInfos();
        onRefresh();
    }



    public void sichtbar(boolean a)
    {
        if (a = true)
        {
            frame.setVisible(true);
        }
    }


    // Text in Info-Textfeld packen
    void anhaengenAnInfo(String str)
    {
        childrenTextArea.append(str);
        childrenTextArea.setCaretPosition(childrenTextArea.getText().length() - 1);
    }
}
