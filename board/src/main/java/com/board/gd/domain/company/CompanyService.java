package com.board.gd.domain.company;

import com.board.gd.domain.stock.StockService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by godong9 on 2017. 5. 14..
 */

@Slf4j
@Transactional(readOnly = true)
@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private StockService stockService;

    @Transactional(readOnly = false)
    public void parseCompanyHtmlAndSave() throws IOException {
        stockService.findAll().forEach(stock -> {
//            String companyInfoPath = "http://finance.daum.net/item/company.daum?code={{codeNum}}&type=21";
            String companyInfoPath = "http://wisefn.stock.daum.net/company/c1020001.aspx?cmp_cd={{codeNum}}&frq=&rpt=";
            companyInfoPath = companyInfoPath.replace("{{codeNum}}", stock.getCode());
            log.info("companyInfoPath: {}", companyInfoPath);
            try {
                saveCompanyData(companyInfoPath, stock.getName());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
    }

    @Transactional(readOnly = false)
    public void saveCompanyData(String uri, String companyName) throws IOException {
        Document doc = Jsoup.connect(uri).get();

        Elements table = doc.select("#cTB201");
        for (Element t: table) {
            Elements homepageTr = t.select("tr:nth-of-type(2)");
            String homepageStr = homepageTr.select("td.c2 > a").text();

            Elements groupTr = t.select("tr:nth-of-type(4)");
            String groupStr = groupTr.select("td.c2").text();

            log.info("Homepage: {}, Group: {}", homepageStr, groupStr);
            companyRepository.save(Company.builder()
                    .companyName(companyName)
                    .companyMail("")
                    .groupName(groupStr)
                    .groupMail("")
                    .homepage(homepageStr)
                    .build());
        }

    }
}
