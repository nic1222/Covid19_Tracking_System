<%-- 
    Document   : weeklyConfirmedCases
    Created on : Dec 4, 2021, 5:14:05 PM
    Author     : user
--%>

<%@page import="model.ByPeriodCases"%>
<%@page import="java.util.Map"%>
<%@page import="java.time.LocalDate"%>
<%@page import="model.ByPeriodCases"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <h1>Weekly Confirmed Covid-19 Cases</h1>
                <br>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <form class="d-flex" action="SearchCountryWeekly" method="POST">
                        <input class="form-control me-2" type="search" name="country" placeholder="Search for Country" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
                <div class="col-md-8"></div>
            </div>
        </div>
        <table class="table table-striped" id="table">
            <thead>
                <tr>
                    <th scope="col">Country</th>
                    <th scope="col">State</th>
                        <%
                            List<ByPeriodCases> weeklyList = (List<ByPeriodCases>) request.getAttribute("weeklyList");
                            List<Map<LocalDate, Integer>> caseMapList = (List<Map<LocalDate, Integer>>) request.getAttribute("caseMapList");
                            Map<LocalDate, Integer> caseMap = caseMapList.get(0);
                            pageContext.setAttribute("caseMap", caseMap);
                        %>
                        <c:forEach var="row" items="${caseMap}">
                        <th scope="col"><c:out value="${row.key}"/></th>
                        </c:forEach>
                </tr>
            </thead>
            <tbody>
                <%
                    for (ByPeriodCases week : weeklyList) {
                %>
                <tr> 
                    <td><%=week.getLocation().getCountry()%></td>
                    <td><%=week.getLocation().getState()%></td>
                    <%
                        pageContext.setAttribute("sumCaseList", week.getSumOfCasesByPeriod());
                    %>
                    <c:forEach var="row" items="${sumCaseList}">
                        <td scope="col"><c:out value="${row.value}"/></td>
                    </c:forEach>
                </tr>
                <%};%>
            </tbody>
        </table>
    </body>
</html>
