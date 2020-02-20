/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dao.ImpDAO_Livre;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author DELL
 */
@ManagedBean
@RequestScoped
public class Livre {
    private String code_livre,titre;
    private double prix;
    private String dateachat;
    private String  idediteur;

    /**
     * Creates a new instance of Livre
     */
    public Livre() {
    }

    public Livre(String code_livre, String titre, double prix, String idediteur) {
        this.code_livre = code_livre;
        this.titre = titre;
        this.prix = prix;
        this.idediteur = idediteur;
    }

    
    public String getCode_livre() {
        return code_livre;
    }

    public void setCode_livre(String code_livre) {
        this.code_livre = code_livre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDateachat() {
        return dateachat;
    }

    public void setDateachat(String dateachat) {
        this.dateachat = dateachat;
    }

    public String getIdediteur() {
        return idediteur;
    }

    public void setIdediteur(String idediteur) {
        this.idediteur = idediteur;
    }
    public void addBook(){
        ImpDAO_Livre dao = new ImpDAO_Livre();
        dao.ajouterLivre(this);
    }
    public void deleteBook(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String code= params.get("action");
        code_livre=code;
        ImpDAO_Livre dao = new ImpDAO_Livre();
        dao.supprimerLivre(this);
    }
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    public String updateBook(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String fieldCode= params.get("action");
        ImpDAO_Livre dao = new ImpDAO_Livre();
        Livre l=dao.getLivre(fieldCode);
        sessionMap.put("editLivre", l);  
        return "/edit.xhtml?faces-redirect=true"; 

    }
    
    public String editBook(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	code_livre = params.get("updateCode");
        titre = params.get("updateTitre");
        try{
        prix = Float.parseFloat(params.get("updatePrix"));
        idediteur = params.get("updateIdEditeur");
        }catch(Exception e){
            
        }
        ImpDAO_Livre dao = new ImpDAO_Livre();
        Livre l = new Livre(code_livre,titre,prix,idediteur);
        dao.editerLivre(this);

       return "/index.xhtml?faces-redirect=true";   
}

    public ArrayList<Livre> allLivre(){
        ImpDAO_Livre dao = new ImpDAO_Livre();
        ArrayList<Livre> list = (ArrayList<Livre>) dao.getAllLivre();
        return list;
    }

    public Livre(String code_livre, String titre, double prix, String dateachat, String idediteur) {
        this.code_livre = code_livre;
        this.titre = titre;
        this.prix = prix;
        this.dateachat = dateachat;
        this.idediteur = idediteur;
    }
   
    
}
