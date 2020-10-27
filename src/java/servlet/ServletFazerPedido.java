/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DAOIngrediente;
import dao.DAOLanche;
import dao.DAOPedido;
import entidade.Ingrediente;
import entidade.Lanche;
import entidade.Pedido;
import entidade.Pessoa;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ferreira
 */
@WebServlet(name = "ServletFazerPedido", urlPatterns = {"/ServletFazerPedido"})
public class ServletFazerPedido extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOIngrediente dAOIngrediente = new DAOIngrediente();
        
        
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOIngrediente daoIngrediente = new DAOIngrediente();
        DAOLanche dAOLanche = new DAOLanche();
        
        String idp = request.getParameter("cbIngrediente");
        String submitType = request.getParameter("enviar");
        
        List<Lanche> lanches = new ArrayList<>();
        
        if(!idp.equals("Escolha um Ingrediente") && submitType.equals("Adicionar")){
            
            Lanche lanche = new Lanche();
            
            List<Ingrediente> ingredientes = daoIngrediente.encontrar(Integer.parseInt(idp));
           
            if(request.getSession().getAttribute("list") != null){
            lanches = (List)request.getSession().getAttribute("list");
            }
            
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setId(ingredientes.get(0).getId());
            
            lanche.setIdLanche(dAOLanche.ultimo() + 1);
            lanche.setIdIngrediente(ingrediente);
            lanche.setQuantidade(Integer.parseInt(request.getParameter("quantidadeingrediente")));
            lanche.setPreco(ingredientes.get(0).getPreco() * lanche.getQuantidade());
            
            int i = 0;
            if(lanches.isEmpty()){
                lanches.add(lanche);
            }else{
                for(Lanche l : lanches){
                    if(l.getIdIngrediente().equals(lanche.getIdIngrediente())){
                       int sqtd = l.getQuantidade() + lanche.getQuantidade();
                       l.setQuantidade(sqtd);
                       float sprc = l.getPreco() + lanche.getPreco();
                       l.setPreco(sprc);
                    }else{
                        i++;
                    }
                }
                if(lanches.size() == i){
                    lanches.add(lanche);
                }
            }
            
            
            request.getSession().setAttribute("list", lanches);
            request.setAttribute("message", ingredientes.get(0).getNome() + " Adicionado");
            
            request.getRequestDispatcher("/home/fazerpedido.jsp").forward(request, response);
        }else if(submitType.equals("Finalizar")){
            
            if(request.getSession().getAttribute("list") != null){
            lanches = (List) request.getSession().getAttribute("list");
            int i = 0;
            int p = 0;
            int c = 0;
            
            for(Lanche l : lanches){
                i = 0;
                List<Ingrediente> ingredientes = daoIngrediente.tipos("Pao");
                
                if(p != 8){
                while(ingredientes.size() > i){
                if(ingredientes.get(i).getId().equals(l.getIdIngrediente().getId())){
                    p = 8;
                    break;
                }else{
                    i++;
                }
                }
                }
            }
            
            for(Lanche l : lanches){
                i = 0;
                List<Ingrediente> ingredientes = daoIngrediente.tipos("Carne");
                if(c != 8){
                while(ingredientes.size() > i){
                if(ingredientes.get(i).getId().equals(l.getIdIngrediente().getId())){
                    c = 8;
                    break;
                }else{
                    i++;
                }
                }
                }
            }
            
            
            if(p == 8 && c == 8){
                
                
                List<Pedido> pedidos = new ArrayList<>();
                
                if(request.getSession().getAttribute("pedidos") != null){
                    pedidos = (List<Pedido>) request.getSession().getAttribute("pedidos");
                }
                
                int idpes =(Integer) request.getSession().getAttribute("CURRENT_ID");
                DAOPedido daoPedido = new DAOPedido();
                Pedido pedido = new Pedido();
                pedido.setIdPedido(daoPedido.ultimo() + 1);
                pedido.setIdLanche(dAOLanche.ultimo() + 1);
                Pessoa pessoa = new Pessoa();
                pessoa.setId(idpes);
                pedido.setIdPessoa(pessoa);
            dAOLanche.salvarLista(lanches);
                pedido.setPreco(dAOLanche.somaPreco(pedido.getIdLanche()));
                
                pedidos.add(pedido);
                
                request.getSession().setAttribute("pedidos", pedidos);
                
            
            request.getSession().setAttribute("list", null);
            request.setAttribute("message", "Pedido Finalizado com Sucesso");
            request.getRequestDispatcher("/home/fazerpedido.jsp").forward(request, response);
            }else{
                request.setAttribute("message", "Você precisa escolher no minimo um Pão e uma carne");
                request.getRequestDispatcher("/home/fazerpedido.jsp").forward(request, response);
            }
            }else{
                request.setAttribute("message", "Você precisa escolher ingredientes");
                request.getRequestDispatcher("/home/fazerpedido.jsp").forward(request, response);
            }
            
        }else if(submitType.equals("Limpar")){
            request.getSession().setAttribute("list", null);
            request.getRequestDispatcher("/home/fazerpedido.jsp").forward(request, response);
        }else if(idp.equals("Escolha um Ingrediente") && submitType.equals("Adicionar")){
            request.setAttribute("message", "Você precisa escolher um ingrediente");
            request.getRequestDispatcher("/home/fazerpedido.jsp").forward(request, response);
        }

        
        
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
