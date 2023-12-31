package com.JiCode.ProductDev.domain.service.correlation.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JiCode.ProductDev.domain.bo.RelateBo;

import jakarta.websocket.server.ServerEndpoint;
import lombok.Data;

@Service
@Data
public class RelateStrategy {
    @Autowired
    BacklogRelateStrategy backlogRelateStrategy;

    @Autowired
    SprintRelateStrategy sprintRelateStrategy;

    @Autowired
    ReleaseRelateStrategy releaseRelateStrategy;

    // next strategy level to match relate type
    RelateStrategy successor;

    public boolean testType(RelateBo item1, RelateBo item2)
    {
        return true;
    }

    protected void checkSuccessor(RelateBo item1, RelateBo item2)
    {
        if(backlogRelateStrategy.testType(item1, item2)){
            setSuccessor(backlogRelateStrategy);
        }
        else if(sprintRelateStrategy.testType(item1, item2)){
            setSuccessor(sprintRelateStrategy);
        }
        else if(releaseRelateStrategy.testType(item1, item2)){
            setSuccessor(releaseRelateStrategy);
        }
    }

    public void relate(RelateBo item1, RelateBo item2)
    {
        checkSuccessor(item1, item2);
        successor.relate(item1, item2);
    }
}
