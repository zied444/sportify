package Services;

import Models.Evenement;
import utils.mydb;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EvenementService implements IServices<Evenement> {
    Connection conn;

    public EvenementService() {
        this.conn = mydb.getInstance().getConn();
    }

    @Override
    public void create(Evenement evenement) throws Exception {
        // Vérifier si l'utilisateur existe
        String checkUserQuery = "SELECT COUNT(*) FROM utilisateur WHERE id_utilisateur = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkUserQuery);
        checkStmt.setInt(1, evenement.getUtilisateur_id());
        ResultSet rs = checkStmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        if (count == 0) {
            throw new Exception("L'utilisateur avec l'ID " + evenement.getUtilisateur_id() + " n'existe pas");
        }

        String req = "INSERT INTO evenement (nom, lieu, date, type, description, nbr_places, utilisateur_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(req);
        {
            pstmt.setString(1, evenement.getNom());
            pstmt.setString(2, evenement.getLieu());
            pstmt.setDate(3, evenement.getDate());
            pstmt.setString(4, evenement.getType());
            pstmt.setString(5, evenement.getDescription());
            pstmt.setInt(6, evenement.getNbr_places());
            pstmt.setInt(7, evenement.getUtilisateur_id());

            pstmt.executeUpdate();
            System.out.println("Événement ajouté !");
        }
    }

    @Override
    public void update(Evenement evenement) throws Exception {
        String req = "UPDATE evenement SET nom=?, lieu=?, date=?, type=?, description=?, nbr_places=? WHERE id_evenement=?";
        PreparedStatement pstmt = conn.prepareStatement(req);

        pstmt.setString(1, evenement.getNom());
        pstmt.setString(2, evenement.getLieu());
        pstmt.setDate(3, evenement.getDate());
        pstmt.setString(4, evenement.getType());
        pstmt.setString(5, evenement.getDescription());
        pstmt.setInt(6, evenement.getNbr_places());
        pstmt.setInt(7, evenement.getId());

        pstmt.executeUpdate();
        System.out.println("Événement modifié !");

    }


    @Override
    public void delete(Evenement evenement) throws Exception {
        String req = "DELETE FROM Evenement WHERE id_evenement=?";
        PreparedStatement pstmt = conn.prepareStatement(req);
        pstmt.setInt(1, evenement.getId());
        pstmt.executeUpdate();
        System.out.println("Événement supprimé !");

    }

    @Override
    public List<Evenement> display() throws Exception {
        String req = "SELECT * FROM evenement";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(req);
        List<Evenement> listevent = new ArrayList<>();
        while (rs.next()) {
            Evenement e = new Evenement();
            e.setId(rs.getInt("id_evenement"));
            e.setNom(rs.getString("nom"));
            e.setLieu(rs.getString("lieu"));
            e.setDate(rs.getDate("date"));
            e.setType(rs.getString("type"));
            e.setDescription(rs.getString("description"));
            e.setUtilisateur_id(rs.getInt("utilisateur_id"));
            e.setNbr_places(rs.getInt("nbr_places"));
            e.setNbr_personne_inscrit(rs.getInt("nbr_personne_inscrit"));

            listevent.add(e);
        }
        return listevent;
    }
}





