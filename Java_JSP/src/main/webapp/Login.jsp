<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href='https://fonts.googleapis.com/css?family=Aldrich' rel='stylesheet'>
    <link rel="stylesheet" href="./global_styles.css">
    <link rel="stylesheet" href="./login_styles.css">

    <title>Login</title>
</head>

<body>
    <div class="login_header">
        <span>Login page</span>
    </div>

    <div class="login_card">
        <div class="space"></div>
        <div class="login_question">
            <div class="login_title">
                <span>Login</span>
            </div>
            <form autocomplete="off" action="${pageContext.request.contextPath}/api/login" method="post">
                <br>
                <label for="login_field">Your login:</label>
                <input class="green_text" type="text" name="email">
                <label for="password_field">Your password:</label>
                <input class="green_text" type="password" name="password">
                <div class="space"></div>
                <input class="white_submit" type="submit" value="login" name="login">
            </form>
        </div>
    </div>
</body>
</html>