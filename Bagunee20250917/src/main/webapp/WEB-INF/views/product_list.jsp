<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <!-- ✅ Bootstrap CDN 추가 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-primary">🛍️ Product List</h2>
        <a href="/cart" class="btn btn-outline-primary">Go to Cart</a>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle text-center">
            <thead class="table-primary">
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${products}">
                <tr>
                    <td>${p.name}</td>
                    <td>₩${p.price}</td>
                    <td>
                        <form action="/add" method="post" class="d-flex justify-content-center align-items-center gap-2">
                            <input type="hidden" name="productId" value="${p.id}" />
                            <input type="number" name="quantity" value="1" min="1" class="form-control w-50" />
                            <button type="submit" class="btn btn-success btn-sm">Add to Cart</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- ✅ Bootstrap JS (선택사항) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
