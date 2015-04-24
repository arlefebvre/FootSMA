package fr.alefebvre.school.footsma.vue;

import fr.alefebvre.school.footsma.controleur.Simulation;

import javax.swing.*;
import java.awt.*;

public class SimulationWindow extends Canvas {
    public SimulationWindow(int width, int height, String title, Simulation simulation) {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(simulation, BorderLayout.CENTER);
        JButton runButton = new JButton("Coup d'envoi");
        runButton.addActionListener(e -> simulation.start());
        JButton quitButton = new JButton("Quitter");
        quitButton.addActionListener(e -> System.exit(0));
        JPanel controlPanel;
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(5, 5));
        controlPanel.add(runButton);
        controlPanel.add(quitButton);
        frame.add(controlPanel, BorderLayout.LINE_END);
        frame.setVisible(true);
        simulation.start();
    }
}
