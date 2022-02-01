<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href='https://fonts.googleapis.com/css?family=Aldrich' rel='stylesheet'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/global_styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/login_styles.css">

    <title>Login</title>
</head>

<body>
    <script>
        const xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                window.location.replace("${pageContext.request.contextPath}/game");
            }
        }

        xmlHttp.open("GET", "${pageContext.request.contextPath}/api/login", true);
        xmlHttp.send(null);
    </script>

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
                <label for="login_field">Your email:</label>
                <input class="green_text" type="text" name="email">
                <label for="password_field">Your password:</label>
                <input class="green_text" type="password" name="password">
                <div class="space"></div>
                <input class="pink_submit" type="submit" value="login" name="login">
            </form>
            <form autocomplete="off" action="${pageContext.request.contextPath}/signup">
                <input class="pale_submit" type="submit" value="Don't have an account?" name="signup">
            </form>
        </div>
    </div>
</body>
</html>