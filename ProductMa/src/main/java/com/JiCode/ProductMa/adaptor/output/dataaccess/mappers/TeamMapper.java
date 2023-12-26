package com.JiCode.ProductMa.adaptor.output.dataaccess.mappers;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Team;
import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.TeamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TeamMapper {
    long countByExample(TeamExample example);

    int deleteByExample(TeamExample example);

    int deleteByPrimaryKey(String teamId);

    int insert(Team record);

    int insertSelective(Team record);

    List<Team> selectByExampleWithRowbounds(TeamExample example, RowBounds rowBounds);

    List<Team> selectByExample(TeamExample example);

    Team selectByPrimaryKey(String teamId);

    int updateByExampleSelective(@Param("record") Team record, @Param("example") TeamExample example);

    int updateByExample(@Param("record") Team record, @Param("example") TeamExample example);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);
}