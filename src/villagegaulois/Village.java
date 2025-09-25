package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche {
		private Etal[] etals;
		private int nbEtalMarche = 0;
	
	
	private Marche(int nbEtalMax) {
		this.nbEtalMarche = nbEtalMax;
		etals = new Etal[nbEtalMax];
		
		for(int i = 0; i< nbEtalMax; i++) {
			etals[i] = new Etal();
		}
	}
	
	private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
		if (indiceEtal < 0 || indiceEtal >= etals.length) {
			System.out.println("Indice en dehors des limites du tableau ");
			return; 
		}
		if(etals[indiceEtal].isEtalOccupe()) {
			System.out.println("L'étal est déja occupé");
		}
		etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		
		System.out.println("Le Gaulois" + vendeur.getNom() + "s'est installé a l'étal" + indiceEtal + "et vend" + nbProduit + produit);
	}
	
	private int trouverEtalLibre() {
		for(int i = 0; i < etals.length; i++) {
			if(!(etals[i].isEtalOccupe())) {
				return i;
			}
		}
		return -1;
	}
	
	
	private Etal[] trouverEtals(String produit) {
		int compteur = 0;
		for(int i = 0; i<etals.length; i++) {
			if(etals[i] != null && etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
				compteur = compteur + 1;
			}
		}
		Etal[] etaloccupe = new Etal[compteur];
		
		if(compteur == 0) {
			return etaloccupe;
		}
		int compteur2 = 0;
		for(int j = 0; j<etals.length; j++) {
			if(etals[j] != null && etals[j].isEtalOccupe() && etals[j].contientProduit(produit)) {
				etaloccupe[compteur2] = etals[j];
				compteur = compteur + 1;
			}
		}
		return etaloccupe;
		
		
	}
	
	private Etal trouverVendeur(Gaulois gaulois) {
		for(int i = 0; i<etals.length; i++) {
			if(etals[i].getVendeur() == gaulois) {
				return etals[i];
			}
		}
		return null;
	}
	
	private String afficherMarcher() {
		StringBuilder chaine = new StringBuilder();
		int nbEtalsvide = 0;
		
		for(int i = 0; i < etals.length; i++) {
			if(etals[i].isEtalOccupe()) {
				chaine.append(etals[i].afficherEtal()).append("\n");
			}
			else {
				nbEtalsvide = nbEtalsvide + 1;
			}
		}
		if(trouverEtalLibre() != -1) {
			chaine.append("Il reste").append(nbEtalsvide).append("etals non utilise dans le marche. \n");
		}
		return chaine.toString();
	}
	
	public String afficherMarche() {
		return marche.afficherMarche();
	}
	
	}
	
}