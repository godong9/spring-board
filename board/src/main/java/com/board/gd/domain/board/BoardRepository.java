package com.board.gd.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

/**
 * Created by gd.godong9 on 2017. 4. 19.
 */

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, QueryDslPredicateExecutor<Board>, QuerydslBinderCustomizer<QBoard> {

    @Override
    default public void customize(QuerydslBindings bindings, QBoard root) {
        bindings.including(root.id);
    }
}
