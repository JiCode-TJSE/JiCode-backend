package com.JiCode.ProductDev.domain.model;

import com.JiCode.ProductDev.domain.repository.ScheduleRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Data
@NoArgsConstructor
public class  ScheduleAggregation {

    private String id;
    private Integer estimatedWorkhour;
    private Integer actualWorkhour;
    private Integer remainWorkhour;
    private float progress;

    // 子聚合根
    private List<WorkhourAggregation> workhourAggregation;

    public List<WorkhourAggregation> getWorkhourAggregation() {
        return workhourAggregation;
    }

    public void setWorkhourAggregation(List<WorkhourAggregation> workhourAggregation) {
        this.workhourAggregation = workhourAggregation;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setEstimatedWorkhour(Integer estimatedWorkhour) {
        this.estimatedWorkhour = estimatedWorkhour;
    }

    public void setActualWorkhour(Integer actualWorkhour) {
        this.actualWorkhour = actualWorkhour;
    }

    public void setRemainWorkhour(Integer remainWorkhour) {
        this.remainWorkhour = remainWorkhour;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }


}
