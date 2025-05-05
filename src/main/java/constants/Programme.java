package constants;


public class Programme {
    private String nom;
    private String description;
    private String niveau;

    public Programme(String nom, String description, String niveau) {
        this.nom = nom;
        this.description = description;
        this.niveau = niveau;
    }

    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public String getNiveau() { return niveau; }
}
