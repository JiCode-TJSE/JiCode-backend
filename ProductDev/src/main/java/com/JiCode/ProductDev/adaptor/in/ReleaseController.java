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

    @GetMapping("/all")
    public ComResponse<?> selectAll(@RequestParam("organizationId")String organizationId){
        return ComResponse.success(releaseApplication.selectAll(organizationId));
    }

    @PostMapping("/insert")
    public ComResponse<?> insert(@RequestBody ReleaseDto releaseDto){
        return ComResponse.success(releaseApplication.insert(releaseDto));
    }
}
