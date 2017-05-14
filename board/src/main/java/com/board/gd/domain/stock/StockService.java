package com.board.gd.domain.stock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * Created by godong9 on 2017. 5. 7..
 */

@Transactional(readOnly = true)
@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Transactional(readOnly = false)
    public void parseStockHtmlAndSave() throws IOException {
        String kospiPath = "https://www.hdable.co.kr/go.able?linkcd=s03090010P1I1&gubun=0&actionType=G&radiotype=1&findKeyword=&searchGubun=1";
        saveStockCodeAndName(kospiPath);

        String kodaqPath = "https://www.hdable.co.kr/go.able?linkcd=s03090010P1I1&gubun=0&actionType=G&radiotype=2&findKeyword=&searchGubun=1";
        saveStockCodeAndName(kodaqPath);
    }

    @Transactional(readOnly = false)
    public void saveStockCodeAndName(String uri) throws IOException {
        Document doc = Jsoup.connect(uri).get();

        Elements trs = doc.select(".tbScY tr");
        for (Element e: trs) {
            Elements code = e.select("td:nth-of-type(2)");
            if (!code.isEmpty()) {
                Elements name = e.select("td.tL");
                String codeStr = code.text();
                String nameStr = name.text();
                nameStr = nameStr.replaceAll("[#*]", "");

                stockRepository.save(Stock.builder()
                        .name(nameStr)
                        .code(codeStr)
                        .build());
            }
        }
    }
}
