package com.JiCode.ProductDev.adaptor.in;


import com.JiCode.ProductDev.application.ProjectApplication;
import com.JiCode.ProductDev.application.dto.CreateProjectDto;
import com.JiCode.ProductDev.application.dto.UpdateProjectDto;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;
import com.JiCode.ProductDev.util.ComResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Project")
public class ProjectController {
    @Autowired
    ProjectApplication projectApplication;

    @GetMapping("/all")
    public ComResponse<List<ProjectAggregation>> all(@RequestParam("organization_id")String organization_id) throws SelectFailureException {
        return ComResponse.success(projectApplication.getAllProject(organization_id));
    }

    @PostMapping("/add")
    public ComResponse<?> add(@RequestBody CreateProjectDto createProjectDto) {
        projectApplication.createProject(createProjectDto);
        return ComResponse.success();
    }

    @DeleteMapping("/delete")
    public ComResponse<?> delete(@RequestParam("projectId")String projectId) {
        projectApplication.deleteProject(projectId);
        return ComResponse.success();
    }

    @GetMapping("/get")
    public ComResponse<?> get(@RequestParam("projectId")String projectId) {
        return ComResponse.success(projectApplication.getProjectInfo(projectId));
    }
    @PutMapping("/update")
    public ComResponse<?> update(@RequestBody UpdateProjectDto updateProjectDto)
    {
        projectApplication.updateProject(updateProjectDto);
        return ComResponse.success();
    }






}
