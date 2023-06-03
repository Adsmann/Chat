<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ChatPog</title>
</head>
<body>
    <h1><a href="/">Start page</a></h1> <h2><a href="/chat">Чаты</a></h2><hr>

    <#list listchat as chatOne>
        <#if chatOne.idUserOne == mainUser.id || chatOne.user.id == mainUser.id>
            <h3><a href="/chat/${chatOne.id}">${chatOne.name}</a></h3>
            <form action="/chat/delete/${chatOne.id}" method="post">

                <input type="submit" value="Delete">
            </form>


        <#else>
        </#if>
    </#list>
<h3>New Chat</h3>
<form action="/chat/create" method="post">
    Name Chat: <input type="text" name="name">
    Id User: <input type="number" name="idUserOne">

    <input type="submit" value="Create">
</form>
</body>
</html>