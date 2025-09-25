<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>영화 목록</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-5">

<h1 class="mb-4">🎬 영화 목록</h1>

<div class="mb-3">
    <a href="/postmovie/new" class="btn btn-primary">영화 저장</a>
</div>

<table class="table table-hover">
    <thead class="table-dark">
    <tr>
        <th>ID</th>
        <th>제목</th>
        <th>감독</th>
        <th>장르</th>
        <th>작성자</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="postmovie" items="${postmovie}">
        <tr>
            <td>${postmovie.id}</td>
            <td><a href="/postmovie/${postmovie.id}">${postmovie.title}</a></td>
            <td>${postmovie.director}</td>
            <td>${postmovie.genre}</td>
            <td>${postmovie.writer}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>