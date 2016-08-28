<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		
		<h2>
			
			
			<c:forEach var="name" items="${nameList}">
						<tr>
			        		<td>${name}</td>  
			      			
			  			</tr>
			</c:forEach> 
		</h2>
	</center>
</body>
</html>