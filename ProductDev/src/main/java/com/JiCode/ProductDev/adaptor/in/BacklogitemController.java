package com.JiCode.ProductDev.adaptor.in;

import com.JiCode.ProductDev.adaptor.in.vo.SelectAllBacklogitemVo;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Backlogitem;
import com.JiCode.ProductDev.application.BacklogitemApplication;
import com.JiCode.ProductDev.application.dto.BacklogitemDto;
import com.JiCode.ProductDev.application.dto.SelectAllBacklogitemDto;
import com.JiCode.ProductDev.util.ComResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/backlogitem")
@CrossOrigin(origins = "*")
public class BacklogitemController {
    @Autowired
    BacklogitemApplication backlogitemApplication;

    @GetMapping("/get")
    public ComResponse<List<SelectAllBacklogitemDto>> selectAll(@RequestParam("organizationId") String organizationId){
        List<SelectAllBacklogitemDto> selectAllBacklogitemDtos = backlogitemApplication.selectAll(organizationId);

        return ComResponse.success(selectAllBacklogitemDtos);

    }

    @GetMapping("/managed")
    public ComResponse<List<BacklogitemDto>> selectManaged(@RequestParam("accountId") String accountId){
        List<BacklogitemDto> backlogitemDtos = backlogitemApplication.selectManaged(accountId);
        return ComResponse.success(backlogitemDtos);
    }

    @DeleteMapping("/delete")
    public ComResponse<Integer> delete(@RequestParam("id") String id){
        int ans = backlogitemApplication.delete(id);
        return ComResponse.success(ans);
    }

    @PostMapping("/insert")
    public ComResponse<Integer> insert(@RequestBody BacklogitemDto backlogitemDto){
        int ans = backlogitemApplication.insert(backlogitemDto);
        return ComResponse.success(ans);
    }

    @PostMapping("/update")
    public ComResponse<Integer> update(@RequestBody BacklogitemDto backlogitemDto){
        int ans = backlogitemApplication.update(backlogitemDto);
        return ComResponse.success(ans);
    }

    @GetMapping("/detail")
    public ComResponse<BacklogitemDto> selectById(@RequestParam("id") String id){
        BacklogitemDto backlogitemDto = backlogitemApplication.selectById(id);
        return ComResponse.success(backlogitemDto);
    }

    @GetMapping("/joined")
    public ComResponse<List<BacklogitemDto>> getJoined(@RequestParam("accountId") String accountId){
        List<BacklogitemDto> backlogitemDtos = backlogitemApplication.getJoined(accountId);
        return ComResponse.success(backlogitemDtos);
    }
}
