<%--
  Created by IntelliJ IDEA.
  User: Mak
  Date: 11/13/2023
  Time: 9:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        body {
            padding: 5%;
        }
    </style>
</head>
<body>
<h1 class="text-center">Student List</h1>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Student ID</th>
        <th scope="col">Name</th>
        <th scope="col">Birthday</th>
        <th scope="col">Class</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${studentList}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.birthday}</td>
            <td>${student.className}</td>
            <td>
                <a href="student?action=edit&id=${student.id}" class="btn btn-success">Edit</a>
                <a href="student?action=delete&id=${student.id}" class="btn btn-danger">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="student?action=add" class="btn btn-primary">Add</a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>
</html>
