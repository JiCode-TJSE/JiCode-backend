//package com.JiCode.ProductDev.application;
//
//import com.JiCode.ProductDev.application.dto.SelectAllBacklogitemDto;
//import com.JiCode.ProductDev.domain.model.BacklogItemAggregation;
//import com.JiCode.ProductDev.domain.repository.BacklogItemRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class BacklogitemApplication {
//    @Autowired
//    BacklogItemRepository backlogItemRepository;
//
//    @Transactional(readOnly = true)
//    public List<SelectAllBacklogitemDto> selectAll(String organizationId){
//        List<BacklogItemAggregation> backlogitemAggregations = backlogItemRepository.selectAll();
//        var ans=new ArrayList<SelectAllBacklogitemDto>();
//        for(BacklogItemAggregation backlogItemAggregation:backlogitemAggregations){
//            if(backlogItemAggregation.getOrganizationId().equals(organizationId)){
//
//            }
//        }
//
//
//    }
//
//}
