package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtalMax) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtalMax);
	}
	
	private static class Marche {
		private Etal[] etals;
		private int nbEtalMarche=0;
		
		
		
	private Marche(int nbEtalMax) {
		this.nbEtalMarche = nbEtalMax;
		etals = new Etal[nbEtalMax];
		
		for (int i = 0; i < nbEtalMax; i++) {
	        etals[i] = new Etal();
	    }
		
	}
	private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
		if (indiceEtal < 0 || indiceEtal >= etals.length) {
			System.out.println("Indice en dehors du tableau.");
			return;
		}
		
		if (etals[indiceEtal].isEtalOccupe()) {
			System.out.println("L'étal est déjà occupé");
		}
		
		etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		
		
		System.out.println("Le gaulois " + vendeur.getNom() + " s'est installé à l'étal " + indiceEtal + " et vend " + nbProduit + produit);
		
	}
	
	private int trouverEtalLibre() {
		for(int i=0;i<etals.length;i++) {
			if (!(etals[i].isEtalOccupe())) {
				return i;
			}
			}
		return -1;
	}
	
	private Etal[] trouverEtals(String produit) {
		int cpt = 0;
		for(int i=0;i<etals.length;i++) {
			if (etals[i] != null && etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
				cpt++;
			}
			}
		Etal[] etaloccupe = new Etal[cpt];
		
		if(cpt==0) {
			return etaloccupe;
		}
		
		int cpt2=0;

		
		for(int j=0;j<etals.length;j++) {
			if(etals[j] != null && etals[j].isEtalOccupe() && etals[j].contientProduit(produit)) {
				etaloccupe[cpt2]=etals[j];
				cpt2++;
				
			}
		}
		
		return etaloccupe;
		
	}
	
	private Etal trouverVendeur(Gaulois gaulois) {
		for(int i=0;i<etals.length;i++) {
			if(etals[i].getVendeur() == gaulois) {
				return etals[i];
			}
		}
		return null;
	}
	
	
	private String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		int nbEtalsvide = 0;
		
		for(int i=0;i<etals.length;i++) {
			if (etals[i].isEtalOccupe()) {
				chaine.append(etals[i].afficherEtal()).append("\n");
				}
			else {
				nbEtalsvide++;
			}
			}
		if (trouverEtalLibre() != -1) {
			chaine.append("Il reste ").append(nbEtalsvide).append(" étals non utilisés dans le marché. \n");
	}
		return chaine.toString();
	}
	
	}
	
	public String afficherMarche() {
		return marche.afficherMarche();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
	    int etalLibre = marche.trouverEtalLibre();
	    if (etalLibre == -1) {
	        return "Il n'y a plus d'étals disponibles pour " + vendeur.getNom() + ".\n";
	    }
	    marche.utiliserEtal(etalLibre, vendeur, produit, nbProduit);
	    return "Le vendeur " + vendeur.getNom() + " s'est installé à l'étal " + etalLibre + " et vend " + nbProduit + " " + produit + ".\n";
	}

	public String rechercherVendeursProduit(String produit) {
	    Etal[] etalsTrouves = marche.trouverEtals(produit);
	    StringBuilder chaine = new StringBuilder();

	    if (etalsTrouves.length == 0) {
	        chaine.append("Aucun vendeur ne propose ").append(produit).append(" sur le marché.\n");
	    } else {
	        chaine.append("Les vendeurs proposant ").append(produit).append(" sont :\n");
	        for (Etal etal : etalsTrouves) {
	            chaine.append("- ").append(etal.getVendeur().getNom()).append("\n");
	        }
	    }

	    return chaine.toString();
	}

	public String partirVendeur(Gaulois vendeur) {
	    Etal etal = marche.trouverVendeur(vendeur);
	    if (etal == null) {
	        return vendeur.getNom() + " n'a pas d'étal sur le marché.\n";
	    }
	    etal.libererEtal();
	    return vendeur.getNom() + " a quitté son étal.\n";
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
	    return marche.trouverVendeur(vendeur);
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
		if(chef==null) {
			throw new VillageSansChefException("Le village "+ nom + "ne peut pas exister sans chef !");
		}
		
		
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}