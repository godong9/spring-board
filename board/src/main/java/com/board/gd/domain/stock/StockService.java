package com.board.gd.domain.stock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Stock findOne(Long id) {
        return stockRepository.findOne(id);
    }

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public List<Stock> findByName(String name) {
        String sql = "SELECT * FROM stocks WHERE MATCH(name) AGAINST(?)";
        return jdbcTemplate.query(sql, new String[] {name}, new BeanPropertyRowMapper(Stock.class));
    }

    @Transactional(readOnly = false)
    public Stock create(StockDto stockDto) {
        return stockRepository.save(Stock.builder()
                .name(stockDto.getName())
                .code(stockDto.getCode())
                .build());
    }

    @Transactional(readOnly = false)
    public void parseStockHtmlAndSave() throws IOException {
        String kospiPath = "https://www.kbsec.com/go.able?linkcd=s03090010P1I1&gubun=0&actionType=G&radiotype=1&findKeyword=&searchGubun=1";
        saveStockCodeAndName(kospiPath);

        String kodaqPath = "https://www.kbsec.com/go.able?linkcd=s03090010P1I1&gubun=0&actionType=G&radiotype=2&findKeyword=&searchGubun=1";
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

    @Transactional(readOnly = false)
    public void deleteAll() {
        stockRepository.deleteAll();
    }
}
