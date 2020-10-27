/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
/**
 *
 * @author Ferreira
 */
public class DAOConecta {
    
    private static EntityManagerFactory emf;
    public static EntityManager em;
    
    public void abreConexao(){
        emf = Persistence.createEntityManagerFactory("TesteAPS5PU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    
    public void fechaConexao() {
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
