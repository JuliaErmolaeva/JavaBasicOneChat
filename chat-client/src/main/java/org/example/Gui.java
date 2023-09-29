package org.example;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.IOException;

public class Gui extends JFrame implements Runnable {

    protected JTextArea outTextArea;
    protected JPanel southPanel;
    protected JTextField inTextField;
    protected JButton inTextSendButton;

    Network network;

    public Gui(String title, Network network) throws HeadlessException {
        super(title);
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1, 10, 10));
        southPanel.add(inTextField = new JTextField());
        inTextField.setEditable(true);
        southPanel.add(inTextSendButton = new JButton("Send message"));
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(BorderLayout.CENTER, outTextArea = new JTextArea());
        outTextArea.setEditable(false);
        cp.add(BorderLayout.SOUTH, southPanel);

        this.network = network;

        inTextSendButton.addActionListener(event ->
                {
                    String text = inTextField.getText();
                    try {
                        network.sendMessage(text);
                        inTextField.setText("");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setVisible(true);
        inTextField.requestFocus();
        (new Thread(this)).start();
        this.network.setCallback(new Callback() {
            @Override
            public void call(Object... args) {
                outTextArea.append((String) args[0]);
                outTextArea.append("\n");
            }
        });
    }


    @Override
    public void run() {

    }
}
