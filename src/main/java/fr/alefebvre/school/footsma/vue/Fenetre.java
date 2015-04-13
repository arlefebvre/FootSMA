package fr.alefebvre.school.footsma.vue;

import fr.alefebvre.school.footsma.modele.AgentArbitre;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {
    private VueTerrain vueTerrain = null;
    private JLabel nomEquipe1, nomEquipe2, score;
    private JLabel temps = new JLabel("-- min");
    private JPanel scorePanel;
    private JPanel controlePanel;
    private JButton boutonDemarrer = new JButton("Coup d'envoi");
    private JButton boutonQuitter = new JButton("Quitter");
    private ContainerController cc;

    public Fenetre() {
        vueTerrain = new VueTerrain();
        initialiser();
    }

    public Fenetre(VueTerrain myVueTerrain, ContainerController mycc) {

        initialiser();
        vueTerrain = myVueTerrain;
        cc = mycc;
        mettreEnPage();
    }

    public void initialiser() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("FootSMA");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        nomEquipe1 = new JLabel("      Equipe 1");
        nomEquipe2 = new JLabel("Equipe 2");
        score = new JLabel("0       -       0");
        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(1, 5));
        scorePanel.add(new JLabel("Score"));
        scorePanel.add(nomEquipe1);
        scorePanel.add(score);
        scorePanel.add(nomEquipe2);
        scorePanel.add(temps);
        controlePanel = new JPanel();
        controlePanel.setLayout(new GridLayout(5, 5));
        boutonDemarrer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Object[] args = new Object[2];
                args[0] = temps;
                args[1] = vueTerrain;
                AgentController arbitre = null;
                try {
                    arbitre = cc.createNewAgent("Arbitre", AgentArbitre.class.getName(), args);
                } catch (StaleProxyException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    arbitre.start();
                } catch (StaleProxyException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                boutonDemarrer.setEnabled(false);
            }
        });
        controlePanel.add(boutonDemarrer);
        boutonQuitter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });
        controlePanel.add(boutonQuitter);
    }

    public void mettreEnPage() {
        setLayout(new BorderLayout());
        add(vueTerrain, BorderLayout.CENTER);
        /*Icon icon = new ImageIcon("images/terrain.jpg");
		add(new JLabel(icon),BorderLayout.SOUTH);*/
        add(scorePanel, BorderLayout.SOUTH);
        add(controlePanel, BorderLayout.EAST);
        pack();
    }

    public VueTerrain getVueTerrain() {
        return vueTerrain;
    }

    public void setVueTerrain(VueTerrain vueTerrain) {
        this.vueTerrain = vueTerrain;
    }

    public JLabel getNomEquipe1() {
        return nomEquipe1;
    }

    public void setNomEquipe1(JLabel nomEquipe1) {
        this.nomEquipe1 = nomEquipe1;
    }

    public JLabel getNomEquipe2() {
        return nomEquipe2;
    }

    public void setNomEquipe2(JLabel nomEquipe2) {
        this.nomEquipe2 = nomEquipe2;
    }

    public JLabel getScore() {
        return score;
    }

    public void setScore(JLabel score) {
        this.score = score;
    }

    public JLabel getTemps() {
        return temps;
    }

    public void setTemps(JLabel temps) {
        this.temps = temps;
        scorePanel.repaint();
    }


}
