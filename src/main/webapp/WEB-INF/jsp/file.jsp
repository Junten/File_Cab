<!DOCTYPE html>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>File Cab</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
      
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
        <script src="<c:url value="/resources/js/getrequest.js" />"> </script>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
            	<a class="navbar-brand " href="#">
                	<span class="p-3 mb-5 text-dark">File Cab</span>
            	</a>
            	<form action="/" method="get">
                	<div class="nav navbar-nav navbar-right">
                    	<button type="Submit" class="btn btn-default" >
                        	<span class="glyphicon glyphicon-log-out"></span> 
                        	Log out
                    	</button>
                	</div>
            	</form>
            </div>
        </nav>
        <table class="table">
            <thead>
                <th scope="col">File Name</th>
                <th scope="col">File Description</th>
                <th scope="col">File Created Time</th>
                <th scope="col">File Updated Time</th>
                <th scope="col">Download</th>
                <th scope="col">Delete</th>
            </thead>
            <div id="listFiles">
            </div>
            <tbody>
            	<c:forEach var="listValue" items="${fileList}">
                <tr>
                    <th scope="row">
                        ${listValue.fileName}
                    </th>
                    <td>
                        ${listValue.description}
                    </td>
                    <td>
                    	${listValue.createDate}
                    </td>
                    <td>
                    	${listValue.updateDate}
 
                    </td>
                   
                    <td>
                        <a target="_blank" rel="canonical" href="http://daenrptbvbfth.cloudfront.net/${listValue.fileName}">
                            <button class="btn-info btn-sm" name="deleteFile" id="downloadFile" value="Download File" >
                                <span class="glyphicon glyphicon-download-alt btn" ></span>
                            </button>
                        </a>
                    </td>                           
                    <td>
                    	<form:form method="DELETE" action="/file/delete/${listValue.fileName}">     
                        <a target="_blank title="Delete">
                            <button class="btn-danger btn-sm" name="deleteFile" id="deleteFile" value="Delete File">
                                <span class="glyphicon glyphicon-trash btn"></span>
                            </button>
                        </a>
                        </form:form>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <form:form method="POST"  enctype="multipart/form-data" id="UploadForm" action="/file/upload">
            <div class="form-group">
                <table>
                    <tr>
                        <td>
                            <label class="control-label" for="upload">Upload Your Files:</label>
                        </td>
               
                        <td>
                            <input type="file" class="multipart/form-control" id="upload" name="uploadFile">
                        </td>
               
                        <td>
                            <input name="description" type="text" placeholder="Description" class="form-control" />
                        </td>
                
                        <td>
                            <button type="submit" class="btn btn-md btn-primary btn-block" id="btnSubmit" >Upload</button>
                        </td>
                    </tr>
                </table>
            </div>
        </form:form>
    </body>
</html>