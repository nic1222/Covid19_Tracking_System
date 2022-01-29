<%-- 
    Document   : allTotalCases
    Created on : Dec 4, 2021, 6:27:13 PM
    Author     : user
--%>

<%@page import="model.TotalCases"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Total Cases Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">   

    </head>
    <body>
        <jsp:include page="banner.jsp"/>
        <br>
        <div class="container p-5">
            <div class="row">
                <h1>Total Covid-19 Cases</h1>
                <br>
                <div class="row">
                    <div class="col-md-4">
                        <form class="d-flex" action="SearchCountryTotalCases" method="POST">
                            <input class="form-control me-2" type="search" name="country" placeholder="Search for Country" aria-label="Search">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>
                    </div>
                    <div class="col-md-8"></div>
                </div>
                <br>
                <table class="table table-striped" id="table">
                    <thead>
                        <tr>
                            <th scope="col">Country</th>
                            <th scope="col">State</th>
                            <th scope="col">Total Confirmed Cases</th>
                            <th scope="col">Total Death Cases</th>
                            <th scope="col">Total Recovered Cases</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<TotalCases> allTotalCases = (List<TotalCases>) request.getAttribute("allTotalCases");
                            System.out.println(allTotalCases);
                            for (TotalCases cases : allTotalCases) {
                                pageContext.setAttribute("c", cases.getTotalRecoveredCases());
                        %>
                        <tr> 
                            <td><%out.println(cases.getLocation().getCountry());%></td>
                            <td><%out.println(cases.getLocation().getState());%></td>
                            <td><%out.println(cases.getTotalConfirmedCases().getCases());%></td>
                            <td><%out.println(cases.getTotalDeathCases().getCases());%></td>
                            <td>
                                <c:if test="${not empty c}">
                                    <%out.println(cases.getTotalRecoveredCases().getCases());%>
                                </c:if>
                                <c:if test="${empty c}">
                                    <%out.println("-");%>
                                </c:if>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
