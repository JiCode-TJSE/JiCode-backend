package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Backlogitem;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.BacklogitemMapper;
import com.JiCode.ProductDev.domain.model.BacklogItemAggregation;
import com.JiCode.ProductDev.domain.model.ProjectAggregation;
import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class BacklogItemRepositoryImpl implements BacklogItemRepository {
    @Autowired
    BacklogitemMapper backlogitemMapper;

    public BacklogItemAggregation selectById(String id){
        Backlogitem backlogitem = backlogitemMapper.selectByPrimaryKey(id);
        return BacklogItemAggregation.createBacklogItem(backlogitem.getId(),backlogitem.getPriority(),backlogitem.getStartTime(),backlogitem.getEndTime(),backlogitem.getSource(),backlogitem.getType(),backlogitem.getDescription(),backlogitem.getProjectId(),backlogitem.getManagerId(),backlogitem.getScheduleId());
    }

    public PageInfo<BacklogItemAggregation> getPage(int pageNum, int pageSize){
        try{
            PageHelper.startPage(pageNum, pageSize);
            Page<Backlogitem> backlogitems = backlogitemMapper.selectByPaging(null);
            System.out.println(backlogitems);
            List<BacklogItemAggregation> backlogItemAggregations = new ArrayList<>();
            for (Backlogitem backlogitem : backlogitems) {
                BacklogItemAggregation backlogItemAggregation = BacklogItemAggregation.createBacklogItem(backlogitem.getId(),backlogitem.getPriority(),backlogitem.getStartTime(),backlogitem.getEndTime(),backlogitem.getSource(),backlogitem.getType(),backlogitem.getDescription(),backlogitem.getProjectId(),backlogitem.getManagerId(),backlogitem.getScheduleId()); // use builder to create ProjectAggregation
                backlogItemAggregations.add(backlogItemAggregation);
            }
            return new PageInfo<>(backlogItemAggregations);
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    public int insert(BacklogItemAggregation backlogItemAggregation){
        try {
            Backlogitem backlogitem = new Backlogitem();
            BeanUtils.copyProperties(backlogItemAggregation, backlogitem);
            if(backlogitem.getId()==null)
                backlogitem.setId(UUID.randomUUID().toString());
            System.out.println(backlogitem);
            return backlogitemMapper.insert(backlogitem);

        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int updateById(BacklogItemAggregation backlogItemAggregation){
        try{
            Backlogitem backlogitem = new Backlogitem();
            BeanUtils.copyProperties(backlogItemAggregation, backlogitem);
            return backlogitemMapper.updateByPrimaryKey(backlogitem);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int deleteById(String id){
        try{
            return backlogitemMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
}
