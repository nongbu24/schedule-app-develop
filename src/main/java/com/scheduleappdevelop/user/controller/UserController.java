package com.scheduleappdevelop.user.controller;

import com.scheduleappdevelop.user.dto.*;
import com.scheduleappdevelop.user.entity.User;
import com.scheduleappdevelop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpSession session
    ) {
        // 1. 사용자 검증
        User user = userService.login(request);

        // 2. 세션에 저장
        session.setAttribute("loginUser", user);

        return ResponseEntity.ok(new LoginResponse(user.getId(), user.getName()));
    }

    // CRUD
    // 생성
    @PostMapping
    public ResponseEntity<UserCreateResponse> create(
            @Valid @RequestBody UserCreateRequest request
    ) {
        UserCreateResponse response = userService.signup(request);
        URI location = URI.create("/users" + response.getId());

        return ResponseEntity.created(location).body(response);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<UserListResponse> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // 단 건 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserGetResponse> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findOne(id));
    }

    // 수정
    // putMapping에서 PatchMapping으로 수정
    // sessionAttribute에서 id를 가져올 것이므로 경로 삭제
    @PatchMapping
    public ResponseEntity<UserUpdateResponse> updateName(
            @SessionAttribute(name = "loginUser", required = false) User user,
            @RequestBody UserUpdateRequest request
    ) {
        if (user == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        return ResponseEntity.ok(userService.updateName(user.getId(), request));
    }

    // 삭제
    @DeleteMapping
    public ResponseEntity<Void> delete(
            @SessionAttribute(name = "loginUser", required = false) User user
    ) {
        userService.delete(user.getId());

        return ResponseEntity.noContent().build();
    }
}
