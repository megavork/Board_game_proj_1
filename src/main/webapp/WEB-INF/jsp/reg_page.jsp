<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<style>
    <%@include file='../../css/style.css' %>
</style>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="../../css/style.cs">
        <title>Registration page</title>
    </head>
    <body>
        <form class="decor" method="post" action="/main" id="Name">
            <div class="form-left-decoration"></div>
            <div class="form-right-decoration"></div>
            <div class="circle"></div>
            <div class="form-inner">

                <h1><b>Registration</b></h1>

                <input type="text" name="Login" placeholder="Enter your login" required/>
                <input type="password" minlength="6" name="password_one" placeholder="Enter password" required/>
                <input type="password" minlength="6" name="password_two" placeholder="Enter password again" required/>

                <h2><b>Enter your e-mail:</b></h2>
                <input type="email" name="price" placeholder="Enter your e-mail" required/>
                <input type="reset" class="btn" value="Clear all"/>
                <input type="submit" class="btn" value="Send message"/>
            </div>
        </form>
    </body>
</html>