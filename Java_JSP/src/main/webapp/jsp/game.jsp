<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href='https://fonts.googleapis.com/css?family=Aldrich' rel='stylesheet'>
    <link href="https://fonts.googleapis.com/css2?family=Nova+Mono&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/global_styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/game_styles.css">

    <title>Game</title>
</head>

<body>
    <script>
        const xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 400) {
                    window.location.replace("${pageContext.request.contextPath}/login");
                } else if (xmlHttp.status == 200) {
                    document.getElementById("score").innerHTML = xmlHttp.responseText;
                }
            }
        }

        xmlHttp.open("GET", "${pageContext.request.contextPath}/api/game", true);
        xmlHttp.send(null);

        function doClick() {
            const xmlHttp = new XMLHttpRequest();
            xmlHttp.onreadystatechange = function() {
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    document.getElementById("score").innerHTML = xmlHttp.responseText;
                }
            }

            xmlHttp.open("POST", "${pageContext.request.contextPath}/api/game", true);
            xmlHttp.send(null);
        }

        function logout() {
            window.location.replace("${pageContext.request.contextPath}/api/logout");
        }
    </script>

    <div class="background">
        <p class="score_me" id="score"></p>
    </div>
    <div class="content">
        <div class="logout_div">
            <button class="logout_me" onclick="logout()">Logout</button>
        </div>
        <div class="click_div">
            <button class="click_me" onclick="doClick()">Click me hard</button>
        </div>
    </div>
</body>
</html>