package net.abdel.pres;
import net.abdel.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PresSpringAnnotations {
    public static void main(String[] args) {
        // Scan des packages pour trouver les @Component
        ApplicationContext context = new AnnotationConfigApplicationContext("net.abdel.dao", "net.abdel.metier");
        IMetier metier = context.getBean(IMetier.class);
        System.out.println("Résultat Spring Annotations : " + metier.calcul());
    }
}
