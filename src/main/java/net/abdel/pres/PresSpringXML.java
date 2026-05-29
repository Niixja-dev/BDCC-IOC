package net.abdel.pres;
import net.abdel.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresSpringXML {
    public static void main(String[] args) {
        // 1. Charger le contexte Spring à partir du fichier XML
        // Ce fichier doit se trouver dans src/main/resources
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        // 2. Récupérer le bean (l'objet) de la couche métier
        // Spring a déjà créé l'objet et injecté la DAO dedans grâce au XML
        IMetier metier = (IMetier) context.getBean("metier");

        // 3. Tester le résultat
        System.out.println("Résultat via Spring XML : " + metier.calcul());
    }
}
