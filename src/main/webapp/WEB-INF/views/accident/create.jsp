<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <title>Accident Create</title>
</head>
<body>

<script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <h3>
                    Форма добавления инцидента, заполните все поля
                </h3>
            </li>
        </ul>
    </div>
</div>

<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/'/>"><h3>Главная</h3></a>
            </li>
        </ul>
    </div>
</div>

<form class="card-body" action="<c:url value='/save'/>" method='POST'>
    <div class="form-group" style="margin-left:390px; width: 700px; margin-top: 15px">
        <label>Имя</label>
        <input type="text" class="form-control" name="name" id="name" placeholder="Введите Имя">
    </div>
    <div class="form-group" style="margin-left:390px; width: 700px; margin-top: 15px">
        <label>Тип нарушения</label>
<%--        <input type="text" class="form-control" name="types" id="types" placeholder="Выберите тип">--%>
        <div>
            <select name="type.id" id="type.id" placeholder="Выберите тип">
                <c:forEach var="type" items="${types}" >
                    <option value="${type.id}">${type.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group" style="margin-left:390px; width: 700px; margin-top: 15px">
        <label>Текст</label>
        <input type="text" class="form-control" name="text" id="text" placeholder="Введите суть нарушения">
    </div>
    <div class="form-group" style="margin-left:390px; width: 700px; margin-top: 15px">
        <label>Адрес</label>
        <input type="text" class="form-control" name="address" id="address" placeholder="Введите Адрес">
    </div>
    <button style="margin-left: 390px" type="submit" class="btn btn-primary" onclick="return validate() ">Сохранить</button>
</form>

<script>
    function validate() {
        let fields = [$("#name"), $("#text"), $("#address"), $("#type.id")];
        let result = true;
        let answer = '';
        for (let i = 0; i < fields.length; i++) {
            if (fields[i].val() === "") {
                answer += fields[i].attr("placeholder") + "\n";
                result = false;
            }
        }
        if (!result) {
            alert(answer);
        }
        return result;
    }
</script>

</body>
</html>
