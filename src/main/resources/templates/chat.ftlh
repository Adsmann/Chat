<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ChatPog</title>
</head>
<body>
<h1><a href="/">Start page</a></h1> <h2><a href="/chat">Чаты</a></h2><hr>

<#list listmess as messOne>
    <div>
    <h3>${messOne.user.username}</h3>
    <h4>${messOne.contents}</h4>
    <#if chat.idUserOne == mainUser.id || chat.user.id == mainUser.id>
    <form action="/chat/${chat.id}/delete/${messOne.id}" method="post">
        <input type="submit" value="Delete">
    </form>
    </#if>
    </div>
<#--    <a href="/chat/${id}/delete/${messOne.id}">X</a>-->
</#list>
<#if chat.idUserOne == mainUser.id || chat.user.id == mainUser.id>
<h3>New Mess</h3>
<form action="/chat/${id}/create" method="post">
<#--    User: <input type="text" name="name">-->
    Text: <input type="text" name="contents">
    <input type="hidden" name="id" value="${id+99}">
    <input type="hidden" name="chat" value="${id}">
    <input type="hidden" name="name" value="${id}">
    <input type="submit" value="Create">
</form>
</#if>

</body>
</html>