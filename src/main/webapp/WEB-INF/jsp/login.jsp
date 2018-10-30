<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
    <head>
        <c:url value="/css/main.css" var="jstlCss" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
            <a class="navbar-brand " href="#" style="padding-right: 500px">
                <span class="p-3 mb-5 text-dark">File Cab</span>
            </a>
        </nav>
		<div class="container">
            <div class="col-xs-4 vcenter center-block">
                <h3>Login</h3>
                <a class="btn btn-block btn-social btn-facebook customBtn">
                  <span class="fa fa-facebook width42px"></span> Sign in with Facebook
                </a>
                
                <a class="btn btn-block btn-social btn-google customBtn">
                  <span class="fa fa-google width42px"></span> Sign in with Google
                </a>
        
                <form:form method="POST" action="/login">     
                    <table>
                        <tr>
                           <td><input name="username" type="text" placeholder="Username" class="form-control"/></td>
                        </tr>
                        <tr>
                            <td><input name='password' type='password' placeholder="Password" class="form-control" /></td>
                        </tr>       
                        <tr>
                            <td><input name="submit" type="submit" value="submit" /></td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </div>
    </body>
</html>
