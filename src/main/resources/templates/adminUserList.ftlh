<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ChatPog/Admin</title>
</head>
<body>
<h1><a href="/">Start page</a></h1> <h2><a href="/chat">Чаты</a></h2><h2><a href="/admin">Админская</a></h2><hr>
<h3></h3>
<h3><a></a></h3>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Delete</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.id}</th>
            <th><a href="/admin/user/${user.id}">${user.name}</a></th>
            <th>
                <form action="/admin/user/delete/${user.id}" method="post">
                <input type="submit" value="Delete">
                </form>
            <th>
        </tr>
    </#list>
</table>
</body>
</html>

