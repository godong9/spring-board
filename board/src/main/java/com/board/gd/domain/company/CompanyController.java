package com.board.gd.domain.company;

import com.board.gd.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by godong9 on 2017. 5. 14..
 */

@Slf4j
@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies/parse")
    public ServerResponse getCompanyParse() {
        try {
            companyService.parseCompanyHtmlAndSave();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ServerResponse.success();
    }

    /**
     * @api {get} /companies Request Get companies by email
     * @apiName GetCompaniesByEmail
     * @apiGroup Company
     *
     * @apiParam {String} email 유저 이메일
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object[]} data 회사 리스트
     * @apiSuccess {Number} data.id 회사 id
     * @apiSuccess {String} data.group_name 회사 그룹 이름
     * @apiSuccess {String} data.company_name 회사 이름
     *
     * @apiSampleRequest http://localhost:9700/companies?email=test@test.com
     *
     * @apiUse BadRequestError
     */
    @GetMapping("/companies")
    public ServerResponse getUserCompanyList(@RequestParam("email") String email) {
        List<Company> companyList = companyService.getCompaniesByEmail(email);
        return ServerResponse.success(CompanyResult.getCompanyResultList(companyList));
    }
}
