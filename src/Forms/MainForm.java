package Forms;

import Lan.SocketServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
public class MainForm extends JFrame{
    public JButton button1;
    private JPanel rootPanel;
    private JTextField message;
    public MainForm(final SocketServer sock){
        super("Server");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            }
        });
    }
}
