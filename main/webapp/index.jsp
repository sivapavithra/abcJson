<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="taskOneServlet" method="post">
<input type="submit" value="task1"></form>

<form action="taskThreeServlet" method="post">
<input type="submit" value="task3">
Name: <input type = "text" name = "name">
</form>

<form action="taskFourServlet" method="post">
<input type="submit" value="task4">
Name:<input type = "text" name = "name">
</form>

<form action="taskFiveServlet" method="post">
<input type="submit" value="task5">
Name:<input type = "text" name = "name">
</form>

<form action="taskTwoServlet" method="post">
<input type="submit" value="task2">
Name:<input type = "text" name = "name">
Operation:<input type = "text" name = "operation">
Data:<input type = "text" name = "data">
</form>

</body>
</html>