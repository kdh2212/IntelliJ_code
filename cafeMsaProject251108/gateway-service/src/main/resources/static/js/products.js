// ===== 전역 =====
let productModal;
let currentRole = null; // "ROLE_USER" / "ROLE_OWNER"
let allProducts = [];   // 전체 상품 목록 캐시
let currentCategoryFilter = 'ALL'; // 'ALL' | '음료' | '푸드'

document.addEventListener('DOMContentLoaded', async function () {
    productModal = new bootstrap.Modal(document.getElementById('productModal'));

    // 1) 현재 로그인 사용자 정보 조회 (테스트용 하드코딩)
    await loadCurrentUser();

    // 2) 역할에 따라 네비바 & 버튼 세팅
    renderNavBar();
    setupUIByRole();

    // 3) 기본 필터 버튼 상태 설정
    updateFilterButtons();

    // 4) 상품 목록 조회 + 렌더링
    await loadProducts();
});

/**
 * 현재 로그인 사용자 정보 가져오기 (테스트용 하드코딩)
 * 실제로는 /api/auth/me 등에서 가져와야 함
 */
async function loadCurrentUser() {
    // 🔧 실제 구현에서는 서버로부터 사용자/역할 정보를 조회하세요.
    currentRole = "ROLE_USER";            // 👤 사용자 화면 테스트
    localStorage.setItem('userId', '1');  // ✅ 테스트용 사용자ID 저장
}

/**
 * 역할에 따라 네비바 렌더링
 * 💡 수정: btn-primary 대신 btn-nav-custom 및 btn-nav-active 사용
 */
function renderNavBar() {
    const navMenu = document.getElementById('nav-menu');
    if (!navMenu) return;

    // 💡 새로운 클래스 정의
    const activeClass = 'btn btn-nav-active';
    const normalClass = 'btn btn-nav-custom';

    // 💡 URL에 따라 활성화 클래스를 동적으로 설정하는 헬퍼 함수
    const getClass = (path) => location.pathname.startsWith(path) ? activeClass : normalClass;


    if (currentRole === 'ROLE_OWNER') {
        // 👔 사장 네비바
        navMenu.innerHTML = `
            <a href="/products" class="${getClass('/products')} me-2">상품목록</a>
            <a href="/orders/purchase" class="${getClass('/orders/purchase')} me-2">발주</a>
            <a href="/orders/purchase/history" class="${getClass('/orders/purchase/history')} me-2">발주내역</a>
            <a href="/admin/users" class="${getClass('/admin/users')}">사용자 관리</a>
        `;
    } else if (currentRole === 'ROLE_USER') {
        // 👤 일반 사용자 네비바
        navMenu.innerHTML = `
            <a href="/products" class="${getClass('/products')} me-2">상품목록</a>
            <a href="/cart" class="${getClass('/cart')} me-2">장바구니</a>
            <a href="/orders" class="${getClass('/orders')} me-2">상품내역</a>
            <a href="/favorites" class="${getClass('/favorites')}">즐겨찾기</a>
        `;
    } else {
        // 비로그인 or 기타 역할
        navMenu.innerHTML = `
            <a href="/products" class="${getClass('/products')} me-2">상품목록</a>
        `;
    }
}

/**
 * 역할에 따라 UI 세팅 (상품 추가 버튼 / 헤더 텍스트 등)
 */
function setupUIByRole() {
    const addBtn = document.getElementById('btn-add-product');
    const actionHeader = document.getElementById('th-action');

    if (currentRole === 'ROLE_OWNER') {
        // 👔 사장: 상품 추가 버튼 보이기, 헤더 "작업"
        if (addBtn) addBtn.style.display = 'inline-block';
        if (actionHeader) actionHeader.textContent = '작업';
    } else if (currentRole === 'ROLE_USER') {
        // 👤 사용자: 상품 추가 버튼 숨기기, 헤더 "장바구니"
        if (addBtn) addBtn.style.display = 'none';
        if (actionHeader) actionHeader.textContent = '장바구니';
    } else {
        if (addBtn) addBtn.style.display = 'none';
        if (actionHeader) actionHeader.textContent = '';
    }
}

/**
 * 상품 목록 불러오기
 */
