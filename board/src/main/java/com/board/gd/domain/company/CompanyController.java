package com.board.gd.domain.company;

import com.board.gd.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
