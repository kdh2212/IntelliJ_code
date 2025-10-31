let reviewModal;
let editingReviewId = null; // 수정 모드 시 ID 저장

document.addEventListener('DOMContentLoaded', () => {
    reviewModal = new bootstrap.Modal(document.getElementById('reviewModal'));

    loadReviews(productId);

    // 🔹 검색 버튼
    document.getElementById('searchBtn').addEventListener('click', () => {
        const inputVal = document.getElementById('productIdInput').value.trim();
        if (!inputVal) {
            alert('상품 ID를 입력하세요.');
            return;
        }
        productId = inputVal;
        document.getElementById('currentProductId').textContent = productId;
        loadReviews(productId);
    });

    // 🔹 전체보기 버튼
    document.getElementById('resetBtn').addEventListener('click', () => {
        productId = null;
        document.getElementById('productIdInput').value = '';
        document.getElementById('currentProductId').textContent = '전체';
        loadReviews();
    });

    // ✅ 리뷰 추가 버튼 클릭 → 모달 열기
    document.getElementById('openReviewModalBtn').addEventListener('click', () => {
        editingReviewId = null; // 새 리뷰 추가 모드
        document.getElementById('reviewForm').reset();
        document.getElementById('reviewModalTitle').textContent = '리뷰 추가';
        document.getElementById('submitReviewBtn').textContent = '등록';
        document.getElementById('reviewProductIdInput').value = productId || '';
        reviewModal.show();
    });

    // ✅ 리뷰 등록 or 수정 버튼 클릭
    document.getElementById('submitReviewBtn').addEventListener('click', async () => {
        const author = document.getElementById('authorInput').value.trim();
        const content = document.getElementById('contentInput').value.trim();
        const rating = document.getElementById('ratingInput').value;
        const pid = document.getElementById('reviewProductIdInput').value;

        if (!author || !content || !rating || !pid) {
            alert('모든 항목을 입력해주세요.');
            return;
        }

        const reviewData = {
            author,
            content,
            rating: parseInt(rating),
            productId: parseInt(pid)
        };

        try {
            let res;
            if (editingReviewId) {
                // 수정 모드
                res = await fetch(`/api/reviews/${editingReviewId}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(reviewData)
                });
            } else {
                // 추가 모드
                res = await fetch('/api/reviews', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(reviewData)
                });
            }

            if (!res.ok) throw new Error('리뷰 저장 실패');
            alert(editingReviewId ? '리뷰가 수정되었습니다.' : '리뷰가 등록되었습니다.');
            reviewModal.hide();
            console.log("pid=",pid);
            loadReviews(pid);
        } catch (err) {
            console.error(err);
            alert('리뷰 저장 중 오류가 발생했습니다.');
        }
    });
});

// ✅ 리뷰 목록 로드
async function loadReviews(productId) {
    try {

        if (productId == undefined) {
            document.getElementById("currentProductId").innerHTML = '전체';
        }else{
            document.getElementById("currentProductId").innerHTML = productId;
        }


        let url = '/api/reviews';
        if (productId) url = `/api/reviews?productId=${productId}`;

        const response = await fetch(url);
        if (!response.ok) throw new Error('리뷰 불러오기 실패');
        const reviews = await response.json();

        const tbody = document.getElementById('reviewTableBody');
        tbody.innerHTML = '';

        if (reviews.length === 0) {
            tbody.innerHTML = `<tr><td colspan="6" class="text-center text-muted">등록된 리뷰가 없습니다.</td></tr>`;
            return;
        }

        reviews.forEach(r => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${r.id}</td>
                <td>${r.author}</td>
                <td>${r.content}</td>
                <td>${r.rating} / 5</td>
                <td>${r.createdAt}</td>
                <td>
                    <button class="btn btn-sm btn-warning me-1" onclick="editReview(${r.id})">수정</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteReview(${r.id})">삭제</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (err) {
        console.error(err);
        alert('리뷰를 불러오는 중 오류가 발생했습니다.');
    }
}

// ✅ 리뷰 수정 기능
async function editReview(id) {
    try {
        const res = await fetch(`/api/reviews/${id}`);
        if (!res.ok) throw new Error('리뷰 정보를 불러올 수 없습니다.');
        const review = await res.json();

        editingReviewId = review.id;
        document.getElementById('reviewModalTitle').textContent = '리뷰 수정';
        document.getElementById('submitReviewBtn').textContent = '수정';
        document.getElementById('authorInput').value = review.author;
        document.getElementById('contentInput').value = review.content;
        document.getElementById('ratingInput').value = review.rating;
        document.getElementById('reviewProductIdInput').value = review.productId;

        reviewModal.show();
    } catch (err) {
        console.error(err);
        alert('리뷰 정보를 불러오는 중 오류가 발생했습니다.');
    }
}

// ✅ 리뷰 삭제 기능
async function deleteReview(id) {
    if (!confirm('정말 삭제하시겠습니까?')) return;
    try {
        const res = await fetch(`/api/reviews/${id}`, { method: 'DELETE' });
        if (!res.ok) throw new Error('리뷰 삭제 실패');
        alert('리뷰가 삭제되었습니다.');
        loadReviews(productId);
    } catch (err) {
        console.error(err);
        alert('리뷰 삭제 중 오류가 발생했습니다.');
    }
}
