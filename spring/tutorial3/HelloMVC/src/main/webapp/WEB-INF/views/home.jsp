<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>


<!-- Change 'action' to /hellomvc/cheers2 to test the person wrapper class. -->
<form action="/hellomvc/cheers" method="POST">
	
	Name: <input name="name" /><br />
	Age: <input name="age" /><br />
	<input type="submit"/> 
</form>

</body>
</html>
