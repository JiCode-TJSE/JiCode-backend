package com.JiCode.ProductDev.domain.repository.Impl;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Sprint;
import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Workhour;
import com.JiCode.ProductDev.adaptor.output.dataaccess.mappers.WorkhourMapper;
import com.JiCode.ProductDev.domain.factory.WorkhourFactory;
import com.JiCode.ProductDev.domain.model.WorkhourAggregation;
import com.JiCode.ProductDev.domain.repository.WorkhourRepository;
import com.JiCode.ProductDev.exceptions.WorkHour.DeleteFailureException;
import com.JiCode.ProductDev.exceptions.WorkHour.SelectFailureException;
import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;
import com.JiCode.ProductDev.exceptions.sprint.UpdateFaliureException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WorkhourRepositoryImpl implements WorkhourRepository {
    @Autowired
    WorkhourMapper workhourMapper;

    @Autowired
    WorkhourFactory workhourFactory;

    public WorkhourAggregation selectById(String id) throws SelectFailureException {
       try{
           Workhour workhour = workhourMapper.selectByPrimaryKey(id);
           return workhourFactory.createWorkhour(workhour.getId(), workhour.getHours(), workhour.getDate(), workhour.getType(), workhour.getDetail(), workhour.getScheduleId());
       }catch(Exception e){
           throw new SelectFailureException("Can not find this workhour id: "+id);
       }
    }

    public PageInfo<WorkhourAggregation> getPage(int pageNum, int pageSize) throws SelectFailureException {
        try{
            PageHelper.startPage(pageNum, pageSize);
            Page<Workhour> workhours = workhourMapper.selectByPaging(null);
            List<WorkhourAggregation> workhourAggregations = new ArrayList<>();
            for(Workhour workhour : workhours){
                WorkhourAggregation workhourAggregation = workhourFactory.createWorkhour(workhour.getId(), workhour.getHours(), workhour.getDate(), workhour.getType(), workhour.getDetail(), workhour.getScheduleId());
                workhourAggregations.add(workhourAggregation);
            }
            return new PageInfo<>(workhourAggregations);
        }catch (Exception e){
            throw new SelectFailureException("Fail to get workhour page");
        }
    }

    public int insert(WorkhourAggregation workhourAggregation) throws InsertFailureException {
        try{
            Workhour workhour = new Workhour();
            if(workhourAggregation.getId()==null)
                workhour.setId(UUID.randomUUID().toString());
            BeanUtils.copyProperties(workhourAggregation, workhour);
            return workhourMapper.insert(workhour);
        }catch (Exception e){
            throw new InsertFailureException("Fail to insert workhour");
        }
    }

    public int update(WorkhourAggregation workhourAggregation) throws UpdateFaliureException {
        try{
            Workhour workhour = new Workhour();
            BeanUtils.copyProperties(workhourAggregation, workhour);
            return workhourMapper.updateByPrimaryKey(workhour);
        }catch (Exception e){
            throw new UpdateFaliureException("Fail to update workhour id: "+workhourAggregation.getId());
        }
    }

    public int delete(String id) throws DeleteFailureException {
        try{
            return workhourMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            throw new DeleteFailureException("Fail to delete workhour id: "+id);
        }
    }
}
