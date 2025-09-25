<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>영화 리뷰 상세</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
  <style>
    .comment-container {
      max-height: 300px; /* 댓글 영역 최대 높이 설정 */
      overflow-y: auto;  /* 세로 스크롤바 추가 */
    }
  </style>
</head>
<body class="container mt-5">

<!-- 수정, 삭제, 목록 버튼을 상단 오른쪽에 배치 -->
<div class="d-flex justify-content-end mb-4">
  <!-- 수정 버튼 -->
  <a href="/postmovie/${postMovie.id}/edit" class="btn btn-warning" style="height: 38px; margin-right: 10px;">수정</a>

  <!-- 삭제 버튼 -->
  <form action="/postmovie/${postMovie.id}/delete" method="post" class="d-inline" style="margin-right: 10px;">
    <button type="submit" class="btn btn-danger" style="height: 38px;">삭제</button>
  </form>

  <!-- 목록 버튼 -->
  <a href="/postmovie" class="btn btn-secondary" style="height: 38px;">목록</a>
</div>

<h1 class="mb-4">🎬 제목: ${postMovie.title}</h1>
<p><strong>감독</strong></p>
<p class="border p-3">${postMovie.director}</p>
<p><strong>장르</strong></p>
<p class="border p-3">${postMovie.genre}</p>
<p><strong>작성자:</strong> ${postMovie.writer}</p>
<br/>
<p><strong>리뷰 글</strong></p>
<p class="border p-3">${postMovie.content}</p>

<hr/>

<!-- 댓글 목록 -->
<h4 class="mt-5">💬 댓글</h4>
<div class="comment-container mb-3">
  <c:forEach var="comment" items="${comments}">
    <div class="border rounded p-3 mb-2">
      <strong>${comment.writer}</strong>
      <small class="text-muted float-end">${comment.createdAt}</small>
      <p class="mb-0">${comment.content}</p>
    </div>
  </c:forEach>
</div>

<!-- 댓글 작성 버튼 -->
<div class="mt-3">
  <a href="/postmovie/${postMovie.id}/newcomment" class="btn btn-info">댓글 작성</a> <!-- 댓글 작성 폼으로 이동하는 버튼 -->
</div>

<!-- 추천 버튼 영역 -->
<div class="mt-4">
  <p><strong>이 리뷰는 어떻게 생각하시나요?</strong></p>

  <!-- 추천해요 버튼과 추천 수 -->
  <form action="/postmovie/${postMovie.id}/recommend/yes" method="post" class="d-inline">
    <button type="submit" class="btn btn-success">추천</button>
  </form>
  <span class="ml-2">${postMovie.recommendations} 명</span> <!-- 추천 수 표시 -->

  <!-- 추천 안해요 버튼과 비추천 수 -->
  <form action="/postmovie/${postMovie.id}/recommend/no" method="post" class="d-inline">
    <button type="submit" class="btn btn-danger">싫어요</button>
  </form>
  <span class="ml-2">${postMovie.rejections} 명</span> <!-- 비추천 수 표시 -->
</div>

<!-- 빈 공간을 추가 -->
<div style="height: 100px;"></div> <!-- 고정된 높이로 빈 공간을 추가 -->

</body>
</html>
