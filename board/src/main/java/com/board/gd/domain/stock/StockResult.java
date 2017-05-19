package com.board.gd.domain.stock;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gd.godong9 on 2017. 5. 19.
 */

@Data
public class StockResult {
    private Long id;
    private String name;
    private String code;

    public static StockResult getStockResult(Stock stock) {
        StockResult stockResult = new StockResult();
        stockResult.setId(stock.getId());
        stockResult.setName(stock.getName());
        stockResult.setCode(stock.getCode());

        return stockResult;
    }

    public static List<StockResult> getStockResultList(List<Stock> stockList) {
        return stockList.stream()
                .map(stock -> getStockResult(stock))
                .collect(Collectors.toList());
    }
}
