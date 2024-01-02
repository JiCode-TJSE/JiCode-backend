package com.JiCode.Account.adaptor.in;

import com.JiCode.Account.application.OrganizationApplication;
import com.JiCode.Account.application.dto.OrganizationDto;
import com.JiCode.Account.util.ComResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    @Autowired
    OrganizationApplication organizationApplication;

    @GetMapping("/organizationinfo")
    public ComResponse<OrganizationDto> getOrganizationInfo(@RequestParam("organizationId") String organizationId) {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto = organizationApplication.selectByOrganizationId(organizationId);
        if (organizationDto == null) {
            return ComResponse.error("organization not found");
        }
        return ComResponse.success(organizationDto);
    }
}
