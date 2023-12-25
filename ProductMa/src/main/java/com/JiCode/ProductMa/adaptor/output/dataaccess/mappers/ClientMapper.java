package com.JiCode.ProductMa.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Client;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.ClientExample;
import java.util.List;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface ClientMapper {
    long countByExample(ClientExample example);

    int deleteByExample(ClientExample example);

    int deleteByPrimaryKey(String id);

    int insert(Client record);

    int insertSelective(Client record);

    List<Client> selectByExample(ClientExample example);

    Client selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Client record, @Param("example") ClientExample example);

    int updateByExample(@Param("record") Client record, @Param("example") ClientExample example);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);

    //分页查询
    Page<Client> selectByPaging(ClientExample example);

}