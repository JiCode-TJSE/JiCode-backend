package com.JiCode.ProductMa.application.Impl;

import com.JiCode.ProductMa.application.ProductMaApplication;
import com.JiCode.ProductMa.application.dto.ProductSimpleInfoArrDTO;
import com.JiCode.ProductMa.domain.model.RequirementAggregation;
import com.JiCode.ProductMa.domain.model.VersionAggregation;
import com.JiCode.ProductMa.domain.repository.VersionRepository;
import com.JiCode.ProductMa.domain.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductMaApplicationImpl
        implements ProductMaApplication {

    @Autowired
    VersionRepository versionRepository;

    @Autowired
    RequirementRepository requirementRepository;

    @Transactional(readOnly = true)
    public ProductSimpleInfoArrDTO getAllProductsByUserID(String userID) {
        // TODO 等写逻辑捏，注意 aggregation 和 repository 都可能会抛出异常，异常是在 application 层统一处理的，记得
        // try catch
        return new ProductSimpleInfoArrDTO();
    }

}
