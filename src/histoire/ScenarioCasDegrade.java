package histoire;

import villagegaulois.Etal;
import personnages.Gaulois;

public class ScenarioCasDegrade {
    public static void main(String[] args) {
        
        try {
            Etal etal = new Etal();
            etal.libererEtal();
        } catch (Exception e) {
            System.err.println("Exception : " + e.getMessage());
        }

      
        try {
            Etal etal = new Etal();
            etal.acheterProduit(5, null);
        } catch (Exception e) {
            System.err.println("Exception " + e.getMessage());
        }

        
        try {
            Etal etal = new Etal();
            Gaulois asterix = new Gaulois("Astérix", 10);
            etal.acheterProduit(-2, asterix);
        } catch (Exception e) {
            System.err.println("Exception  " + e.getMessage());
        }

        
        try {
            Etal etal = new Etal();
            Gaulois obelix = new Gaulois("Obélix", 50);
            etal.acheterProduit(3, obelix);
        } catch (Exception e) {
            System.err.println("Exception " + e.getMessage());
        }

        System.out.println("Fin du test");
    }
}