package models ;

import java.util.*;
import io.ebean.*;
import javax.persistence.*;


@Entity
public class Utilisateur extends Model {
  
    
    private static final long serialVersionUID = 1L;
    
    @Id
    public long id;
    public String pseudo ;
    public String nom;
    public String prenom;
    public String motDepasse ;
    public String status ; 
   
    
    public Utilisateur(String pseudo, String nom, String prenom, String motDepasse, String status){
        this.pseudo=pseudo;
        this.nom=nom;
        this.prenom=prenom;
        this.motDepasse=motDepasse;
        this.status=status;
    }
    
    public String getNom(){
        return this.nom;
    }
    
    public void setNom(String nom){
        this.nom = nom;
    }
    
    public String getPrenom(){
        return prenom;
    }
    
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    
     public String getpSeudo(){
        return this.pseudo;
    }
    
    public void setPseudo(String pseudo){
        this.pseudo = pseudo;
    }
    
      public String getStatus(){
        return this.status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public void setMotDePasse(String motDepasse){
        this.motDepasse = motDepasse;
    }
    
    public String getMotDePasse(){
        return this.motDepasse;
    }
        
  
     public static Finder<Long, Utilisateur> find = new Finder<Long,Utilisateur>(Utilisateur.class);
}
    