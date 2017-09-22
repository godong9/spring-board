package com.board.gd.domain.company;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by godong9 on 2017. 5. 14..
 */

@Data
public class CompanyResult {
    private Long id;
    private String groupName;
    private String companyName;

    public static CompanyResult getCompanyResult(Company company) {
        CompanyResult companyResult = new CompanyResult();
        companyResult.setId(company.getId());
        companyResult.setGroupName(company.getGroupName());
        companyResult.setCompanyName(company.getCompanyName());

        return companyResult;
    }

    public static List<CompanyResult> getCompanyResultList(List<Company> companyList) {
        return companyList.stream()
                .map(company -> getCompanyResult(company))
                .collect(Collectors.toList());
    }
}
