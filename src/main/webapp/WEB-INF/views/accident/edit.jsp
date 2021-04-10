<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form class="card-body" action="<c:url value='/save'/>" method='POST'>
    <div class="form-group">
        <label>Имя</label>
        <input type="text" class="form-control" name="name" id="name" placeholder="Введите Имя">
    </div>
    <div class="form-group">
        <label>Текст</label>
        <input type="text" class="form-control" name="text" id="text" placeholder="Введите Текст">
    </div>
    <div class="form-group">
        <label>Адрес</label>
        <input type="text" class="form-control" name="address" id="address" placeholder="Введите Адрес">
    </div>
    <button type="submit" class="btn btn-primary" onclick="return validate()">Сохранить</button>
</form>

<script>
    function validate() {
        let fields = [$("#name"), $("#text"), $("#address")];
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