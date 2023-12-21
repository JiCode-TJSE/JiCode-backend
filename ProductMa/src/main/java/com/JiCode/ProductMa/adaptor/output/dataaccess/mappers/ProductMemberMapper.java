package com.JiCode.ProductMa.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.ProductMemberExample;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.ProductMemberKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMemberMapper {
    long countByExample(ProductMemberExample example);

    int deleteByExample(ProductMemberExample example);

    int deleteByPrimaryKey(ProductMemberKey key);

    int insert(ProductMemberKey record);

    int insertSelective(ProductMemberKey record);

    List<ProductMemberKey> selectByExample(ProductMemberExample example);

    int updateByExampleSelective(@Param("record") ProductMemberKey record, @Param("example") ProductMemberExample example);

    int updateByExample(@Param("record") ProductMemberKey record, @Param("example") ProductMemberExample example);
}