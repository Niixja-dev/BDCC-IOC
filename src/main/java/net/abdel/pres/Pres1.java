package net.abdel.pres;
import net.abdel.dao.DaoImpl;
import net.abdel.metier.MetierImpl;

public class Pres1 {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao); // Injection manuelle
        System.out.println("Résultat : " + metier.calcul());
    }
}
