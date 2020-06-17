package models ;

import java.util.*;
import io.ebean.*;
import javax.persistence.*;


@Entity
public class Livre extends Model {
  
    private static final long serialVersionUID = 1L;

    @Id
    public long id;   
    public String titre ;
    public String auteur ;
    public Date dateEdition ;
    public String categorie ;
    public String description ;
    public String isEmprunte ;
    public boolean isEmpruntable ;
    public boolean EnReparation ;
    public boolean isPerdu ;
    
    
    public Livre(String auteur, Date dateEdition, String titre, String categorie, String description){
        this.auteur=auteur;
        this.dateEdition=dateEdition;
        this.titre=titre;
        this.categorie=categorie;
        this.description=description;
        this.isEmprunte= null;
        this.isEmpruntable=true;
        this.EnReparation=true;
        this.isPerdu=true;
    }
    
    public String getAuteur(){
        return this.auteur;
    }
    public void setAuteur(){
         this.auteur = auteur;
    }
    public String getTitre(){
        return this.titre;
    }
    public void setTitre(){
        this.titre = titre ;
    }
    public Date getDateEdition(){
        return this.dateEdition;
    }
    public void setDateEdition(Date dateEdition){
       this.dateEdition = dateEdition;
    }
     public String getCategorie(){
        return this.categorie;
    }
    public void setCategorie(){
         this.categorie = categorie;
    }
     public String getDescription(){
        return this.description;
    }
    public void setDescription(){
         this.description = description;
    }
    
    
    
    public static Finder<Long, Livre> find = new Finder<Long,Livre>(Livre.class);
}