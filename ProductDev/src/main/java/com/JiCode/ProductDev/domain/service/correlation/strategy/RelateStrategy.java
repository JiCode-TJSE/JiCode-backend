package com.JiCode.ProductDev.domain.service.correlation.strategy;

import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JiCode.ProductDev.domain.bo.RelateBo;
import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
import com.JiCode.ProductDev.domain.repository.ReleaseRepository;
import com.JiCode.ProductDev.domain.repository.SprintRepository;

import jakarta.websocket.server.ServerEndpoint;
import lombok.Data;

@Service
@Data
public class RelateStrategy {
    // next strategy level to match relate type
    RelateStrategy successor;

    public boolean testType(RelateBo item1, RelateBo item2)
    {
        return true;
    }

    protected void checkSuccessor(RelateBo item1, RelateBo item2)
    {
        BacklogRelateStrategy backlogRelateStrategy = new BacklogRelateStrategy();
        SprintRelateStrategy sprintRelateStrategy = new SprintRelateStrategy();
        ReleaseRelateStrategy releaseRelateStrategy = new ReleaseRelateStrategy();
        
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

    public RelateStrategy getSuccessor() {
        return successor;
    }

    public void setSuccessor(RelateStrategy successor) {
        this.successor = successor;
    }

    public void relate(RelateBo item1, RelateBo item2) throws InsertFailureException {
        checkSuccessor(item1, item2);
        successor.relate(item1, item2);
    }
}
