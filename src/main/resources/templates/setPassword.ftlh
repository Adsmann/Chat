<!DOCTYPE html>
<html>
<head>
    <title>ChatPog</title>
</head>
<body>
<h1>ChatPog</h1><hr>
<h4>Авторизация</h4>
<form action="/usersetpassword/{id}" method="post">
    Password: <input type="password" name="password"><br><br>
    <#--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
    <input type="submit" value="Изменить"/>
</form>

</body>
</html>