/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Ingrediente;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Ferreira
 */
public class DAOIngrediente {
    
    DAOConecta conect = new DAOConecta();
    
    public void salvar(Ingrediente ingrediente){
        conect.abreConexao();
        conect.em.persist(ingrediente);
        conect.fechaConexao();
    }
    
    public List<Ingrediente> listar (){
        List<Ingrediente> ingredientes = null;
        conect.abreConexao();
        try{
            ingredientes = conect.em.createNamedQuery("Ingrediente.findAll").getResultList();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        conect.fechaConexao();
        return ingredientes;
    }
    
    public List<Ingrediente> encontrar(int id){
        List<Ingrediente> ingredientes = null;
        conect.abreConexao();
        try{
            Query query = conect.em.createNamedQuery("Ingrediente.findById");
            query.setParameter("id", id);
            
            ingredientes = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        conect.fechaConexao();
        return ingredientes;
    }
    
    public List<Ingrediente> tipos(String tipo){
        List<Ingrediente> ingredientes = null;
        conect.abreConexao();
        try{
            Query query = conect.em.createNamedQuery("Ingrediente.findByTipo");
            query.setParameter("tipo", tipo);
            
            ingredientes = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return ingredientes;
    }
    
    public void alterar(Ingrediente ingrediente){
        try{
            conect.abreConexao();
            conect.em.merge(ingrediente);
            conect.fechaConexao();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
