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
import java.math.BigInteger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ferreira
 */
@WebServlet(name = "ServletSalvarIngrediente", urlPatterns = {"/ServletSalvarIngrediente"})
public class ServletSalvarIngrediente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        
        DAOIngrediente dAOIngrediente = new DAOIngrediente();
        Ingrediente ingrediente = new Ingrediente();
        
        String nome = request.getParameter("nome");
        String tipo = request.getParameter("tipo");

        
        if(nome.equals("") || tipo.equals("")){
            request.setAttribute("sucmessage", "Algum dos valores está vazio!");
        request.getRequestDispatcher("/home/cadastroingredientes.jsp").forward(request, response);
        }
        else{
        
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        float preco = Float.parseFloat(request.getParameter("preco"));    
            
        ingrediente.setNome(nome);
        ingrediente.setTipo(tipo);
        ingrediente.setQuantidade(quantidade);
        ingrediente.setPreco((preco));
        
        dAOIngrediente.salvar(ingrediente);
        
        request.setAttribute("sucmessage", "Salvo com Sucesso!");
        request.getRequestDispatcher("/home/cadastroingredientes.jsp").forward(request, response);
        
        
        
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
