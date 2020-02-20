/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import managedbean.Livre;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface InterDaoLivre {
    public void ajouterLivre(Livre l );
    public void  editerLivre(Livre l);
    public void supprimerLivre(Livre l);
    public List<Livre> getAllLivre();
    public Livre getLivre(String codelivre);
}
