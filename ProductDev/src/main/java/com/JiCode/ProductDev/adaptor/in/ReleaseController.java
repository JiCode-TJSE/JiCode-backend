package com.JiCode.ProductDev.adaptor.in;

import com.JiCode.ProductDev.application.ReleaseApplication;
import com.JiCode.ProductDev.application.dto.ReleaseDto;
import com.JiCode.ProductDev.util.ComResponse;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productdev")
public class ReleaseController {
    @Autowired
    ReleaseApplication releaseApplication;

    @GetMapping("/releases")
    public ComResponse<?> selectAll(@RequestParam("organizationId")String organizationId,@RequestParam(value = "projectId",required = false)String projectId){
        return ComResponse.success(releaseApplication.selectAll(organizationId,projectId));
    }

    @PostMapping("/release")
    public ComResponse<?> insert(@RequestBody ReleaseDto releaseDto){
        return ComResponse.success(releaseApplication.insert(releaseDto));
    }

    @DeleteMapping("/release")
    public ComResponse<?> delete(@RequestParam("releaseId")String releaseId){
        return ComResponse.success(releaseApplication.delete(releaseId));
    }

    @GetMapping("/release")
    public ComResponse<?> selectById(@RequestParam("releaseId")String releaseId){
        return ComResponse.success(releaseApplication.selectById(releaseId));
    }

    @PutMapping("/release")
    public ComResponse<?> update(@RequestBody ReleaseDto releaseDto){
        return ComResponse.success(releaseApplication.update(releaseDto));
    }
}
