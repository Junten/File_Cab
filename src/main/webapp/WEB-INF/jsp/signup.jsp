<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 
           Access the bootstrap CSS like this,Spring boot will handle the resource mapping automcatically 
        -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
        <title>Sign Up New Account</title>

    </head>

    <body>
   		<nav class="navbar navbar-default">
            <div class="container-fluid">
            <a class="navbar-brand " href="#" style="padding-right: 500px">
                <span class="p-3 mb-5 text-dark">File Cab</span>
            </a>
           
        </nav>
        <div class="col-xs-6 vcenter center-block">
        <form:form method="POST" modelAttribute="userForm">   
            <h2 class="form-signin-heading">Create your account</h2>
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control" placeholder="Username" autofocus="true"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="passwordConfirm" class="form-control" placeholder="Confirm your password"></form:input>
                    <form:errors path="passwordConfirm"></form:errors>
                </div>
            </spring:bind>
			
			<spring:bind path="firstname">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="firstname" class="form-control" placeholder="firstname"></form:input>
                    <form:errors path="firstname"></form:errors>
                </div>
            </spring:bind>
            
            <spring:bind path="lastname">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="lastname" class="form-control" placeholder="lastname"></form:input>
                    <form:errors path="lastname"></form:errors>
                </div>
            </spring:bind>
			
            <button class="btn btn-lg btn-primary" type="submit">Submit</button>
        </form:form>
        </div>
    </body>
</html>
