<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Customer Manager</title>
</head>
    <body>
        <div align="center">
            <h2>Customer Manager</h2>
            <form method="get" action="search">
                <input type="text" name="keyword" />
                <input type="submit" value="Search" />
            </form>
            <h3><a href="/reg">New User</a></h3>
            <table border="1" cellpadding="5">
                <tr>
                    <th>Name</th>
                    <th>E-mail</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
                <c:forEach items="${listUser}" var="user">
                    <tr>
                        <td>${user.login}</td>
                        <td>${user.email}</td>
                        <td>${user.user_role}</td>
                        <td>
                            <a href="/edit?id=${user.login}">Edit</a>

                            <a href="/delete?id=${user.login}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br>
        <div align="center">
            <h2>Game Manager</h2>
            <form method="get" action="search">
                <input type="text" name="keyword" />
                <input type="submit" value="Search" />
            </form>
            <h3><a href="/upload">Upload</a></h3>
            <table border="1" cellpadding="5">
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Max Players</th>
                    <th>Min Players</th>
                    <th>Year Published</th>
                    <th>Description</th>
                </tr>

                <%--<c:forEach items="${gameList}" var="game">
                    <tr>
                        <td>${game.name}</td>
                        <td>${game.price}</td>
                        <td>${game.image_url}</td>
                        <td>${game.max_players}</td>
                        <td>${game.min_players}</td>
                        <td>${game.year_published}</td>
                        <td>${game.description_preview}</td>
                        <td>
                            <a href="/getById/id=${game.idGame}">Edit</a>
                            <a href="/delete/id=${game.idGame}">Delete</a>
                            <a href="/gameCategory/id=${game.idGame}">Category</a>
                            <a href="/gameMechanic/id=${game.idGame}">Mechanics</a>
                            <a href="/categoryList">categoryList</a>
                        </td>
                    </tr>
                </c:forEach>--%>
            </table>
        </div>
    </body>
</html>