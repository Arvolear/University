<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Sign Up</title>
    </head>
    <body>
        <div>
            <form action="${pageContext.request.contextPath}/api/signup" method="post">
                <table border="0">
                    <tr>
                        <td>Nick:</td>
                        <td><input type="text" name="nickname"><br/></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="text" name="email"><br/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password"><br/></td>
                    </tr>
                    <tr>
                        <td colspan="2" valign="center">
                            <button type="submit">Sign up</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div>
            <form action="${pageContext.request.contextPath}/login">
                <button type="submit">Login</button>
            </form>
        </div>
    </body>
</html>