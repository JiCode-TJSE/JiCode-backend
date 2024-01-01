package com.JiCode.ProductDev.adaptor.in;

import com.JiCode.ProductDev.application.ReleaseApplication;
import com.JiCode.ProductDev.application.dto.ReleaseDto;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/release")
@CrossOrigin(origins = "*")
public class ReleaseController {
    @Autowired
    ReleaseApplication releaseApplication;

    @GetMapping("/all")
    public List<ReleaseDto> selectAll(@RequestParam("organizationId")String organizationId){
        return releaseApplication.selectAll(organizationId);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ReleaseDto releaseDto){
        return releaseApplication.insert(releaseDto);
    }
}
