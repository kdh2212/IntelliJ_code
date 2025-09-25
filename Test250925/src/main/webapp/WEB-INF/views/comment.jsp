<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>댓글 작성</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
  <h2>댓글 작성</h2>
  <p>영화 리뷰에 대한 의견을 남겨주세요.</p>

  <!-- 댓글 작성 폼 -->
  <form method="post" action="/postmovie/${postMovie.id}/comments">
    <div class="card p-4 shadow-sm">
      <div class="mb-3">
        <label for="writer" class="form-label">작성자</label>
        <input type="text" name="writer" id="writer" class="form-control" placeholder="작성자 이름" required>
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">내용</label>
        <textarea name="content" id="content" class="form-control" rows="4" placeholder="댓글을 작성해주세요." required></textarea>
      </div>
      <button type="submit" class="btn btn-primary w-100 mt-3">댓글 작성</button>
    </div>
  </form>

  <!-- 추가 스타일링을 위한 마진 및 여백 -->
  <div class="mt-4">
    <a href="/postmovie/${postMovie.id}" class="btn btn-secondary w-100">돌아가기</a>
  </div>
</div>

</body>
</html>
