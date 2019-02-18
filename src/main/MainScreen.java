package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {

    private JLabel program_title;
    private JPanel main_screen;
    private JTextPane program_info;
    private JSpinner board_size;
    private JSpinner max_distance;
    private JButton start_button;
    private JSpinner versuche;
    private JLabel Geschwindigkeit;
    private JSpinner speed;

    public MainScreen() {
        this.setContentPane(main_screen);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        board_size.setValue(20);
        max_distance.setValue(2);
        versuche.setValue(1500);
        speed.setValue(1000);

        start_button.addActionListener(e -> {
            JFrame gameWindow = new GameFrame((int) board_size.getValue(), (int) max_distance.getValue(), (int) versuche.getValue(), (int) speed.getValue());
        });
    }

}
