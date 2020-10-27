/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Ingrediente;
import entidade.Lanche;
import entidade.Pedido;
import entidade.Vendas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Ferreira
 */
public class DAOPedido {
    
    DAOConecta conect = new DAOConecta();
    
    public int ultimo(){
        List<Pedido> pedidos = new ArrayList<>();
        conect.abreConexao();
        int i = 0;
        try{
            Query query = conect.em.createNamedQuery("Pedido.findAll");
            
            pedidos = query.getResultList();
            
            for(Pedido p : pedidos){
                if(p.getIdPedido() > i){
                    i = p.getIdPedido();
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        conect.fechaConexao();
        return i;
        
    }
    
    public void salvarLista(List<Pedido> pedidos){
        conect.abreConexao();
        for(Pedido p : pedidos){
            conect.em.persist(p);
        }
        conect.fechaConexao();
    }
    
    public void salvarVendas(List<Pedido> pedidos){
        conect.abreConexao();
        try{
        for(Pedido p : pedidos){
            DAOLanche dAOLanche = new DAOLanche();
            List<Lanche> lanches = dAOLanche.encontrar(p.getIdLanche());
            for(Lanche l : lanches){
                DAOVendas daoVendas = new DAOVendas();
                DAOIngrediente daoIngrediente = new DAOIngrediente();
                Ingrediente ingrediente = new Ingrediente();
                List<Ingrediente> ingredientes = daoIngrediente.encontrar(l.getIdIngrediente().getId());
                Vendas vendas = new Vendas();
                vendas.setNome(ingredientes.get(0).getNome());
                vendas.setQuantidade(l.getQuantidade());
                vendas.setPreco(ingredientes.get(0).getPreco());
                vendas.setDtVenda(java.util.Calendar.getInstance().getTime());
                vendas.setIdIngrediente(ingredientes.get(0));
                daoVendas.salvar(vendas);
            }
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

}
