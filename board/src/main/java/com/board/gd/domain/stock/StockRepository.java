package com.board.gd.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by godong9 on 2017. 5. 7..
 */

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
