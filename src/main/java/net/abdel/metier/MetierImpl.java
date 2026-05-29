package net.abdel.metier;
import net.abdel.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetierImpl implements IMetier {
    private IDao dao; // Couplage faible : on utilise l'interface

    @Override
    public double calcul() {
        double d = dao.getData();
        return d * 10;
    }

    // Indispensable pour l'injection des dépendances
    @Autowired
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}