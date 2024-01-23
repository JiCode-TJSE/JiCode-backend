package com.JiCode.ProductDev.adaptor.in;

import com.JiCode.ProductDev.application.ProjectApplication;
import com.JiCode.ProductDev.application.SprintApplication;
import com.JiCode.ProductDev.application.dto.CreateSprintDto;
import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;
import com.JiCode.ProductDev.exceptions.sprint.DeleteFailureException;
import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;
import com.JiCode.ProductDev.exceptions.sprint.UpdateFaliureException;
import com.JiCode.ProductDev.util.ComResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productdev")
public class SprintController {
    @Autowired
    SprintApplication sprintApplication;

    @PostMapping("/sprint")
    public ComResponse<?> add(@RequestBody CreateSprintDto createSprintDto) throws InsertFailureException {
        sprintApplication.createSprint(createSprintDto);
        return ComResponse.success();
    }

    @GetMapping("/sprints")
    public ComResponse<?> get(@RequestParam("organizationId")String organizationId) {
        return ComResponse.success(sprintApplication.selectSprint(organizationId));
    }

    @DeleteMapping("/sprint")
    public ComResponse<Integer> delete(@RequestParam("sprintId")String sprintId) throws DeleteFailureException {
        return ComResponse.success(sprintApplication.delete(sprintId));
    }

    @PutMapping("/sprint")
    public ComResponse<Integer> update(@RequestBody CreateSprintDto createSprintDto) throws DeleteFailureException, UpdateFaliureException {
        return ComResponse.success(sprintApplication.update(createSprintDto));
    }

    @GetMapping("/sprint")
    public ComResponse<?> getById(@RequestParam("sprintId")String sprintId) throws DeleteFailureException, SelectFailureException {
        return ComResponse.success(sprintApplication.selectById(sprintId));
    }
}
