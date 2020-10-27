/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DAOVendas;
import entidade.Vendas;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ferreira
 */
@WebServlet(name = "ServletVendas", urlPatterns = {"/ServletVendas"})
public class ServletVendas extends HttpServlet {

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
            DAOVendas daoVendas = new DAOVendas();
            
            String date1 = request.getParameter("data1");
            String date2 = request.getParameter("data2");
            String submitType = request.getParameter("enviar");
            Date dt1 = null;
            Date dt2 = null;
        try {
            dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
            dt2 = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
        } catch (ParseException ex) {
            Logger.getLogger(ServletVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(submitType.equals("Buscar")){
            List<Vendas> desorga = daoVendas.listarVendas(dt1, dt2);
            List<Vendas> vendas = new ArrayList<>(); 
            
            for(Vendas vd : desorga){
                int i = 0;
                if(vendas.isEmpty()){
                    vendas.add(vd);
                }else{
                    for(Vendas v : vendas){
                        if(v.getIdIngrediente() == vd.getIdIngrediente()){
                            int sqtd = v.getQuantidade() + vd.getQuantidade();
                            v.setQuantidade(sqtd);
                            break;
                        }else{
                            i++;
                        }
                    }
                    if(vendas.size() <= i){
                        vendas.add(vd);
                    }
                }
            }
            
            
            
            request.getSession().setAttribute("vendas", vendas);
            request.getRequestDispatcher("/home/vendas.jsp").forward(request, response);
            }else if(submitType.equals("Limpar")){
                request.getSession().setAttribute("vendas", null);
                request.getRequestDispatcher("/home/vendas.jsp").forward(request, response);
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
