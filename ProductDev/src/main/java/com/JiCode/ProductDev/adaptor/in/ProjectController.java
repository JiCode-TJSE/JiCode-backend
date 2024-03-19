package com.JiCode.ProductDev.adaptor.in;


import com.JiCode.ProductDev.application.ProjectApplication;
import com.JiCode.ProductDev.application.dto.CreateProjectDto;
import com.JiCode.ProductDev.application.dto.RelateDto;
import com.JiCode.ProductDev.application.dto.UpdateProjectDto;
import com.JiCode.ProductDev.domain.bo.RelateBo;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;
import com.JiCode.ProductDev.exceptions.project.InsertProjectFailureException;
import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;
import com.JiCode.ProductDev.util.ComResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productdev")
public class ProjectController {
    @Autowired
    ProjectApplication projectApplication;

    @GetMapping("/projects")
    public ComResponse<List<ProjectAggregation>> all(@RequestParam("organization_id")String organization_id) throws SelectFailureException {
        return ComResponse.success(projectApplication.getAllProject(organization_id));
    }

    @PostMapping("/project")
    public ComResponse<?> add(@RequestBody CreateProjectDto createProjectDto) throws InsertProjectFailureException {
        projectApplication.createProject(createProjectDto);
        return ComResponse.success();
    }

    @DeleteMapping("/project")
    public ComResponse<?> delete(@RequestParam("projectId")String projectId) {
        projectApplication.deleteProject(projectId);
        return ComResponse.success();
    }

    @GetMapping("/project")
    public ComResponse<?> get(@RequestParam("projectId")String projectId) {
        return ComResponse.success(projectApplication.getProjectInfo(projectId));
    }
    @PutMapping("/project")
    public ComResponse<?> update(@RequestBody UpdateProjectDto updateProjectDto)
    {
        projectApplication.updateProject(updateProjectDto);
        return ComResponse.success();
    }

    @PostMapping("/relation")
    public ComResponse relate(@RequestBody RelateDto relateDto) throws InsertFailureException {
        projectApplication.relate(new RelateBo(relateDto.getId1(), relateDto.getType1()),
                                 new RelateBo(relateDto.getId2(), relateDto.getType2()));
        return ComResponse.success();
    }




}
