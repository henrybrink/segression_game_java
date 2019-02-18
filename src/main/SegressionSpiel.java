package main;

import javax.swing.*;

/**
 * Diese Simulation wurde nach dem Vorbild der Simulation von Jörg Kantel programmiert.
 *
 * Quelle: http://py.kantel-chaos-team.de/segregation/
 */
public class SegressionSpiel {

    /**
     * constructor
     *
     * Diese Funktion wird automatisch durch Java gestartet und öffnet das Einstelungsfenster.
     */
    public static void main(String[] args) {
        JFrame frame = new MainScreen();

        frame.setTitle("Segressionsspiel");
        frame.setSize(400, 600);
        frame.setResizable(false);
        frame.setLocation(50, 50);
        frame.setVisible(true);
    }


}
