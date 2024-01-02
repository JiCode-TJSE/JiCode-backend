package com.JiCode.ProductDev.adaptor.in;

import com.JiCode.ProductDev.application.ProjectApplication;
import com.JiCode.ProductDev.application.SprintApplication;
import com.JiCode.ProductDev.application.dto.CreateSprintDto;
import com.JiCode.ProductDev.domain.model.SprintAggregation;
import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;
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
    public ComResponse<?> get(@RequestParam("sprintId")String sprintId) {
        return ComResponse.success(sprintApplication.selectSprint(sprintId));
    }


}
