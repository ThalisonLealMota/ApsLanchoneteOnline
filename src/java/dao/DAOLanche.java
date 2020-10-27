/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Lanche;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javax.persistence.Query;

/**
 *
 * @author Ferreira
 */
public class DAOLanche {
    
    DAOConecta conect = new DAOConecta();
    
    public int ultimo(){
        List<Lanche> lanches = new ArrayList<>();
        conect.abreConexao();
        int i = 0;
        try{
            Query query = conect.em.createNamedQuery("Lanche.findAll");
            
            lanches = query.getResultList();
            
            for(Lanche l : lanches){
               if(l.getIdLanche() > i){
                   i = l.getIdLanche();
               }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        conect.fechaConexao();
        return i;
    }
    
    public void salvarLista(List<Lanche> lanches){
        conect.abreConexao();
        Lanche lanche = new Lanche();
        for(Object obj : lanches){
            lanche = (Lanche)obj;
            conect.em.persist(lanche);
        }
        conect.fechaConexao();
    }
    
    public void salvar(Lanche lanche){
        conect.abreConexao();
        conect.em.persist(lanche);
        conect.fechaConexao();
    }
    
        public float somaPreco(int i){
        List<Lanche> lanches = new ArrayList<>();
        float sp = 0;
        conect.abreConexao();
        try{
            Query query = conect.em.createNamedQuery("Lanche.findByIdLanche");
            query.setParameter("idLanche", i);
            
            lanches = query.getResultList();
            for(Lanche l: lanches){
                sp += l.getPreco();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        conect.fechaConexao();
        return sp;
    }
        
        public List<Lanche> encontrar(int i){
            List<Lanche> lanches = new ArrayList<>();
            conect.abreConexao();
            try{
                Query query = conect.em.createNamedQuery("Lanche.findByIdLanche");
                query.setParameter("idLanche", i);
                
                lanches = query.getResultList();
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
            conect.fechaConexao();
            return lanches;
        }
    
    
}
