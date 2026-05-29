package net.abdel.pres;
import net.abdel.dao.IDao;
import net.abdel.metier.IMetier;
import java.io.File;
import java.util.Scanner;
import java.lang.reflect.Method;

public class Pres2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(
                Pres2.class.getClassLoader().getResourceAsStream("config.txt")
        );
        String daoClassName = scanner.nextLine();
        Class cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.newInstance();

        String metierClassName = scanner.nextLine();
        Class cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.newInstance();

        Method method = cMetier.getMethod("setDao", IDao.class);
        method.invoke(metier, dao); // Injection dynamique

        System.out.println("Résultat : " + metier.calcul());
    }
}
