package com.board.gd.domain.stock;

import com.board.gd.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * @api {get} /stocks Request Get stock list
     * @apiName GetStocksByName
     * @apiGroup Stock
     *
     * @apiDescription 주식 종목 코드 이름으로 조회
     *
     * @apiParam {String} name 종목 이름
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object[]} data 종목 리스트
     * @apiSuccess {Number} data.id 종목 id
     * @apiSuccess {String} data.name 종목 이름
     * @apiSuccess {String} data.code 종목 코드
     */
    @GetMapping("/stocks")
    public ServerResponse getStockList(@RequestParam("name") String name) {
        List<Stock> stockList = stockService.findByName(name);
        return ServerResponse.success(StockResult.getStockResultList(stockList));
    }
}
