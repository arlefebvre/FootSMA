/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Arthur Lefebvre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

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
        runButton.addActionListener(e -> simulation.startMatch());
        JButton quitButton = new JButton("Quitter");
        quitButton.addActionListener(e -> simulation.endMatch());
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
