package fr.alefebvre.school.footsma.controleur;

import fr.alefebvre.school.footsma.modele.*;
import fr.alefebvre.school.footsma.vue.SimulationWindow;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Simulation extends Canvas implements Runnable {

    public static final long serialVersionUID = 1L;

    private boolean running = false;
    private Thread thread;
    private AgentHandler agentHandler;

    public Simulation(Runtime rt, Profile profile) throws StaleProxyException {
        new SimulationWindow(Constants.WIDTH, Constants.HEIGHT, "Football simulation", this);
        agentHandler = new AgentHandler();
        initialize(rt, profile);
    }

    public static void main(String[] args) {
        // Get a hold on JADE runtime
        jade.core.Runtime rt = Runtime.instance();
        /*
            http://jade.tilab.com/pipermail/jade-develop/2008q3/012874.html
        */
        rt.setCloseVM(true);
        System.out.print("runtime created\n");
        // Create a default profile
        Profile profile = new ProfileImpl();
        System.out.print("profile created\n");

        System.out.println("Launching a whole in-process platform..." + profile);
        jade.wrapper.AgentContainer mainContainer = rt.createMainContainer(profile);

        // now set the default Profile to start a container
        ProfileImpl pContainer = new ProfileImpl(null, 1200, null);
        System.out.println("Launching the agent container ..." + pContainer);

        jade.wrapper.AgentContainer cont = rt.createAgentContainer(pContainer);
        System.out.println("Launching the agent container after ..." + pContainer);

        System.out.println("containers created");
        System.out.println("Launching the rma agent on the main container ...");
        AgentController rma;
        try {
            rma = mainContainer.createNewAgent("rma",
                    "jade.tools.rma.rma", new Object[0]);
            rma.start();
            new Simulation(rt, profile);
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    public void initialize(Runtime rt, Profile profile) throws StaleProxyException {
        // Create a new non-main container, connecting to the default
        // main container (i.e. on this host, port 1099)
        ContainerController cc = rt.createAgentContainer(profile);

        agentHandler.getObjects().clear();
        Object[] argsT = new Object[1];
        argsT[0] = agentHandler;
        AgentController terrain = cc.createNewAgent("terrain",
                AgentTerrain.class.getName(), argsT);

        // Gardiens
        // Creation d'un joueur
        Object[] argsJoueur1 = getArgsJoueur(terrain, agentHandler, true, 1, 1);
        Object[] argsJoueur2 = getArgsJoueur(terrain, agentHandler, false, 1, 9);
        Object[] argsJoueur3 = getArgsJoueur(terrain, agentHandler, true, 2, 1);
        Object[] argsJoueur4 = getArgsJoueur(terrain, agentHandler, false, 2, 10);

        AgentController joueur1 = cc.createNewAgent("joueur1", AgentJoueur.class.getName(),
                argsJoueur1);
        AgentController joueur2 = cc.createNewAgent("joueur2",
                AgentJoueur.class.getName(), argsJoueur2);
        AgentController joueur3 = cc.createNewAgent("joueur3", AgentJoueur.class.getName(),
                argsJoueur3);
        AgentController joueur4 = cc.createNewAgent("joueur4",
                AgentJoueur.class.getName(), argsJoueur4);

        terrain.start();
        joueur1.start();
        joueur2.start();
        joueur3.start();
        joueur4.start();

        Object[] argsArbitre = new Object[2];
        argsArbitre[0] = 0;
        argsArbitre[1] = agentHandler;
        AgentController arbitre = cc.createNewAgent("Arbitre", AgentArbitre.class.getName(), argsArbitre);
        arbitre.start();

        //agentHandler.addMap(new TilesMap(0, 0, Constants.TEST_MAP_PATH));
        // agentHandler.getObjects().add(new Player(0, 0, 0, 0, agentHandler));
    }

    public static Object[] getArgsJoueur(AgentController terrain, AgentHandler agentHandler, boolean estGardien, int numEquipe, int numJoueur) {
        Object[] argsJoueur = new Object[6];
        argsJoueur[0] = agentHandler;
        // couleur
        if(estGardien)
            argsJoueur[1] = Color.YELLOW;
        else if (numEquipe ==1 )
            argsJoueur[1] = Color.RED;
        else
            argsJoueur[1] = Color.BLUE;
        if(estGardien){
            if(numEquipe == 1)
                argsJoueur[2] = new Position(ReglesDuJeu.getPosButEquipe1());
            else
                argsJoueur[2] = new Position(ReglesDuJeu.getPosButEquipe2());
        }
        else
        {
            if(numEquipe == 1)
                argsJoueur[2] = new Position(ReglesDuJeu.getPosMillieuTerrain());
            else
                argsJoueur[2] = new Position(ReglesDuJeu.getPosMillieuTerrain());
        }

        argsJoueur[3] = numJoueur;
        argsJoueur[4] = estGardien;
        argsJoueur[5] = numEquipe;
        //argsJoueur[6] = terrain;
        return argsJoueur;
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();

    }

    synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void run() {
        this.requestFocus();
        //long lastTime = System.nanoTime();
       // final double amountOfTicks = 60.0;
       // double ns = 1000000000 / amountOfTicks;
        //double delta = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            //long now = System.nanoTime();
            //delta += (now - lastTime) / ns;
            //lastTime = now;
            /*if (delta >= 1) {
                tick();
                delta--;
            }*/
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS " + frames);
                frames = 0;
            }
        }
        stop();
    }

/*    private void tick() {
        this.agentHandler.tick();
    }*/

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        this.agentHandler.render(g);
        g.dispose();
        bs.show();
    }

    public void startMatch() {
        if(agentHandler!=null){
            agentHandler.startMatch();
        }
    }
}
