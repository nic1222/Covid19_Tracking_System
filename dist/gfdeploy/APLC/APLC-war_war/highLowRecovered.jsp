<%-- 
    Document   : highLowRecovered
    Created on : Dec 5, 2021, 2:34:51 AM
    Author     : user
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.HighestLowestCases"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="banner.jsp"/>
        <br>
        <div class="container p-5">
            <div class="row">
                <h1>Highest & Lowest Recovered Case</h1>
                <br>
                <div class="row">
                    <div class="col-md-4">
                        <form class="d-flex" action="SearchCountryHighLowRecovered" method="POST">
                            <input class="form-control me-2" type="search" name="country" placeholder="Search for Country" aria-label="Search">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>
                    </div>
                    <div class="col-md-8"></div>
                </div>
                <table class="table table-striped" id="table">
                    <thead>
                        <tr>
                            <th scope="col">Country</th>
                            <th scope="col">State</th>
                            <th scope="col">Highest Recovered Case</th>
                            <th scope="col">Lowest Recovered Case</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<HighestLowestCases> caseList = (ArrayList<HighestLowestCases>) request.getAttribute("caseList");
                            for (int i = 0; i < caseList.size(); i++) {
                        %>
                        <tr>  
                            <td><%=caseList.get(i).getLocation().getCountry()%></td>
                            <td><%=caseList.get(i).getLocation().getState()%></td>
                            <td><%=caseList.get(i).getHighestCase()%></td>
                            <td><%=caseList.get(i).getLowestCase()%></td>
                        </tr>
                        <%};%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