async function loadProducts() {
    try {
        const response = await fetch('/api/products');
        if (!response.ok) {
            const text = await response.text().catch(()=>'');
            throw new Error(`상품 목록 조회 실패 (${response.status}) ${text}`);
        }

        const products = await response.json();
        allProducts = products || [];
        renderProducts();
    } catch (error) {
        console.error('상품 목록을 불러오는데 실패했습니다:', error);
        alert('상품 목록을 불러오는데 실패했습니다.');
    }
}

/**
 * 현재 필터 상태에 맞게 상품 렌더링
 */
function renderProducts() {
    const tbody = document.getElementById('productTableBody');
    if (!tbody) return;
    tbody.innerHTML = '';

    // 안전한 숫자 포맷터
    const fmt = (n) => {
        const v = Number(n || 0);
        try { return v.toLocaleString(); } catch { return String(v); }
    };

    const filtered = allProducts.filter(product => {
        if (currentCategoryFilter === 'ALL') return true;
        return product.category === currentCategoryFilter;
    });

    filtered.forEach(product => {
        const tr = document.createElement('tr');

        const isSoldOut = !product.stock || product.stock <= 0;
        const soldOutText = isSoldOut ? '품절' : '판매중';

        let actionButtons = '';

        if (currentRole === 'ROLE_OWNER') {
            // 👔 사장: 수정/삭제
            actionButtons = `
                <button class="btn btn-sm btn-primary me-1" onclick="editProduct(${product.id})">수정</button>
                <button class="btn btn-sm btn-danger" onclick="deleteProduct(${product.id})">삭제</button>
            `;
        } else if (currentRole === 'ROLE_USER') {
            // 👤 사용자: 장바구니 (품절이면 비활성화)
            // 💡 여기 버튼은 HTML에서 정의한 btn-success 대신, 기존 디자인을 유지하거나 btn-coffee 스타일을 적용할 수 있습니다.
            // 여기서는 HTML에서 정의한 btn-success를 커피 스타일로 오버라이드했다고 가정하고 그대로 둡니다.
            if (!isSoldOut) {
                actionButtons = `
                    <button class="btn btn-sm btn-success" onclick="addToCart(${product.id})">장바구니 담기</button>
                `;
            } else {
                actionButtons = `
                    <button class="btn btn-sm btn-secondary" disabled>품절</button>
                `;
            }
        }

        tr.innerHTML = `
            <td>${product.id}</td>
            <td>
                <a href="/reviews/${product.id}" class="text-decoration-none">
                    ${product.name}
                </a>
            </td>
            <td>${fmt(product.price)}원</td>
            <td>${product.category || '-'}</td>
            <td>${soldOutText}</td>
            <td>${actionButtons}</td>
        `;
        tbody.appendChild(tr);
    });
}

/**
 * 카테고리 필터 변경
 */
function setCategoryFilter(filter) {
    currentCategoryFilter = filter; // 'ALL' | '음료' | '푸드'
    updateFilterButtons();
    renderProducts();
}

/**
 * 필터 버튼 UI 상태 갱신
 */
function updateFilterButtons() {
    const btnAll = document.getElementById('btn-filter-all');
    const btnDrink = document.getElementById('btn-filter-drink');
    const btnFood = document.getElementById('btn-filter-food');

    // 💡 카테고리 필터 버튼도 커피 스타일 CSS에 맞게 업데이트
    const buttons = [btnAll, btnDrink, btnFood];
    buttons.forEach(btn => {
        if (!btn) return;
        // 기존 클래스 제거 (btn-secondary, text-white)
        btn.classList.remove('btn-coffee', 'text-white');
        // 아웃라인 커피 스타일로 설정
        btn.classList.add('btn-outline-coffee');
    });

    let activeBtn = null;
    if (currentCategoryFilter === 'ALL') activeBtn = btnAll;
    if (currentCategoryFilter === '음료') activeBtn = btnDrink;
    if (currentCategoryFilter === '푸드') activeBtn = btnFood;

    if (activeBtn) {
        // 활성화된 버튼은 솔리드 커피 스타일로 설정
        activeBtn.classList.remove('btn-outline-coffee');
        activeBtn.classList.add('btn-coffee', 'text-white');
    }
}

/**
 * 상품 추가 모달 열기 (👔 사장 전용)
 */
