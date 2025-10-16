package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	public class VillageSansChefException extends Exception {
		public VillageSansChefException() {
			super("Le village n'a pas de chef!");
		}

		public VillageSansChefException(String message) {
			super(message);
		}
	}
	
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	Marche marche;
	
	
	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	    marche = new Marche(nbEtals);
	}
	

	private static class Marche {
		private Etal[] etals;
		
		private Marche(int nbetalmarche) {
			etals = new Etal[nbetalmarche];
			for (int i = 0; i < nbetalmarche; i++) {
				etals[i] = new Etal(); 
			}
		}
		
		 private void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit){
			 etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
		 }
		 
		 private int trouverEtalLibre() {
			 for(int i=0; i<etals.length;i++) {
				 if(etals[i].isEtalOccupe() == false) {
					return i;
				 }
			 }
			 return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int c =0;
			for(int i=0; i<etals.length; i++) {
				if(etals[i].contientProduit(produit)){
					c++;
				}
			}
			Etal[] etalsproduit = new Etal[c];
			int j=0;
			for(int i=0; i<etals.length; i++) {
				if(etals[i].contientProduit(produit)) {
					etalsproduit[j]=etals[i];
					j++;
				}
			}
			return etalsproduit;
		}
		
		 private Etal trouverVendeur(Gaulois gaulois) {
			 for(int i=0; i<etals.length; i++) {
				if(etals[i].getVendeur()== gaulois){
					return etals[i];
				}
			}
			return null;
		 }
		 
		 private String afficherMarche() {
			 int nbetalvide = 0;
			 String texte="";
			 for(int i=0; i<etals.length; i++) {
					if(etals[i].isEtalOccupe() == false){
						nbetalvide++;
					}
					else {
						texte = texte + etals[i].afficherEtal()+ "";
					}
			 }
			 texte += "Il reste " + nbetalvide + "etait non utilisés dans le marché .\n";
			 return texte;
		 }
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

	/*public String afficherVillageois() {
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
	}*/
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit){
		String texte= "\n" + vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit +" "+ produit + "." + "\n";
		int i=marche.trouverEtalLibre();
		if(i !=-1) {
			marche.utiliserEtal(i,vendeur,produit,nbProduit);
			texte += "le vendeur "+ vendeur.getNom() +" vend des "+ produit + "dans l'étal n°"+ i;
			return texte;
					
		}
		return texte;
	}
	
	 public String rechercherVendeursProduit(String produit){
		 String texte = "";
		 Etal[] e = marche.trouverEtals(produit);
		 int i = e.length;
		 if(i == 0) {
			 texte +="\nIl n' y a de vendeur qui propose des " + produit + " au marché\n";
			 return texte;
		 }else if(i == 1){
			 Gaulois vendeur = e[0].getVendeur();
			 texte +="\nSeul le vendeur " + vendeur.getNom()+ " propose des " + produit +" au marché \n";
			 return texte;
		 }else{
			 texte += "\nLes Vendeurs qui proposent des " + produit + " sont  : \n";
			 for (int j = 0; j < i; j++) {
				Gaulois vendeur = e[j].getVendeur();
				texte += "- " + vendeur.getNom() + "\n"; 
			}
			return texte;
		 }
	 }
	 
	 public Etal rechercherEtal(Gaulois vendeur) {
		 return marche.trouverVendeur(vendeur);
	 }
	 
	 public String partirVendeur(Gaulois vendeur) {
		 String message = rechercherEtal(vendeur).libererEtal();
		 return message;
	 }
	 
	 public String afficherMarche() {
		 StringBuilder chaine = new StringBuilder();
		 chaine.append(marche.afficherMarche());
		 return chaine.toString();
	 }
	 
	 public String afficherVillageois() throws VillageSansChefException {
			if (chef == null) {
				throw new VillageSansChefException();
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