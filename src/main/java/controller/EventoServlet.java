/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EventSpeakers;
import org.bson.Document;

/**
 *
 * @author isabella
 */
@WebServlet(name = "EventoServlet", urlPatterns = {"/eventoServlet"})
public class EventoServlet extends HttpServlet {

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
        String pagina = "/index.jsp";
        request.setAttribute("eventos", DBConfig.getAbstractDAO().findAll());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
        dispatcher.forward(request, response);
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
        if (request.getParameterMap().containsKey("action")) {
            switch (request.getParameter("action")) {
                case "list":
                    request.setAttribute("page", "gerenciarEventos.jsp");
                    break;
                case "insert":
                    request.setAttribute("page", "inserirEvento.jsp");
                    break;
                case "delete":
                    DBConfig.getAbstractDAO().delete(new EventSpeakers(request.getParameter("id_evento")));
                    request.setAttribute("page", "gerenciarEventos.jsp");
                    break;
                case "update":
                    request.setAttribute("eventoUpdate", (EventSpeakers) DBConfig.getAbstractDAO().find(request.getParameter("id_evento")));
                    request.setAttribute("page", "inserirEvento.jsp");
                    break;
                default:
                    request.removeAttribute("page");
                    break;
            }
            request.setAttribute("menuAtivo", "menu_eventos");
        }

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
        Document doc = Document.parse(request.getParameter("eventoJson"));
        EventSpeakers e = null; // parse to obj
        
        if ( e.getId().equals("0") ) // novo evento
            DBConfig.getAbstractDAO().create(e);
        else                         // update evento existente
            DBConfig.getAbstractDAO().update(e);
            
        PrintWriter out = response.getWriter();
        out.print("{\"url\": \"index.jsp\"}");
        out.flush();
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
