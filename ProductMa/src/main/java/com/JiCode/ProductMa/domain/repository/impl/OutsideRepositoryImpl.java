package com.JiCode.ProductMa.domain.repository.impl;

import com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels.Team;
import com.JiCode.ProductMa.adaptor.output.dataaccess.mappers.TeamMapper;
import com.JiCode.ProductMa.domain.repository.OutsideRepository;
import com.JiCode.ProductMa.exception.SelectFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OutsideRepositoryImpl implements OutsideRepository {
    @Autowired
    TeamMapper teamMapper;

    // team表：要啥写啥、非正式写法
    @Override
    public String selectTeamNameById(String id) throws SelectFailedException {
        Team team = teamMapper.selectByPrimaryKey(id);
        if (team == null) {
            throw new SelectFailedException("Failed to select team_name by team_id.");
        }
        return team.getTeamName();
    }

}
