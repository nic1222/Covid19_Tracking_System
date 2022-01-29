/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WritePrologFile;
import model.EachTotalCases;
import model.TotalCases;

/**
 *
 * @author user
 */
@WebServlet(name = "AllTotalCases", urlPatterns = {"/AllTotalCases"})
public class AllTotalCases extends HttpServlet {

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
        Calculation.init();
//        WritePrologFile c = new WritePrologFile();
//        c.convert();

        List<EachTotalCases> confirmedCaseList = Calculation.getConfirmedTotalCases(Calculation.getCsvConfirmedRecords());
        List<EachTotalCases> deathCaseList = Calculation.getDeathTotalCases(Calculation.getCsvDeathRecords());
        List<EachTotalCases> recoveredCaseList = Calculation.getRecoveredTotalCases(Calculation.getCsvRecoveredRecords());

        List<TotalCases> allTotalCases = Calculation.groupAllCases(confirmedCaseList, deathCaseList, recoveredCaseList);
        
        
        request.setAttribute("allTotalCases", allTotalCases);
        request.getRequestDispatcher("allTotalCases.jsp").include(request, response);

        try (PrintWriter out = response.getWriter()) {

        }
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
