package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class GameFrame extends JFrame {
    private JPanel screen;

    private int[][] field;

    public GameFrame(int size, int stepSize, int trys, int speed) {
        this.setContentPane(screen);
        this.setTitle("Spielfenster");
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        int windowSize = size * 20;
        this.setSize(windowSize, windowSize);

        field = new int[size][size];
        generateMap(size);

        render(size);

        ActionListener listener = new ActionListener() {
            private int counter = trys;

            @Override
            public void actionPerformed(ActionEvent e) {
                checkHappyness(stepSize);
                render(size);

                if(counter < 1) {
                    ((Timer) e.getSource()).stop();
                } else {
                    counter--;
                }
            }
        };

        Timer timer = new Timer(speed, listener);
        timer.setRepeats(true);
        timer.start();

        System.out.println("\n\n\n" + Arrays.deepToString(field).replace("],", "]\n"));

        render(size);

    }

    /**
     * generateMap
     *
     * Diese Funktion generiert die Ausgangskarte und platziert auf ca. 60% der Karte Frösche und Schildkröten (gleichmäßige Karte)
     *
     * @param size
     */
    private void generateMap(int size) {
        int animalTotalSize = Math.round((size * size / 100 ) * 60);
        int animalSpeciesSize = Math.round(animalTotalSize / 2);

        int frogs = animalSpeciesSize;
        int turtles = animalSpeciesSize;

        System.out.println(frogs + ", " + turtles);

        for(int i = 0; i<field.length; i++) {
            for(int j = 0; j<field.length; j++) {
                int chooseType = ThreadLocalRandom.current().nextInt(0, 2 + 1);
                switch(chooseType) {
                    case 0:
                        field[i][j] = 0;
                        break;
                    case 1:
                        if(frogs > 0) {
                            frogs--;
                            field[i][j] = 1;
                        }
                        break;
                    case 2:
                        if(turtles > 0) {
                            turtles--;
                            field[i][j] = 2;
                        }
                        break;
                }
            }
        }

        System.out.println(Arrays.deepToString(field).replace("],", "]\n"));
    }

    private void checkHappyness(int steps) {
        int size = field.length;
        int x = ThreadLocalRandom.current().nextInt(0, size);
        int y = ThreadLocalRandom.current().nextInt(0, size);

        int inhabitant = field[x][y];

        if(inhabitant != 0) {

            int matching = 0;

            if(y+1 < field.length && field[x][y+1] == inhabitant) {
                matching++;
            }

            if(y-1 > 0 && field[x][y-1] == inhabitant) {
                matching++;
            }

            if(x+1 < field.length && field[x+1][y] == inhabitant) {
                matching++;
            }

            if(x-1 > 0 && field[x-1][y] == inhabitant) {
                matching++;
            }


            if(matching < 2) {
                int direction = ThreadLocalRandom.current().nextInt(0, 4);
                checkAndMove(x, y, direction, steps, inhabitant);
            }

        } else {
            checkHappyness(steps);
        }
    }

    private void checkAndMove(int x, int y, int direction, int steps, int type) {
        int newField = 0;

        int newX = ThreadLocalRandom.current().nextInt(0, field.length);
        int newY = ThreadLocalRandom.current().nextInt(0, field.length);

        if(field[newX][newY] == 0) {
            field[newX][newY] = type;
            field[x][y] = 0;
        } else {
            checkAndMove(x, y, direction, steps, type);
        }
    }

    private void render(int size) {
        screen.setLayout(new GridLayout(size, size));

        screen.removeAll();

        for(int x = 0; x<size; x++) {
            for(int y = 0; y<size; y++) {
                int currentField = field[x][y];
                JLabel label = new JLabel(currentField + "");
                if(currentField == 1) {
                    label.setBackground(Color.GREEN);
                } else if(currentField == 2) {
                    label.setBackground(Color.RED);
                }
                label.setOpaque(true);
                screen.add(label);
            }
        }

        screen.repaint();
        screen.updateUI();
    }


}
