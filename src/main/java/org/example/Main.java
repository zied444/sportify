package org.example;

import entities.Utilisateur;
import service.UtilisateurService;

public class Main {
    public static void main(String[] args) {
        UtilisateurService service = new UtilisateurService();

        Utilisateur utilisateur = new Utilisateur(
                "Doe",
                "John",
                "john.doe@example.com",
                "123456",
                "Athlete",
                "Football"
        );

        service.ajouterUtilisateur(utilisateur);

        service.listerUtilisateurs().forEach(u ->
                System.out.println(u.getNom() + " " + u.getPrenom())
        );
    }
}