function showAddProductModal() {
    if (currentRole !== 'ROLE_OWNER') {
        alert('상품 추가는 사장 계정만 가능합니다.');
        return;
    }

    document.getElementById('modalTitle').textContent = '상품 추가';
    document.getElementById('productForm').reset();
    document.getElementById('productId').value = '';
    document.getElementById('productStock').value = '1';      // 기본: 판매중
    document.getElementById('productCategory').value = '음료'; // 기본: 음료

    productModal.show();
}

/**
 * 상품 수정 모달 열기 (👔 사장 전용)
 */
async function editProduct(id) {
    if (currentRole !== 'ROLE_OWNER') {
        alert('상품 수정은 사장 계정만 가능합니다.');
        return;
    }

    try {
        const response = await fetch(`/api/products/${id}`);
        if (!response.ok) {
            const text = await response.text().catch(()=>'');
            throw new Error(`상품 조회 실패 (${response.status}) ${text}`);
        }

        const product = await response.json();

        document.getElementById('modalTitle').textContent = '상품 수정';
        document.getElementById('productId').value = product.id;
        document.getElementById('productName').value = product.name;
        document.getElementById('productPrice').value = product.price;
        document.getElementById('productStock').value = product.stock > 0 ? '1' : '0';
        document.getElementById('productCategory').value = product.category || '음료';

        productModal.show();
    } catch (error) {
        console.error('상품 정보를 불러오는데 실패했습니다:', error);
        alert('상품 정보를 불러오는데 실패했습니다.');
    }
}

/**
 * 상품 저장 (추가/수정) - 👔 사장 전용
 */
async function saveProduct() {
    if (currentRole !== 'ROLE_OWNER') {
        alert('상품 저장은 사장 계정만 가능합니다.');
        return;
    }

    const id = document.getElementById('productId').value;
    const product = {
        name: document.getElementById('productName').value,
        price: parseFloat(document.getElementById('productPrice').value),
        stock: parseInt(document.getElementById('productStock').value), // 1 or 0
        category: document.getElementById('productCategory').value
    };

    try {
        const url = id ? `/api/products/${id}` : '/api/products';
        const method = id ? 'PUT' : 'POST';

        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(product)
        });

        if (!response.ok) {
            const text = await response.text().catch(()=>'');
            throw new Error(`저장에 실패했습니다. (${response.status}) ${text}`);
        }

        productModal.hide();
        await loadProducts();
        alert('저장되었습니다.');
    } catch (error) {
        console.error('저장에 실패했습니다:', error);
        alert('저장에 실패했습니다.');
    }
}

/**
 * 상품 삭제 - 👔 사장 전용
 */
async function deleteProduct(id) {
    if (currentRole !== 'ROLE_OWNER') {
        alert('상품 삭제는 사장 계정만 가능합니다.');
        return;
    }

    if (!confirm('정말 삭제하시겠습니까?')) {
        return;
    }

    try {
        const response = await fetch(`/api/products/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            const text = await response.text().catch(()=>'');
            throw new Error(`삭제에 실패했습니다. (${response.status}) ${text}`);
        }

        await loadProducts();
        alert('삭제되었습니다.');
    } catch (error) {
        console.error('삭제에 실패했습니다:', error);
        alert('삭제에 실패했습니다.');
    }
}

/**
 * 장바구니 담기 - 👤 사용자(ROLE_USER) 전용
 */
async function addToCart(productId) {
    if (currentRole !== 'ROLE_USER') {
        alert('장바구니 기능은 일반 사용자 계정에서만 사용할 수 있습니다.');
        return;
    }

    try {
        const userId = localStorage.getItem('userId') || '1'; // ✅ 임시(게이트웨이 연동 전)

        const response = await fetch('/api/cart/items', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-USER-ID': userId               // ✅ Cart API 요구 헤더
            },
            body: JSON.stringify({
                productId: productId,
                quantity: 1
            })
        });

        if (!response.ok) {
            const text = await response.text().catch(()=>'');
            throw new Error(`장바구니 담기에 실패했습니다. (${response.status}) ${text}`);
        }

        // ✅ 담은 뒤 장바구니 화면으로 이동
        location.href = '/cart';
        // alert('장바구니에 담았습니다.');
    } catch (error) {
        console.error('장바구니 담기에 실패했습니다:', error);
        alert('장바구니 담기에 실패했습니다.');
    }
}