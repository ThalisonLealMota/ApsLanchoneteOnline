/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DAOPessoa;
import entidade.Pessoa;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletLoginPessoa", urlPatterns = {"/ServletLoginPessoa"})
public class ServletLoginPessoa extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.security.NoSuchAlgorithmException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServletLoginPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        DAOPessoa daoPessoa = new DAOPessoa();
        Pessoa pessoa = new  Pessoa();
        
        String nome = request.getParameter("nome");
        String senha = null;
        try {
            senha = pessoa.gerarMD5(request.getParameter("senha"));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServletLoginPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        String submitType = request.getParameter("enviar");   

        List<Pessoa> pessoas = daoPessoa.login(nome, senha);
        
        if(!pessoas.isEmpty()){
            pessoa.setId(pessoas.get(0).getId());
            pessoa.setUsername(pessoas.get(0).getUsername());
            pessoa.setSenha(pessoas.get(0).getSenha());
            pessoa.setNvAcesso(pessoas.get(0).getNvAcesso());
        }
        
        if(submitType.equals("Login") && nome.equals(pessoa.getUsername()) && senha.equals(pessoa.getSenha())){
            request.getSession().invalidate();
            request.getSession().setAttribute("CURRENT_USER", pessoa.getUsername());
            request.getSession().setAttribute("CURRENT_ID", pessoa.getId());
            request.getSession().setAttribute("CURRENT_NVA", pessoa.getNvAcesso());
            request.getRequestDispatcher("/home/welcome.jsp").forward(request, response);
        }else if(submitType.equals("Cadastro")){
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        }else if(nome.equals("") || senha.equals("d41d8cd98f00b204e9800998ecf8427e")){
            request.setAttribute("message", "Digite seu username e senha!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else{
            request.setAttribute("message","Login ou senha incorreto(s)");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServletLoginPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
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
