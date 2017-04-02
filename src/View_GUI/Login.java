package View_GUI;

import VS2.UserData;
import client.cDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Fuse on 25.01.2017.
 */
public class Login extends javax.swing.JFrame
{
    JFrame m_viewFrame = new JFrame("Bitte Daten eingeben");
    JMenuBar m_menuBar;
    JTextField userTextFeld;
    //JPasswordField pwTextFeld;
    JTextField pwTextFeld;
    JButton m_setButton;
    JLabel m_errorLabel;

    public String user;
    public String pw = null;


    public int port = 0;

    public cDialog a;


    // Create the class for the Controller
    //private LoginController controller = new LoginController();

    public Login ()
    {
        erstellen();
    }


    public void erstellen()
    {
        //Daten um Loginserver zu ersetzten
        ArrayList<UserData> userArray=new ArrayList<UserData>();

        UserData user1=new UserData(1001,"Salva","pw",true);
        UserData user2=new UserData(1005,"Marco","pw",true);
        UserData user3=new UserData(1002,"Sadri","pw",false);

        userArray.add(user1);
        userArray.add(user2);
        userArray.add(user3);


        //m_viewFrame = new JFrame("Bitte Daten eingeben");
        m_viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        m_menuBar = new JMenuBar();
        m_viewFrame.setJMenuBar(m_menuBar);

        JPanel northPanel = new JPanel(new BorderLayout());
        m_viewFrame.getContentPane().add(northPanel,BorderLayout.NORTH);

        Box b1 = Box.createVerticalBox();
        Box b2 = Box.createVerticalBox();

        b1.add(new Label("htw", Label.CENTER));
        b2.add(new Label("Anzeige-Tafel",Label.CENTER));

        b1.add(new Label("Benutzer: ", Label.RIGHT));
        userTextFeld = new JTextField();
        userTextFeld.setHorizontalAlignment(JTextField.CENTER);
        b2.add(userTextFeld);

        b1.add(new Label("Password: ", Label.RIGHT));
        //pwTextFeld = new JPasswordField();
        pwTextFeld = new JTextField();
        pwTextFeld.setHorizontalAlignment(JTextField.CENTER);
        b2.add(pwTextFeld);

        northPanel.add(b1,BorderLayout.WEST);
        northPanel.add(b2,BorderLayout.CENTER);
        m_errorLabel = new JLabel("",JLabel.CENTER);
        northPanel.add(m_errorLabel, BorderLayout.SOUTH);


        m_setButton = new JButton ("Login");
        m_setButton.addActionListener (new ActionListener(){
            public void actionPerformed (ActionEvent e)
            {
                user = userTextFeld.getText();
                pw = pwTextFeld.getText().trim();

                System.out.println(user);
                System.out.println(pw);

                for(int j =0; j< userArray.size(); ++j)
                {
                    if((userArray.get(j).userName.equals(user))&&(userArray.get(j).password.equals(pw)))
                    {
                        Tafel a = new Tafel(user);
                        a.sichtbar(true);
                    }
                }


                //Tafel a = new Tafel(user);
                //a.sichtbar(true);


                //a.gui(ip, port);
                //Sobald eingeloggt mache das login fenster unsichtbar
                //m_viewFrame.setVisible(false);

                //Tafel b = new Tafel(ip);
                //b.sichtbar(true);


            }
        });

        JPanel buttonPanel = new JPanel();
        m_viewFrame.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        buttonPanel.add(m_setButton);

        // Position setzen
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        m_viewFrame.setLocation((d.width - m_viewFrame.getSize().width) / 2,
                (d.height - m_viewFrame.getSize().height) / 2);


        m_viewFrame.setSize(400, 150);

        // JFrame anzeigen
        //m_viewFrame.setVisible(true);
    }

    public void sichtbar(boolean a)
    {
        if (a = true)
        {
            m_viewFrame.setVisible(true);
        }
    }


/*    public static void main(String args[])
    {
        Login start = new Login();
        start.sichtbar(true);
    }*/
}