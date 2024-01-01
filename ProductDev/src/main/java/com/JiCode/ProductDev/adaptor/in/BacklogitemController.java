package com.JiCode.ProductDev.adaptor.in;

import com.JiCode.ProductDev.adaptor.in.vo.SelectAllBacklogitemVo;
import com.JiCode.ProductDev.application.BacklogitemApplication;
import com.JiCode.ProductDev.application.dto.SelectAllBacklogitemDto;
import com.JiCode.ProductDev.util.ComResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/backlogitem")
@CrossOrigin(origins = "*")
public class BacklogitemController {
    @Autowired
    BacklogitemApplication backlogitemApplication;

    @GetMapping("/get")
    public ComResponse<List<SelectAllBacklogitemVo>> selectAll(@RequestParam("organizationId") String organizationId){
        List<SelectAllBacklogitemVo> selectAllBacklogitemVos = new ArrayList<SelectAllBacklogitemVo>();
        List<SelectAllBacklogitemDto> selectAllBacklogitemDtos = backlogitemApplication.selectAll(organizationId);
        for(SelectAllBacklogitemDto selectAllBacklogitemDto:selectAllBacklogitemDtos){
            SelectAllBacklogitemVo selectAllBacklogitemVo = new SelectAllBacklogitemVo();
            BeanUtils.copyProperties(selectAllBacklogitemDto,selectAllBacklogitemVo);
            selectAllBacklogitemVos.add(selectAllBacklogitemVo);
        }
        return ComResponse.success(selectAllBacklogitemVos);

    }
}
