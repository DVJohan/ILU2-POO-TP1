package histoire;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
    
    public static void main(String[] args) { 
        System.out.println("=== Test des exceptions pour la classe Etal ===");
        testExceptionsEtal();
        
        System.out.println("\n=== Test des exceptions pour la classe Village ===");
        testExceptionsVillage();
    }
    
    private static void testExceptionsEtal() {
        Etal etal = new Etal();
        Gaulois acheteur = new Gaulois("Astérix", 10);
        Gaulois vendeur = new Gaulois("Obélix", 15);
        
        // Test 1: "LibérerÉtal" sans vendeur
        System.out.println("Test 1: Libération d'un étal non occupé");
        try {
            String resultat = etal.libererEtal();
            System.out.println(resultat);  // Devrait afficher un message d'erreur
        } catch(IllegalStateException e) {
            System.err.println("Exception attendue: " + e.getClass().getSimpleName());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace(System.err);
        }
        
        // Test 2: "AcheterProduit" sur un étal non occupé
        System.out.println("\nTest 2: Achat sur un étal non occupé");
        try {
            etal.acheterProduit(5, acheteur);
        } catch(IllegalStateException e) {
            System.err.println("Exception attendue: " + e.getClass().getSimpleName());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace(System.err);
        }
        
        /* Test 3: "AcheterProduit" avec une quantité négative*/
        System.out.println("\nTest 3: Achat avec une quantité négative");
        try {
            etal.occuperEtal(vendeur, "cailloux", 10);
            etal.acheterProduit(-2, acheteur);
        } catch (IllegalArgumentException e) {
            System.err.println("Exception attendue: " + e.getClass().getSimpleName());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace(System.err);
        }
        
        // Test 4: "AcheterProduit" avec un acheteur null
        System.out.println("\nTest 4: Achat avec un acheteur null");
        try {
            etal.acheterProduit(5, null);
        } catch(NullPointerException e) {
            System.err.println("Exception attendue: " + e.getClass().getSimpleName());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace(System.err);
        }
        
        // Test 5: "OccuperEtal" avec un vendeur null
        System.out.println("\nTest 5: Occupation d'étal avec vendeur null");
        try {
            etal.libererEtal();  // Libérons l'étal d'abord
            etal.occuperEtal(null, "sangliers", 5);
        } catch(IllegalArgumentException e) {
            System.err.println("Exception attendue: " + e.getClass().getSimpleName());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    private static void testExceptionsVillage() {
    	
    	// Test du cas où le village n'a pas de chef
        Village village = new Village("Village sans chef", 10, 5);
        try {
            String infoVillageois = village.afficherVillageois();
            System.out.println(infoVillageois);
        } catch (Village.VillageSansChefException e) {
            System.out.println("Une erreur est survenue lors de l'affichage des villageois : " + e.getMessage());
            System.out.println("Il faut d'abord désigner un chef pour ce village.");
        }

    }
    
}