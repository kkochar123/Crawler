<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>Gymnasiums : </h2>
	
	<c:forEach var="listValue" items="${item}">
		<li>${listValue}</li>
    </c:forEach>
</body>
</html>
