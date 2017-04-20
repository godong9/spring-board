package com.board.gd.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

/**
 * Created by gd.godong9 on 2017. 4. 4.
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, QueryDslPredicateExecutor<Post>, QuerydslBinderCustomizer<QPost> {

    @Override
    default public void customize(QuerydslBindings bindings, QPost root) {
        bindings.including(root.id);
        bindings.including(root.user.id);
        bindings.including(root.board.id);
    }
}
