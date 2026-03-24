package com.example.demo.project.service;

import com.example.demo.common.exception.BadRequestException;
import com.example.demo.common.exception.NotFoundException;
import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectEntity> getAll() {
        return projectRepository.findAll();
    }

    public ProjectEntity getById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy project với id = " + id));
    }

    public ProjectEntity create(ProjectEntity request) {
        if (request == null) {
            throw new BadRequestException("Request không được null");
        }
        if (request.getTenProject() == null || request.getTenProject().isBlank()) {
            throw new BadRequestException("Tên project không được để trống");
        }

        ProjectEntity project = new ProjectEntity();
        project.setTenProject(request.getTenProject().trim());
        project.setMoTa(request.getMoTa());

        return projectRepository.save(project);
    }
}