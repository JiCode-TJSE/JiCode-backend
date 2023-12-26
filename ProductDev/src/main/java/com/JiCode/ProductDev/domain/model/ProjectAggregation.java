package com.JiCode.ProductDev.domain.model;

import com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels.Project;
import com.JiCode.ProductDev.domain.repository.ProjectRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 聚合类
 * @author Laurent Wu
 * @date 2023/12/24
 */
@Data
@NoArgsConstructor
public class ProjectAggregation {

    private String id;

    String status;

    private Float progress;

    private Date startTime;

    private Date endTime;

    private String managerId;

    private List<String> member;

    public List<String> getMember() {
        return member;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Float getProgress() {
        return progress;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProgress(Float progress) {
        this.progress = progress;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public void setMember(List<String> member) {
        this.member = member;
    }
}
