<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <title>Accident</title>
</head>
<body>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

<div style="margin-left:10px">
    Hello : ${user}
</div>

<div class="table table-sm table-bordered" >
    <table class="table" id = "table" style="margin-left:10px; margin-right: 500px">
        <thead style="font-size:14px;">
        <tr style="background-color: rgb(0,150,350)">
            <th scope="col">value</th>
        </tr>
        </thead>

        <tbody>

<c:forEach items="${list}" var="lists">
    <tr>
        <td >
            <c:out value="${lists}"/>
        </td>
    </tr>

</c:forEach>
        </tbody>

    </table>
</div>
</body>
</html>
