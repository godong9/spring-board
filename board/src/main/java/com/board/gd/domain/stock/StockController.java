package com.board.gd.domain.stock;

import com.board.gd.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by godong9 on 2017. 5. 7..
 */

@Slf4j
@RestController
public class StockController {
    @Autowired
    private StockService stockService;

    /**
     * @api {get} /stocks/parse Request stock parse
     * @apiName ParseStocks
     * @apiGroup Stock
     *
     * @apiDescription 주식 종목 코드, 이름 정보 파싱해서 저장
     */
    @GetMapping("/stocks/parse")
    public ServerResponse getStockParse() {
        try {
            stockService.parseStockHtmlAndSave();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ServerResponse.success();
    }
}
