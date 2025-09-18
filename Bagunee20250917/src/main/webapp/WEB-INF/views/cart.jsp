<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <!-- ✅ Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-success">🛒 Shopping Cart</h2>
        <a href="/" class="btn btn-outline-secondary">← Back to Products</a>
    </div>

    <c:choose>
        <c:when test="${not empty cartItems}">
            <div class="table-responsive">
                <table class="table table-bordered table-hover text-center align-middle">
                    <thead class="table-success">
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="grandTotal" value="0" />
                    <c:forEach var="item" items="${cartItems}">
                        <c:set var="itemTotal" value="${item.quantity * item.product.price}" />
                        <c:set var="grandTotal" value="${grandTotal + itemTotal}" />
                        <tr>
                            <td>${item.product.name}</td>
                            <td>₩${item.product.price}</td>
                            <td>${item.quantity}</td>
                            <td>₩${itemTotal}</td>
                            <td>
                                <form action="/cart/delete" method="post">
                                    <input type="hidden" name="cartItemId" value="${item.id}" />
                                    <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr class="table-light fw-bold">
                        <td colspan="3">Total</td>
                        <td colspan="2">₩${grandTotal}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning text-center" role="alert">
                Your cart is empty. 🛒
            </div>
        </c:otherwise>
    </c:choose>
</div>

<!-- ✅ Bootstrap JS (선택사항) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
