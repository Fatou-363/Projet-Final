package controllers;

import models.Utilisateur;
import views.html.*;
import play.mvc.*;
import java.util.List ; 
import play.data.* ;
import javax.inject.Inject; 
import play.i18n.MessagesApi;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class UserController extends Controller {

    @Inject
    FormFactory formFactory; 
    Form <Utilisateur> utilisateurForm ;
    MessagesApi messagesApi;
    
    @Inject
    public UserController(FormFactory formFactory, MessagesApi messagesApi) {
     this.utilisateurForm = formFactory.form(Utilisateur.class);
     this.messagesApi = messagesApi;
    }
    
    
     public Result membre() {
        List<Utilisateur> listeUtilisateur = Utilisateur.find.all();
        return ok(membre.render(listeUtilisateur));
    }
    
    public Result inscription (Http.Request request) {
        utilisateurForm = formFactory.form(Utilisateur.class) ;
        return ok(inscription.render(utilisateurForm, request, messagesApi.preferred(request))) ; 
    }
    
    public Result inscriptionForm(Http.Request request) {
       final Form<Utilisateur> userForm = utilisateurForm.bindFromRequest(request) ; 
       // if (pForm.hasErrors()){
    //      return badRequest(sayhelloform.render(lForm,request, messagesApi.preferred(request)));
    //    }else{
            Utilisateur user = userForm.get();
            user.save();
            return redirect(routes.UserController.membre()); 
      //  }
    }
    public Result connexion (Http.Request request) {
        utilisateurForm = formFactory.form(Utilisateur.class) ;
        return ok(connexion.render(utilisateurForm, true, request, messagesApi.preferred(request))) ; 
    }
    
    public Result connexionForm(Http.Request request) {
       final Form<Utilisateur> userForm = utilisateurForm.bindFromRequest(request) ; 
        if (userForm.hasErrors()){
          return badRequest(connexion.render(userForm, false, request, messagesApi.preferred(request)));
        }else{
            Utilisateur user = userForm.get();
            List<Utilisateur> utilisateurList = Utilisateur.find.all();
            for(Utilisateur utilisateur : utilisateurList){
                if(user.pseudo.equals(utilisateur.pseudo) && user.motDepasse.equals(utilisateur.motDepasse)  ){
                    return redirect(routes.HomeController.index())
                        .addingToSession(request, "connected", utilisateur.pseudo);
                }
            }
            return ok(connexion.render(utilisateurForm, false, request, messagesApi.preferred(request))) ; 
        }
    }
    

}
    
