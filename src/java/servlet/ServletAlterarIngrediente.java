/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DAOIngrediente;
import entidade.Ingrediente;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ServletAlterarIngrediente", urlPatterns = {"/ServletAlterarIngrediente"})
public class ServletAlterarIngrediente extends HttpServlet {

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
        Ingrediente ingrediente = new Ingrediente();
        
        String submitType = request.getParameter("enviar");
        
        if(submitType.equals("Alterar")){
        if(!request.getParameter("idingrediente").equals("")){
            ingrediente.setId(Integer.parseInt(request.getParameter("idingrediente")));
            List<Ingrediente> ingredientes = daoIngrediente.encontrar(Integer.parseInt(request.getParameter("idingrediente")));
            if(!request.getParameter("nomeingrediente").equals("")){
            ingrediente.setNome(request.getParameter("nomeingrediente"));
            }else{
                ingrediente.setNome(ingredientes.get(0).getNome());
            }
            if(!request.getParameter("tipoingrediente").equals("")){
                ingrediente.setTipo(request.getParameter("tipoingrediente"));
            }else{
                ingrediente.setTipo(ingredientes.get(0).getTipo());
            }
            if(!request.getParameter("quantidadeingrediente").equals("")){
            ingrediente.setQuantidade(Integer.parseInt(request.getParameter("quantidadeingrediente")));
            }else{
                ingrediente.setQuantidade(ingredientes.get(0).getQuantidade());
            }
            if(!request.getParameter("precoingrediente").equals("")){
            ingrediente.setPreco(Float.parseFloat(request.getParameter("precoingrediente")));
            }else{
                ingrediente.setPreco(ingredientes.get(0).getPreco());
            }
            
            daoIngrediente.alterar(ingrediente);
            
            request.setAttribute("message", "Alterado com sucesso");
            request.getRequestDispatcher("/home/estoque.jsp").forward(request, response);
        }else{
            request.setAttribute("message", "Digite o id que deseja alterar");
            request.getRequestDispatcher("/home/estoque.jsp").forward(request, response);
        }
        }else if (submitType.equals("Excluir")){
            if(!request.getParameter("idingrediente").equals("")){
                ingrediente.setId(Integer.parseInt(request.getParameter("idingrediente")));
                daoIngrediente.exclur(ingrediente);
                
                request.setAttribute("message", "Removido com sucesso");
                request.getRequestDispatcher("/home/estoque.jsp").forward(request, response);
                
            }else{
                request.setAttribute("message", "Digite o id que deseja excluir");
                request.getRequestDispatcher("/home/estoque.jsp").forward(request, response);
            }
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
