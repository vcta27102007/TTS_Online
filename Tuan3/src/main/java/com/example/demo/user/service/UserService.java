package com.example.demo.user.service;

import com.example.demo.common.exception.BadRequestException;
import com.example.demo.common.exception.NotFoundException;
import com.example.demo.user.dto.UserRequest;
import com.example.demo.user.dto.UserResponse;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public UserResponse getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user với id = " + id));
        return toResponse(user);
    }

    public UserResponse createUser(UserRequest request) {
        validateRequest(request);

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email đã tồn tại: " + request.getEmail());
        }

        UserEntity user = new UserEntity();
        user.setTen(request.getTen().trim());
        user.setEmail(request.getEmail().trim());
        user.setRole(request.getRole().trim());
        user.setDangHoatDong(request.getDangHoatDong() != null ? request.getDangHoatDong() : true);

        return toResponse(userRepository.save(user));
    }

    public UserResponse updateUser(Long id, UserRequest request) {
        validateRequest(request);

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user với id = " + id));

        userRepository.findByEmail(request.getEmail().trim())
                .filter(found -> !found.getId().equals(id))
                .ifPresent(found -> {
                    throw new BadRequestException("Email đã tồn tại: " + request.getEmail());
                });

        user.setTen(request.getTen().trim());
        user.setEmail(request.getEmail().trim());
        user.setRole(request.getRole().trim());
        user.setDangHoatDong(request.getDangHoatDong() != null ? request.getDangHoatDong() : user.getDangHoatDong());

        return toResponse(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user với id = " + id));
        userRepository.delete(user);
    }

    private void validateRequest(UserRequest request) {
        if (request == null) {
            throw new BadRequestException("Request không được null");
        }
        if (request.getTen() == null || request.getTen().isBlank()) {
            throw new BadRequestException("Tên không được để trống");
        }
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new BadRequestException("Email không được để trống");
        }
        if (!request.getEmail().contains("@")) {
            throw new BadRequestException("Email không hợp lệ");
        }
        if (request.getRole() == null || request.getRole().isBlank()) {
            throw new BadRequestException("Role không được để trống");
        }
        if (!request.getRole().equals("USER") && !request.getRole().equals("MANAGER")) {
            throw new BadRequestException("Role chỉ được là USER hoặc MANAGER");
        }
    }

    private UserResponse toResponse(UserEntity user) {
        return new UserResponse(
                user.getId(),
                user.getTen(),
                user.getEmail(),
                user.getRole(),
                user.getDangHoatDong(),
                user.getCreatedAt()
        );
    }
}