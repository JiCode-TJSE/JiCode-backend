package com.JiCode.ProductMa.application.Impl;

import com.JiCode.ProductMa.application.ProductApplication;
import com.JiCode.ProductMa.application.dto.AllProductsDto;
import com.JiCode.ProductMa.application.dto.ProductResponseDto;
import com.JiCode.ProductMa.application.dto.ProductRequestDto;
import com.JiCode.ProductMa.domain.model.ProductAggregation;
import com.JiCode.ProductMa.domain.repository.OutsideRepository;
import com.JiCode.ProductMa.domain.repository.ProductRepository;
import com.JiCode.ProductMa.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductApplicationImpl implements ProductApplication {

    private static final Logger log = LoggerFactory.getLogger(RequirementApplicationImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OutsideRepository outsideRepository;


    /**
     * 新建产品
     * @param productRequestDto
     * @return
     * @throws ServerException
     */
    @Transactional
    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) throws ServerException {
        // 新建聚合
        ProductAggregation productAggregation = new ProductAggregation();

        // 复制同名属性值：id、title、detail、mark（product_id还有）
        BeanUtils.copyProperties(productRequestDto, productAggregation);
        //设置非同名team_id
        productAggregation.setTeamId(productRequestDto.getTeam_id());

        // product表添加脏标记
        productAggregation.setProductDirty(true);
        //这个暂时不做，默认为true
        productAggregation.setVisibility(true);

        try {
            String product_id = productRepository.insert(productAggregation);
            productAggregation.setId(product_id);
        } catch (InsertFailedException e) {
            log.error("Server Error(createProduct): failed to insert product aggregation ", e);
            throw new ServerException(e);
        }

        //返回数据结果
        ProductResponseDto response = new ProductResponseDto();
        BeanUtils.copyProperties(productRequestDto, response);
        try {
            //获取团队名
            String team_name = outsideRepository.selectTeamNameById(productAggregation.getTeamId());
            response.setTeam_name(team_name);
            response.setId(productAggregation.getId());
            return response;
        } catch (SelectFailedException e) {
            log.error("Server Error(createProduct): failed to select team_name by team_id ", e);
            throw new ServerException(e);
        }
    }




    /**
     * 按productId获取产品详情
     * @param productId
     * @return
     * @throws ServerException
     */
    @Transactional(readOnly = true)
    @Override
    public ProductResponseDto getProductDetail(String productId) throws ServerException {
        ProductAggregation productAggregation;
        try {
            productAggregation = productRepository.selectById(productId);
        } catch (SelectFailedException e) {
            log.error("Server Error(getProductDetail): failed to select product by product_id", e);
            throw new ServerException(e);
        }
        ProductResponseDto response = new ProductResponseDto();
        //同名属性赋值：id/title/detail/mark
        BeanUtils.copyProperties(productAggregation, response);
        //获取不同名的team_id
        response.setTeam_id(productAggregation.getTeamId());
        //根据team_id查询team_name
        try {
            String team_name = outsideRepository.selectTeamNameById(productAggregation.getTeamId());
            response.setTeam_name(team_name);
            return response;
        } catch (SelectFailedException e) {
            log.error("Server Error(getProductDetail): failed to select team_name by team_id ", e);
            throw new ServerException(e);
        }
    }




    /**
     * 删除产品（按productId）
     * @param productId
     * @throws ServerException
     */
    @Transactional
    @Override
    public void deleteProduct(String productId) throws ServerException {
        try {
            productRepository.delete(productId);
        } catch (DeleteFailedException e) {
            log.error("Server Error", e);
            throw new ServerException();
        }
    }



    /**
     * 更新产品信息
     * @param request
     * @return
     * @throws ServerException
     */
    @Transactional
    @Override
    public ProductResponseDto updateProduct(ProductRequestDto request) throws ServerException {
        ProductAggregation productAggregation = new ProductAggregation();

        try {
            productAggregation = productRepository.selectById(request.getId());
        } catch (SelectFailedException e) {
            log.error("Server Error(updateProduct): failed to select product by id ", e);
            throw new ServerException(e);
        }

        //复制同名属性：request->productAggregation（id/title/detail/mark）
        BeanUtils.copyProperties(request, productAggregation);
        //设置不同名属性:teamId
        productAggregation.setTeamId(request.getTeam_id());

        try {
            productAggregation.setProductDirty(true);
            productRepository.update(productAggregation);
        } catch (UpdateFailedException e) {
            log.error("Server Error(updateProduct): failed to select product by id ", e);
            throw new ServerException(e);
        } catch (InsertFailedException e) {
            log.error("Server Error(updateProduct): failed to select product by id ", e);
            throw new ServerException(e);
        }

        //返回响应
        ProductResponseDto response = new ProductResponseDto();
        //复制同名属性
        BeanUtils.copyProperties(request, response);
        //设置team_name
        try {
            String team_name = outsideRepository.selectTeamNameById(request.getTeam_id());
            response.setTeam_name(team_name);
            return response;
        } catch (SelectFailedException e) {
            log.error("Server Error(updateProduct): failed to select team_name by team_id ", e);
            throw new ServerException(e);
        }
    }


    /**
     * 根据账号ID返回可见产品列表
     * @param accountId
     * @return
     * @throws ServerException
     */
    @Override
    public AllProductsDto getAllProductsByAccountId(String accountId) throws ServerException {
        List<String> productIds = new ArrayList<>();
        try {
            productIds = productRepository.selectByAccountId(accountId);
        } catch (SelectFailedException e) {
            log.error("Server Error", e);
            throw new ServerException(e);
        }

        //返回响应
        AllProductsDto result = new AllProductsDto();
        List<ProductResponseDto> records = new ArrayList<>();

        for ( String productId : productIds){
            records.add(getProductDetail(productId));
        }

        result.setRecords(records.toArray(new ProductResponseDto[records.size()]));
        result.setTotal(records.size());

        return result;
    }
}
