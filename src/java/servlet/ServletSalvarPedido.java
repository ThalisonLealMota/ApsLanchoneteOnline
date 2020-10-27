/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DAOPedido;
import entidade.Pedido;
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
@WebServlet(name = "ServletSalvarPedido", urlPatterns = {"/ServletSalvarPedido"})
public class ServletSalvarPedido extends HttpServlet {

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
        DAOPedido daoPedido = new DAOPedido();
        Pedido pedido = new Pedido();
        
        String submitType = request.getParameter("enviar");
        
        if(request.getSession().getAttribute("pedidos") != null && submitType.equals("Finalizar")){
            List<Pedido> pedidos =(List<Pedido>) request.getSession().getAttribute("pedidos");
            
            daoPedido.salvarLista(pedidos);
            
            daoPedido.salvarVendas(pedidos);
            
            request.getSession().setAttribute("pedidos", null);
            request.setAttribute("message", "Pedido Salvo com sucesso!");
            request.getRequestDispatcher("/home/pedidos.jsp").forward(request, response);
        }else if(submitType.equals("Cancelar")){
            request.setAttribute("message", "Todos os pedidos foram cancelados!");
            request.getSession().setAttribute("pedidos", null);
            request.getRequestDispatcher("/home/pedidos.jsp").forward(request, response);
            
            }else{
            request.setAttribute("message", "Você não tem nenhum pedido!");
            request.getRequestDispatcher("/home/pedidos.jsp").forward(request, response);
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
