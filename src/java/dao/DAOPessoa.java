/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Pessoa;
import java.util.List;
import javax.persistence.Query;




/**
 *
 * @author Ferreira
 */
public class DAOPessoa {
    
    DAOConecta conect = new DAOConecta();
    
    public void salvar(Pessoa pessoa){
        conect.abreConexao();
        conect.em.persist(pessoa);
        conect.fechaConexao();
    }
    
    public List<Pessoa> login(String nome, String senha){
        List<Pessoa> pessoas = null;
        conect.abreConexao();
        
        try{
            Query query = conect.em.createNamedQuery("Pessoa.findByLogin");
            query.setParameter("username", nome);
            query.setParameter("senha", senha);
            pessoas = query.getResultList();
            conect.fechaConexao();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return pessoas;
    }
    
}
