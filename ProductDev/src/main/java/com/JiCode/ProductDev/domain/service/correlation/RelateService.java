package com.JiCode.ProductDev.domain.service.correlation;

import com.JiCode.ProductDev.domain.bo.RelateBo;
import com.JiCode.ProductDev.exceptions.sprint.InsertFailureException;

public interface RelateService {
    public void relate(RelateBo item1, RelateBo item2) throws InsertFailureException;
}
