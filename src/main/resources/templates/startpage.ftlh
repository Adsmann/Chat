<!DOCTYPE html>
<html>
<head>
    <title>ChatPog</title>
</head>
<body>
<h1>ChatPog</h1><hr>
<h3><a href="/login">Авторизация</a></h3>
<h3><a href="/registration">Зарегистрироваться</a></h3>
<h3><a href="/chat">Чаты</a></h3>
<#--<h3><a href="/setpassword">Смена пароля</a> </h3>-->
<#if user.isAdmin()>
    <a href="/admin">Панель администратора</a>
</#if>
</body>
</html>