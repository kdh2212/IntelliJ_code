<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>할 일 추가</title>
  <style>
    body {
      font-family: "Segoe UI", Arial, sans-serif;
      background: #f4f6f9;
      margin: 0;
      padding: 0;
    }
    .container {
      width: 500px;
      margin: 80px auto;
      background: #fff;
      border-radius: 12px;
      box-shadow: 0 4px 12px rgba(0,0,0,0.1);
      padding: 30px 40px;
    }
    h2 {
      text-align: center;
      color: #333;
      margin-bottom: 25px;
    }
    form {
      display: flex;
      flex-direction: column;
    }
    input[type="text"] {
      padding: 10px;
      font-size: 16px;
      margin-bottom: 20px;
      border: 1px solid #ccc;
      border-radius: 6px;
      outline: none;
      transition: border-color 0.2s;
    }
    input[type="text"]:focus {
      border-color: #4CAF50;
    }
    input[type="submit"] {
      padding: 12px;
      background: #4CAF50;
      color: #fff;
      border: none;
      border-radius: 6px;
      font-size: 16px;
      cursor: pointer;
      transition: background 0.3s;
    }
    input[type="submit"]:hover {
      background: #45a049;
    }
    a.back-btn {
      display: inline-block;
      margin-top: 15px;
      color: #007bff;
      text-decoration: none;
      font-size: 14px;
    }
    a.back-btn:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>➕ 할 일 추가</h2>
  <form action="add" method="post">
    <input type="text" name="title" required placeholder="새 할 일을 입력하세요"/>
    <input type="submit" value="추가"/>
  </form>
  <a href="javascript:history.back()" class="back-btn">← 뒤로</a>
</div>
</body>
</html>