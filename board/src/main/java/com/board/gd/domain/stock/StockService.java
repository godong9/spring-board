package com.board.gd.domain.stock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

/**
 * Created by godong9 on 2017. 5. 7..
 */

@Transactional(readOnly = true)
@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Transactional(readOnly = false)
    public void parseHtmlAndSave() throws IOException {
        String kospiPath = "/board/files/kospi.html";
        saveStockCodeAndName(kospiPath);

        String kodaqPath = "/board/files/kosdaq.html";
        saveStockCodeAndName(kodaqPath);
    }

    @Transactional(readOnly = false)
    public void saveStockCodeAndName(String filePath) throws IOException {
        File file = new File(System.getProperty("user.dir") + filePath);
        Document doc = Jsoup.parse(file, "EUC-KR");

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
