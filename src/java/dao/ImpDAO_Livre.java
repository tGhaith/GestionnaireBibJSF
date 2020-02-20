/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import managedbean.Livre;
import utilitaire.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;


/**
 *
 * @author DELL
 */
public class ImpDAO_Livre implements InterDaoLivre{
    private Connection c;
	@Override
	public void ajouterLivre(Livre l) {
		Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		// TODO Auto-generated method stub
		try{
            c=Singleton.getConn();
            String query ="INSERT INTO livre VALUES('"+l.getCode_livre()+"','"+l.getTitre()+
                "','"+l.getPrix()+"','"+ourJavaTimestampObject+"','"+l.getIdediteur()+"');";
            PreparedStatement ps = c.prepareStatement(query);
        
            ps.executeUpdate();
              }catch (SQLException e1) 
        {
            System.err.println("Error executing query: " + e1.getMessage());
        }
	}

	@Override
	public void editerLivre(Livre l) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		try{
		       c= Singleton.getConn();
		        Statement st = c.createStatement();
		       String query ="UPDATE livre set codelivre='"+l.getCode_livre()+"',titre='"+l.getTitre()+"',prix='"+
		                l.getPrix()+"',dateachat='"+ourJavaTimestampObject+"' WHERE codelivre ='"+l.getCode_livre()+"';";
		        st.executeUpdate(query);
		        
		       }catch(SQLException e ){
		       System.out.print(e.getMessage());
		}
		
	}

	@Override
	public void supprimerLivre(Livre l) {
		// TODO Auto-generated method stub
		try{
	        c=Singleton.getConn();
	        Statement st = c.createStatement();
	        String query ="DELETE FROM livre WHERE codelivre='"+l.getCode_livre()+"'";
	            System.out.println(query);
	        st.executeUpdate(query);
	        } catch (Exception e) {
	        e.printStackTrace(); 
	        }
	}

	@Override
	public List<Livre> getAllLivre() {
		// TODO Auto-generated method stub
		List<Livre>list=new ArrayList<Livre>();
        try{
            Statement s=Singleton.getConn().createStatement();
            String query ="select * from livre;";
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
            	Livre livre = new Livre(rs.getString("codelivre"),
                        rs.getString("titre"),
                        rs.getDouble("prix"),
                        rs.getDate("dateachat").toString(),
                        rs.getString("idediteur"));
            	
            	
                list.add(livre);
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return list;
	}

	@Override
	public Livre getLivre(String codelivre) {
		// TODO Auto-generated method stub
		Livre livre = null;
		try{
		       Connection c=Singleton.getConn();
		        Statement st = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);    
		           String query="SELECT * FROM livre WHERE codelivre='"+codelivre+"';";
		           ResultSet RS = st.executeQuery(query);
		            while(RS.next()) {
		            	livre = new Livre(RS.getString("codelivre"),RS.getString("titre"),RS.getDouble("prix"),RS.getDate("dateachat").toString(),RS.getString("idediteur"));
		            }
		            
		           }catch(SQLException e ){
		       System.out.print(e.getMessage());
		       return livre;
		           }
		        return livre;
	}
	

}
