<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <title>Accident</title>
</head>
<body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

<%--<div style="margin-left:10px">--%>
<%--    Hello : ${user}--%>
<%--</div>--%>

<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <h3>
                    Hello job4j, welcome to Accident
                </h3>
            </li>
        </ul>
    </div>
</div>

<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/create'/>"><h3>Добавить инцидент</h3></a>
            </li>
        </ul>
    </div>
</div>

<div class="table table-sm table-bordered" style="margin-left:150px; width: 1000px; margin-top: 15px">
    <table class="table" id = "table" >
        <thead style="font-size:14px;">
        <tr style="background-color: rgb(0,150,350)">
            <th scope="col" style="text-align: center;">ID</th>
            <th scope="col" style="text-align: center;">Name</th>
            <th scope="col" style="text-align: center;">Text</th>
            <th scope="col" style="text-align: center;">Address</th>
            <th scope="col" style="text-align: center;">Status</th>
        </tr>
        </thead>

        <tbody>

<c:forEach items="${allAccident}" var="accidents">
    <tr>
        <td style="text-align: center;">
            <a href='<c:url value="/edit?id=${accidents.id}"/>'>
                <i ><c:out value="${accidents.id}"/></i>
            </a>
<%--            <c:out value="${accidents.id}"/>--%>
        </td>
        <td style="text-align: center;">
            <c:out value="${accidents.name}"/>
        </td>
        <td style="text-align: center;">
            <c:out value="${accidents.text}"/>
        </td>
        <td style="text-align: center;">
            <c:out value="${accidents.address}"/>
        </td>
        <td style="text-align: center;">
            <c:out value="${accidents.status}"/>
        </td>
    </tr>

</c:forEach>
        </tbody>

    </table>
</div>
</body>
</html>
