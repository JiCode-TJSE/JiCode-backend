package com.JiCode.ProductMa.adaptor.output.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BacklogItemNamesReqDto {
    String[] backlogitemIds;
}
