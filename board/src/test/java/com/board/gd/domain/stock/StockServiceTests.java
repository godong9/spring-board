package com.board.gd.domain.stock;

import com.board.gd.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by gd.godong9 on 2017. 5. 19.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTests {
    @Autowired
    private StockService stockService;

    @Before
    public void setUp() {
        stockService.deleteAll();
        stockService.create(TestHelper.getTestStockDto("삼성전자", "00001"));
        stockService.create(TestHelper.getTestStockDto("삼성SDS", "00002"));
    }

    @Test
    public void success_findByName_when_name_exists() {
        // given
        String name = "삼성";

        // when
        List<Stock> stockList = stockService.findByName(name);

        // then
        assertThat(stockList.size(), is(2));
    }

    @Test
    public void success_findByName_when_name_not_exists() {
        // given
        String name = "LG";

        // when
        List<Stock> stockList = stockService.findByName(name);

        // then
        assertThat(stockList.size(), is(0));
    }
}
