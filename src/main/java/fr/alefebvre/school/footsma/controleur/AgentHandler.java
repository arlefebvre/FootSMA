package fr.alefebvre.school.footsma.controleur;

import fr.alefebvre.school.footsma.modele.AgentArbitre;
import fr.alefebvre.school.footsma.modele.AgentJoueur;
import fr.alefebvre.school.footsma.modele.AgentTerrain;
import jade.core.AID;

import java.awt.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class AgentHandler {

    protected CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<>();

    private Set<AID> equipe1 = new HashSet<AID>();

    private Set<AID> equipe2 = new HashSet<AID>();

    private AID arbitreId;

    private AID terrainId;
    private AgentTerrain terrain;

    public CopyOnWriteArrayList<GameObject> getObjects() {
        return objects;
    }

    /**
     * Rendu de tous les agents
     *
     * @param g
     */
    public void render(Graphics g) {
        objects.forEach(agent -> agent.render(g));
    }

    /**
     * Lancement du match
     */
    public void startMatch() {
        if (arbitreId != null) {
            for (GameObject go : objects) {
                if (go.getAID() == arbitreId) {
                    AgentArbitre arbitre = (AgentArbitre) go;
                    arbitre.sifflerCoupDEnvoi();
                }
            }
        }
    }

    public AID getArbitreId() {
        return arbitreId;
    }

    public void setArbitreId(AID arbitreId) {
        this.arbitreId = arbitreId;
    }

    public AID getTerrainId() {
        return terrainId;
    }

    public void setTerrainId(AID terrainId) {
        this.terrainId = terrainId;
    }

    @Deprecated
    public AgentTerrain getTerrain() {
        return terrain;
    }

    @Deprecated
    public void setTerrain(AgentTerrain at) {
        terrain = at;
    }

    public Stream<AID> getJoueursIds() {
        return Stream.concat(equipe1.stream(), equipe2.stream());
    }

    public void ajouteJoueur(AID joueurAID, int numeroEquipe) {
        if (numeroEquipe == 1)
            equipe1.add(joueurAID);
        else
            equipe2.add(joueurAID);
    }

    public AgentJoueur getJoueur(AID id) {
        Optional<GameObject> gameObject = objects.stream().filter(j -> j.getAID() == id).findFirst();
        return (AgentJoueur) gameObject.orElse(null);
    }
}
