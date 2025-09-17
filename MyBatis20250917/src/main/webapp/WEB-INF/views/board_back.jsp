<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .completed {
        text-decoration: line-through;
        color: gray;
    }
</style>
<body>
<form method = "post" action = "/board/save">
    <input type = "hidden" name = "id" value="${form.id}">
    제목:<input type="text" name="title" value="${form.title}"><br/>

    내용:<textarea name="content">${form.content}</textarea>
    <button type="submit">등록</button>
</form>
<br/>
    <h2>board화면입니다.</h2>
    <table>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성일</th>
            <th>액션</th>
        </tr>
        <c:forEach var="item" items="${list}">
            <tr>
                <td>${item.id}</td>
                <td>${item.title}</td>
                <td>${item.content}</td>
                <td>${item.createdAt}</td>

                <td>
                    <a href="/board?editId=${item.id}">수정</a>
                    <a href="/board/delete/${item.id}" onclick="return confirm('정말삭제할까요?')">삭제</a>

                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
