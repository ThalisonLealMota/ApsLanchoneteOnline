/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Vendas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Ferreira
 */
public class DAOVendas {
    
    DAOConecta conect = new DAOConecta();
    
    
    public void salvar (Vendas vendas){
        conect.abreConexao();
        conect.em.persist(vendas);
        conect.fechaConexao();
    }
    
    public List<Vendas> listarVendas(Date dt1, Date dt2){
        List<Vendas> vendas = new ArrayList<>();
        conect.abreConexao();
        try{
            Query query = conect.em.createNamedQuery("Vendas.findBetweenDtVenda");
            query.setParameter("dtVenda1", dt1);
            query.setParameter("dtVenda2", dt2);
            
            
             
            vendas = query.getResultList(); 
        }catch(Exception e){
            e.printStackTrace();
        }
        conect.fechaConexao();
        return vendas;
    }
    
}
