package com.scheduleappdevelop.user.service;

import com.scheduleappdevelop.user.dto.*;
import com.scheduleappdevelop.user.entity.User;
import com.scheduleappdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 생성
    @Transactional
    public UserCreateResponse saveUser(UserCreateRequest request) {
        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        User savedUser = userRepository.save(user);

        return new UserCreateResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<UserGetResponse> findAll() {
        List<User> users = userRepository.findAll();
        List<UserGetResponse> dtos = new ArrayList<>();

        for (User user : users) {
            dtos.add(new UserGetResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            ));
        }

        return dtos;
    }

    // 단 건 조회
    @Transactional(readOnly = true)
    public UserGetResponse findUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );

        return new UserGetResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 수정
    @Transactional
    public UserUpdateResponse updateUser(Long id, UserUpdateRequest request) {
        User user = validateUser(id, request.getPassword());

        user.update(request.getName());

        return new UserUpdateResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 삭제
    @Transactional
    public void deleteUser(Long id, String password) {
        User user = validateUser(id, password);

        userRepository.delete(user);
    }

    // id, 비밀번호 검증 메서드
    private User validateUser(Long id, String password) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );

        if (!Objects.equals(user.getPassword(), password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}
