package controllers;

import models.Utilisateur;
import models.Livre;
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

public class HomeController extends Controller {

    @Inject
    FormFactory formFactory; 
    Form <Utilisateur> UtilisateurForm ;
    Form <Livre> livreForm ;
    MessagesApi messagesApi;
    
    @Inject
    public HomeController(FormFactory formFactory, MessagesApi messagesApi) {
     this.UtilisateurForm = formFactory.form(Utilisateur.class);
     this.livreForm = formFactory.form(Livre.class);
     this.messagesApi = messagesApi;
    }
    
    /*
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index(Http.Request request) {
        List<Livre> listeLivre = Livre.find.all();
        return request
              .session()
              .get("connected")
              .map(user -> ok(index.render(listeLivre,user)))
              .orElseGet(() -> ok(index.render(listeLivre,"annonyme")));
    }
    
    
    
    public Result creerLivre (Http.Request request) {
        livreForm = formFactory.form(Livre.class) ;
        return ok(creationLivre.render(livreForm, request, messagesApi.preferred(request))) ; 
    }
    
    public Result creerLivreform(Http.Request request) {
       final Form<Livre> lForm = livreForm.bindFromRequest(request) ; 
       // if (pForm.hasErrors()){
    //      return badRequest(sayhelloform.render(lForm,request, messagesApi.preferred(request)));
    //    }else{
            Livre livre = lForm.get();
            livre.save();
            return redirect(routes.HomeController.index()) ;
      //  }
    }
    
    
    public Result show(long id, Http.Request request){
        Livre livre = Livre.find.byId(id);
        return request
              .session()
              .get("connected")
              .map(user -> ok(show.render(livre,user)))
              .orElseGet(() -> ok(show.render(livre,"annonyme")));
    }
    
     public Result delete(long id){
        Livre livre = Livre.find.byId(id);
        livre.delete();
        return redirect(routes.HomeController.index());
    }
    
    public Result update(long id, Http.Request request){
        livreForm = formFactory.form(Livre.class) ;
        Livre livre = Livre.find.byId(id);
        return ok(modificationLivre.render(livreForm.fill(livre), id, request, messagesApi.preferred(request)));
    }
    
     public Result updateOk(Long id, Http.Request request){
        Form<Livre> lform = livreForm.bindFromRequest(request);
        if (lform.hasErrors()){
             return badRequest(modificationLivre.render(lform, id, request,  messagesApi.preferred(request)));
        } else {
             Livre livre = lform.get();
             livre.setId(id);
             livre.update();
             return redirect(routes.HomeController.show(id));
         }
     }
    
    public Result emprunter(long id, String pseudo,  Http.Request request){
        Livre livre = Livre.find.byId(id);
        livre.isEmprunte = pseudo ;
        livre.setId(id);
        livre.update();
        return request
              .session()
              .get("connected")
              .map(user -> ok(show.render(livre,user)))
              .orElseGet(() -> ok(show.render(livre,"annonyme")));
    }
        
    public Result rendre(long id,  Http.Request request){
        Livre livre = Livre.find.byId(id);
        livre.isEmprunte = null ;
        livre.setId(id);
        livre.update();
        return request
              .session()
              .get("connected")
              .map(user -> ok(show.render(livre,user)))
              .orElseGet(() -> ok(show.render(livre,"annonyme")));
    }
    
    public Result empruntable(long id,  Http.Request request){
        Livre livre = Livre.find.byId(id);
        livre.isEmpruntable = !livre.isEmpruntable ;
        livre.setId(id);
        livre.update();
        return request
              .session()
              .get("connected")
              .map(user -> ok(show.render(livre,user)))
              .orElseGet(() -> ok(show.render(livre,"annonyme")));
    }
    
   public Result reparation(long id,  Http.Request request){
        Livre livre = Livre.find.byId(id);
        livre.EnReparation = !livre.EnReparation ;
        livre.setId(id);
        livre.update();
        return request
              .session()
              .get("connected")
              .map(user -> ok(show.render(livre,user)))
              .orElseGet(() -> ok(show.render(livre,"annonyme")));
    }
    
    public Result perdu(long id,  Http.Request request){
        Livre livre = Livre.find.byId(id);
        livre.isPerdu = !livre.isPerdu ;
        livre.setId(id);
        livre.update();
        return request
              .session()
              .get("connected")
              .map(user -> ok(show.render(livre,user)))
              .orElseGet(() -> ok(show.render(livre,"annonyme")));
    }
    
     
    
  

}
