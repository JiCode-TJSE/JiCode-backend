package com.JiCode.ProductDev.application;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Backlogitem;
import com.JiCode.ProductDev.application.dto.BacklogitemDto;
import com.JiCode.ProductDev.application.dto.SelectAllBacklogitemDto;
import com.JiCode.ProductDev.domain.model.BacklogItemAggregation;
import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class BacklogitemApplication {
    @Autowired
    BacklogItemRepository backlogItemRepository;

    @Transactional(readOnly = true)
    public List<SelectAllBacklogitemDto> selectAll(String organizationId,String projectId){
        List<BacklogItemAggregation> backlogitemAggregations = backlogItemRepository.selectAll();
        System.out.println(backlogitemAggregations.size());
        var ans=new ArrayList<SelectAllBacklogitemDto>();
        for(BacklogItemAggregation backlogItemAggregation:backlogitemAggregations){
            if(backlogItemAggregation.getOrganizationId().equals(organizationId)
            &&(backlogItemAggregation.getProjectId().equals(projectId) || projectId==null)){
                SelectAllBacklogitemDto selectAllBacklogitemDto=new SelectAllBacklogitemDto();
                selectAllBacklogitemDto.setId(backlogItemAggregation.getId());
                selectAllBacklogitemDto.setPriority(backlogItemAggregation.getPriority());
                selectAllBacklogitemDto.setStartTime(backlogItemAggregation.getStartTime());
                selectAllBacklogitemDto.setEndTime(backlogItemAggregation.getEndTime());
                selectAllBacklogitemDto.setSource(backlogItemAggregation.getSource());
                selectAllBacklogitemDto.setType(backlogItemAggregation.getType());
                selectAllBacklogitemDto.setDescription(backlogItemAggregation.getDescription());
                selectAllBacklogitemDto.setProjectId(backlogItemAggregation.getProjectId());
                selectAllBacklogitemDto.setOrganizationId(backlogItemAggregation.getOrganizationId());
                selectAllBacklogitemDto.setManagerId(backlogItemAggregation.getManagerId());
                selectAllBacklogitemDto.setScheduleId(backlogItemAggregation.getScheduleId());
                selectAllBacklogitemDto.setTopic(backlogItemAggregation.getTopic());
                selectAllBacklogitemDto.setStatus(backlogItemAggregation.getStatus());
                selectAllBacklogitemDto.setProjectTopic(backlogItemAggregation.getProjectTopic());
                selectAllBacklogitemDto.setMemberIds(backlogItemAggregation.getMemberIds());
                selectAllBacklogitemDto.setSprintIds(backlogItemAggregation.getSprintIds());
                selectAllBacklogitemDto.setReleaseIds(backlogItemAggregation.getReleaseIds());
                System.out.println(selectAllBacklogitemDto.toString());
                ans.add(selectAllBacklogitemDto);
            }
        }
        return ans;


    }

    @Transactional(readOnly = true)
    public List<BacklogitemDto> selectManaged(String accountId){
        List<BacklogItemAggregation> backlogitemAggregations = backlogItemRepository.selectAll();
        var ans=new ArrayList<BacklogitemDto>();
        for(BacklogItemAggregation backlogItemAggregation:backlogitemAggregations){
            System.out.println(backlogItemAggregation.getManagerId());
            if(backlogItemAggregation.getManagerId().equals(accountId)){
                BacklogitemDto backlogitemDto=new BacklogitemDto();
                backlogitemDto.setId(backlogItemAggregation.getId());
                backlogitemDto.setPriority(backlogItemAggregation.getPriority());
                backlogitemDto.setStartTime(backlogItemAggregation.getStartTime());
                backlogitemDto.setEndTime(backlogItemAggregation.getEndTime());
                backlogitemDto.setSource(backlogItemAggregation.getSource());
                backlogitemDto.setType(backlogItemAggregation.getType());
                backlogitemDto.setDescription(backlogItemAggregation.getDescription());
                backlogitemDto.setProjectId(backlogItemAggregation.getProjectId());
                backlogitemDto.setOrganizationId(backlogItemAggregation.getOrganizationId());
                backlogitemDto.setManagerId(backlogItemAggregation.getManagerId());
                backlogitemDto.setScheduleId(backlogItemAggregation.getScheduleId());
                backlogitemDto.setTopic(backlogItemAggregation.getTopic());
                backlogitemDto.setStatus(backlogItemAggregation.getStatus());
                backlogitemDto.setMemberIds(backlogItemAggregation.getMemberIds());
                backlogitemDto.setSprintIds(backlogItemAggregation.getSprintIds());
                backlogitemDto.setReleaseIds(backlogItemAggregation.getReleaseIds());
                ans.add(backlogitemDto);
            }
        }
        return ans;
    }

    @Transactional(readOnly = false)
    public int delete(String id){
        return backlogItemRepository.deleteById(id);

    }

    @Transactional(readOnly = false)
    public int insert(BacklogitemDto backlogitemDto){
        System.out.println(backlogitemDto.toString());
        BacklogItemAggregation backlogItemAggregation=new BacklogItemAggregation();
        backlogItemAggregation.setId(backlogitemDto.getId());
        backlogItemAggregation.setPriority(backlogitemDto.getPriority());
        backlogItemAggregation.setStartTime(backlogitemDto.getStartTime());
        backlogItemAggregation.setEndTime(backlogitemDto.getEndTime());
        backlogItemAggregation.setSource(backlogitemDto.getSource());
        backlogItemAggregation.setType(backlogitemDto.getType());
        backlogItemAggregation.setDescription(backlogitemDto.getDescription());
        backlogItemAggregation.setProjectId(backlogitemDto.getProjectId());
        backlogItemAggregation.setOrganizationId(backlogitemDto.getOrganizationId());
        backlogItemAggregation.setManagerId(backlogitemDto.getManagerId());
        backlogItemAggregation.setScheduleId(backlogitemDto.getScheduleId());
        backlogItemAggregation.setTopic(backlogitemDto.getTopic());
        backlogItemAggregation.setStatus(backlogitemDto.getStatus()==null?"未开始":backlogitemDto.getStatus());
        backlogItemAggregation.setMemberIds(backlogitemDto.getMemberIds());
        backlogItemAggregation.setSprintIds(backlogitemDto.getSprintIds());
        backlogItemAggregation.setReleaseIds(backlogitemDto.getReleaseIds());

        System.out.println(backlogItemAggregation.getProjectId());
        System.out.println(backlogItemAggregation.getStatus());
        return backlogItemRepository.insert(backlogItemAggregation);
    }

    @Transactional(readOnly = false)
    public int update(BacklogitemDto backlogitemDto){
        BacklogItemAggregation backlogItemAggregation=new BacklogItemAggregation();
        backlogItemAggregation.setId(backlogitemDto.getId());
        BacklogItemAggregation origin = backlogItemRepository.selectById(backlogitemDto.getId());

        backlogItemAggregation.setPriority(backlogitemDto.getPriority()==null?origin.getPriority():backlogitemDto.getPriority());
        backlogItemAggregation.setStartTime(backlogitemDto.getStartTime()==null?origin.getStartTime():backlogitemDto.getStartTime());
        backlogItemAggregation.setEndTime(backlogitemDto.getEndTime()==null?origin.getEndTime():backlogitemDto.getEndTime());
        backlogItemAggregation.setSource(backlogitemDto.getSource()==null?origin.getSource():backlogitemDto.getSource());
        backlogItemAggregation.setType(backlogitemDto.getType()==null?origin.getType():backlogitemDto.getType());
        backlogItemAggregation.setDescription(backlogitemDto.getDescription()==null?origin.getDescription():backlogitemDto.getDescription());
        backlogItemAggregation.setProjectId(backlogitemDto.getProjectId()==null?origin.getProjectId():backlogitemDto.getProjectId());
        backlogItemAggregation.setOrganizationId(backlogitemDto.getOrganizationId()==null?origin.getOrganizationId():backlogitemDto.getOrganizationId());
        backlogItemAggregation.setManagerId(backlogitemDto.getManagerId()==null?origin.getManagerId():backlogitemDto.getManagerId());
        backlogItemAggregation.setScheduleId(backlogitemDto.getScheduleId()==null?origin.getScheduleId():backlogitemDto.getScheduleId());
        backlogItemAggregation.setTopic(backlogitemDto.getTopic()==null?origin.getTopic():backlogitemDto.getTopic());
        backlogItemAggregation.setStatus(backlogitemDto.getStatus()==null?origin.getStatus():backlogitemDto.getStatus());
        backlogItemAggregation.setMemberIds(backlogitemDto.getMemberIds()==null?origin.getMemberIds():backlogitemDto.getMemberIds());
        backlogItemAggregation.setSprintIds(backlogitemDto.getSprintIds()==null?origin.getSprintIds():backlogitemDto.getSprintIds());
        backlogItemAggregation.setReleaseIds(backlogitemDto.getReleaseIds()==null?origin.getReleaseIds():backlogitemDto.getReleaseIds());
        return backlogItemRepository.updateById(backlogItemAggregation);
    }

    @Transactional(readOnly = true)
    public BacklogitemDto selectById(String id){
        BacklogItemAggregation backlogItemAggregation = backlogItemRepository.selectById(id);
        BacklogitemDto backlogitemDto=new BacklogitemDto();
        backlogitemDto.setId(backlogItemAggregation.getId());
        backlogitemDto.setPriority(backlogItemAggregation.getPriority());
        backlogitemDto.setStartTime(backlogItemAggregation.getStartTime());
        backlogitemDto.setEndTime(backlogItemAggregation.getEndTime());
        backlogitemDto.setSource(backlogItemAggregation.getSource());
        backlogitemDto.setType(backlogItemAggregation.getType());
        backlogitemDto.setDescription(backlogItemAggregation.getDescription());
        backlogitemDto.setProjectId(backlogItemAggregation.getProjectId());
        backlogitemDto.setOrganizationId(backlogItemAggregation.getOrganizationId());
        backlogitemDto.setManagerId(backlogItemAggregation.getManagerId());
        backlogitemDto.setScheduleId(backlogItemAggregation.getScheduleId());
        backlogitemDto.setTopic(backlogItemAggregation.getTopic());
        backlogitemDto.setStatus(backlogItemAggregation.getStatus());
        backlogitemDto.setMemberIds(backlogItemAggregation.getMemberIds());
        backlogitemDto.setSprintIds(backlogItemAggregation.getSprintIds());
        backlogitemDto.setReleaseIds(backlogItemAggregation.getReleaseIds());
        backlogitemDto.setBacklogitemIds(backlogItemAggregation.getBacklogitemIds());
        return backlogitemDto;
    }

    public List<BacklogitemDto> getJoined(String accountId){
        List<BacklogItemAggregation> backlogitemAggregations = backlogItemRepository.selectAll();
        List<BacklogitemDto> backlogitemDtos = new ArrayList<>();
        for(BacklogItemAggregation backlogItemAggregation:backlogitemAggregations){
            // 如果工作项的成员列表当中含有当前用户的id，那么就将这个工作项加入到返回列表当中
            if(backlogItemAggregation.getMemberIds().contains(accountId)){
                BacklogitemDto backlogitemDto=new BacklogitemDto();
                backlogitemDto.setId(backlogItemAggregation.getId());
                backlogitemDto.setPriority(backlogItemAggregation.getPriority());
                backlogitemDto.setStartTime(backlogItemAggregation.getStartTime());
                backlogitemDto.setEndTime(backlogItemAggregation.getEndTime());
                backlogitemDto.setSource(backlogItemAggregation.getSource());
                backlogitemDto.setType(backlogItemAggregation.getType());
                backlogitemDto.setDescription(backlogItemAggregation.getDescription());
                backlogitemDto.setProjectId(backlogItemAggregation.getProjectId());
                backlogitemDto.setOrganizationId(backlogItemAggregation.getOrganizationId());
                backlogitemDto.setManagerId(backlogItemAggregation.getManagerId());
                backlogitemDto.setScheduleId(backlogItemAggregation.getScheduleId());
                backlogitemDto.setTopic(backlogItemAggregation.getTopic());
                backlogitemDto.setStatus(backlogItemAggregation.getStatus());
                backlogitemDto.setMemberIds(backlogItemAggregation.getMemberIds());
                backlogitemDto.setSprintIds(backlogItemAggregation.getSprintIds());
                backlogitemDto.setReleaseIds(backlogItemAggregation.getReleaseIds());

                backlogitemDtos.add(backlogitemDto);
            }
        }
        return backlogitemDtos;
    }
}
