<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ChatPog</title>
</head>
<body>
<h1><a href="/">Start page</a></h1> <h2><a href="/chat">Чаты</a></h2><h2><a href="/admin">Админская</a></h2><hr>

<#list listchat as chatOne>
        <h3><a href="/chat/${chatOne.id}">${chatOne.name}</a></h3>
</#list>
</body>
</html>