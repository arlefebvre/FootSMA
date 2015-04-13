package fr.alefebvre.school.footsma.modele;

public class ReglesDuJeu {
		public static Position getPosMillieuTerrain() {
		return posMillieuTerrain;
	}
	public static void setPosMillieuTerrain(Position posMillieuTerrain) {
		ReglesDuJeu.posMillieuTerrain = posMillieuTerrain;
	}
		private static int largeurTerrain = 290;
		private static int longueurTerrain=440;
		private static Position posMillieuTerrain=new Position(225,145);
		private static Position posButEquipe1=new Position(10,posMillieuTerrain.getY());
		private static Position posButEquipe2=new Position(posMillieuTerrain.getX()*2-20,posMillieuTerrain.getY());
		private static double seuilDeProximite=10;
		
		public static int getLargeurTerrain() {
			return largeurTerrain;
		}
		public static int getLongueurTerrain() {
			return longueurTerrain;
		}
		public static double getSeuilDeProximite() {
			return seuilDeProximite;
		}
		public static void setSeuilDeProximite(double seuilDeProximite) {
			ReglesDuJeu.seuilDeProximite = seuilDeProximite;
		}
		public static Position getPosButEquipe1() {
			return posButEquipe1;
		}
		public static void setPosButEquipe1(Position posButEquipe1) {
			ReglesDuJeu.posButEquipe1 = posButEquipe1;
		}
		public static Position getPosButEquipe2() {
			return posButEquipe2;
		}
		public static void setPosButEquipe2(Position posButEquipe2) {
			ReglesDuJeu.posButEquipe2 = posButEquipe2;
		}

		
}
