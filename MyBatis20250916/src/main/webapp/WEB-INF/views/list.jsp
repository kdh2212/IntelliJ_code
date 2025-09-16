<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>ToDo List</title>
  <style>
    body {
      font-family: "Segoe UI", Arial, sans-serif;
      background: #f4f6f9;
      margin: 0;
      padding: 0;
    }
    .container {
      width: 600px;
      margin: 50px auto;
      background: #fff;
      border-radius: 12px;
      box-shadow: 0 4px 10px rgba(0,0,0,0.1);
      padding: 30px;
    }
    h2 {
      text-align: center;
      color: #333;
      margin-bottom: 20px;
    }
    a.add-btn {
      display: inline-block;
      margin-bottom: 20px;
      padding: 10px 16px;
      background: #4CAF50;
      color: #fff;
      text-decoration: none;
      border-radius: 6px;
      transition: background 0.3s;
    }
    a.add-btn:hover {
      background: #45a049;
    }
    ul {
      list-style: none;
      padding: 0;
    }
    li {
      display: flex;
      justify-content: space-between;
      align-items: center;
      background: #fafafa;
      margin-bottom: 10px;
      padding: 12px 16px;
      border-radius: 8px;
      transition: background 0.2s;
    }
    li:hover {
      background: #f0f0f0;
    }
    .todo-title {
      font-size: 16px;
      color: #333;
    }
    .completed {
      text-decoration: line-through;
      color: gray;
    }
    .status {
      font-size: 13px;
      color: #666;
      margin-left: 8px;
    }
    .actions a {
      margin-left: 8px;
      font-size: 13px;
      color: #007bff;
      text-decoration: none;
    }
    .actions a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>📋 할 일 목록</h2>
  <a href="add" class="add-btn">+ 할 일 추가</a>
  <ul>
    <c:forEach var="todo" items="${todos}">
      <li>
        <div>
          <a href="toggle/${todo.id}">
						<span class="todo-title ${todo.completed ? 'completed' : ''}">
                            ${todo.title}
                        </span>
          </a>
          <span class="status">[${todo.completed ? '완료됨' : '미완료'}]</span>
        </div>
        <div class="actions">
          <a href="update?id=${todo.id}">✏ 수정</a>
          <a href="delete/${todo.id}">🗑 삭제</a>
        </div>
      </li>
    </c:forEach>
  </ul>
</div>
</body>
</html>