package interfaces;

import entities.Utilisateur;
import java.util.List;

public interface IUtilisateurService {
    void ajouterUtilisateur(Utilisateur utilisateur);
    Utilisateur connecter(String email, String motDePasse);
    List<Utilisateur> listerUtilisateurs();
    void modifierUtilisateur(Utilisateur utilisateur);
    void supprimerUtilisateur(int idUtilisateur);
}
