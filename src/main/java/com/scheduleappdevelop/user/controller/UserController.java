package com.scheduleappdevelop.user.controller;

import com.scheduleappdevelop.user.dto.*;
import com.scheduleappdevelop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // 생성
    @PostMapping
    public ResponseEntity<UserCreateResponse> createUser(
            @RequestBody UserCreateRequest request
    ) {
        UserCreateResponse response = userService.saveUser(request);
        URI location = URI.create("/users" + response.getId());

        return ResponseEntity.created(location).body(response);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<UserGetResponse>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // 단 건 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserGetResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUser(id));
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<UserUpdateResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateRequest request
    ) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
