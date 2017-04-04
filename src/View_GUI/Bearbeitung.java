package View_GUI;


import VS2.MessageData;
import VS2.UserData;
import client.cDialog;

import javax.swing.*;
import java.awt.event.*;
import java.time.format.TextStyle;
import java.util.ArrayList;

/**
 * Created by Amel on 24.01.2017.
 * 
 */
public class Bearbeitung extends JDialog
{

    private JComboBox comboBox1;
    private JButton ändernButton;
    private JButton abbrechenButton;
    private JPanel bearbeitungPanel;
    private JTextArea nachrichtenArea;

    JFrame frame = new JFrame("Bearbeitung");


    cDialog test = new cDialog();

    //Lade Nachrichten in ArrayListe und dann in die comboBox
    ArrayList<MessageData> nachrichten = test.ausgabeNachrichten();

    public Bearbeitung()
    {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        /*Deaktiviere das Tafel-Fenster*/
        Tafel.frame.setEnabled(false);

        erstellen();


        abbrechenButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onAbbrechen();
            }
        });


        ändernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onAendern();
            }
        });

    }


    public void erstellen()
    {
        nachrichtenLaden();


        frame.setContentPane(bearbeitungPanel);
        frame.setLocation(800, 0);
        frame.pack();
        frame.setSize(700, 500);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Tafel.frame.setEnabled(true);
            }
            public void windowClosed(WindowEvent e) {
                Tafel.frame.setEnabled(true);
            }
        });
    }


    private void nachrichtenLaden()
    {
        comboBox1.removeAllItems();

        for (int i = 0; i < nachrichten.size(); i++)
        {
            comboBox1.addItem(" UserID: " + nachrichten.get(i).uid + "   NachrichtID" + nachrichten.get(i).id);
        }


        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                nachrichtenArea.setText(null);
                nachrichtenArea.append(nachrichten.get(comboBox1.getSelectedIndex()).text );
            }
        };
        comboBox1.addActionListener(actionListener);

    }

    private void onAbbrechen()
    {
        frame.dispose();
        Tafel.frame.setEnabled(true);
    }

    private void onAendern()
    {
        String nachricht = nachrichtenArea.getText();
        String nachrichtID = nachrichten.get( comboBox1.getSelectedIndex() ).id;

        if ( !test.nachrichtAendern(nachricht, nachrichtID) )
        {
            JOptionPane.showMessageDialog(null, "Nachricht konnte nicht geändert werden!", "Änderung", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Nachricht wurde geändert!", "Änderung", JOptionPane.INFORMATION_MESSAGE);

        //Nachrichten neu in Liste laden
        nachrichten = test.ausgabeNachrichten();
    }


    private void refreshComboBox() //klappt nicht wie geplant
    {
        nachrichten = test.ausgabeNachrichten();

        comboBox1.removeAllItems();

        for (int i = 0; i < nachrichten.size(); i++)
        {
            comboBox1.addItem(" UserID: " + nachrichten.get(i).uid + "   NachrichtID" + nachrichten.get(i).id);
        }
    }

}