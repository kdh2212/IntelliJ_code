package com.du.accountproj251001.controller;

import com.du.accountproj251001.dto.LoginDto;
import com.du.accountproj251001.entity.Expense;
import com.du.accountproj251001.entity.MyUser;
import com.du.accountproj251001.repository.MyuserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PageController {
    private final MyuserRepository myuserRepository;

    // ✅ 루트 URL 접속 시 login.html로 이동
    @GetMapping("/")
    public String showLoginPage() {
        return "main"; // → src/main/resources/templates/login.html
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupPage() {
        return "signup"; // signup.html (src/main/resources/templates/signup.html)
    }

    @GetMapping("/signupBack")
    public String singuoBack(){
        return "main";
    }

    // ✅ 회원 등록
    @PostMapping("/signup")
    public ResponseEntity<MyUser> createMyUser(@RequestBody MyUser myUser) {
        MyUser saved = myuserRepository.save(myUser);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/index")
    public String indexPage() {
        return "index"; // ✅ src/main/resources/templates/index.html
    }

//    @PostMapping("/login")
//    @ResponseBody
//    public ResponseEntity<?> login(@RequestBody LoginDto request) {
//        MyUser user = myuserRepository.findByLoginId(request.getLoginId());
//
//        if (user == null || !user.getPassword().equals(request.getPassword())) {
//            return ResponseEntity.status(401).body("아이디 또는 비밀번호가 올바르지 않습니다.");
//        }
//
//        return ResponseEntity.ok("로그인 성공");
//
//    }

//    public String login(@RequestParam String username, @RequestParam String password) {
//
//    }

    @PostMapping("/login")
    // HttpServletRequest 객체를 파라미터로 추가합니다.
    public ResponseEntity<?> login(@RequestBody LoginDto request, HttpServletRequest httpRequest) {
        MyUser user = myuserRepository.findByLoginId(request.getLoginId());

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            // 로그인 실패: 상태 코드 401 Unathorized 반환
            return ResponseEntity.status(401).body("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        // ---------------------------------------------
        // 🚨 세션 생성 및 사용자 정보 저장 부분
        // ---------------------------------------------

        // 1. 요청으로부터 세션을 가져오거나, 없으면 새로 생성합니다 (true).
        HttpSession session = httpRequest.getSession(true);

        // 2. 세션에 사용자 정보를 저장합니다.
        // 일반적으로 세션에는 사용자 객체 전체보다는 식별자(ID)만 저장하는 것이 권장됩니다.
        // 여기서는 예시로 사용자 ID(pk)와 로그인 ID를 저장합니다.
        session.setAttribute("USER_ID", user.getId());        // DB의 기본 키 (Primary Key)
        session.setAttribute("LOGIN_ID", user.getLoginId());  // 로그인에 사용된 ID
        session.setAttribute("USERNAME", user.getUsername());

        // 세션의 만료 시간 설정 (예: 3600초 = 1시간)
        session.setMaxInactiveInterval(3600);

        // ---------------------------------------------

        // 로그인 성공: 상태 코드 200 OK와 함께 메시지 반환
        return ResponseEntity.ok("로그인 성공");
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // 기존 세션 가져오기 (없으면 null)
        if (session != null) {
            session.invalidate();  // 세션 무효화 (로그아웃)
        }
        return ResponseEntity.ok(Collections.singletonMap("message", "로그아웃 성공"));
    }

    @GetMapping("/api/session")
    @ResponseBody
    public ResponseEntity<?> getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        Long userId = (Long) session.getAttribute("USER_ID");
        String loginId = (String) session.getAttribute("LOGIN_ID");
        String username = (String) session.getAttribute("USERNAME");

        if (userId == null || loginId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 정보 없음");
        }

        Map<String, Object> response = Map.of(
                "userId", userId,
                "loginId", loginId,
                "username", username

        );

        return ResponseEntity.ok(response);
    }

}
