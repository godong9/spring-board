package com.board.gd.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gd.godong9 on 2017. 4. 12.
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, QueryDslPredicateExecutor<Comment>, QuerydslBinderCustomizer<QComment> {
    List<Comment> findByPostId(Long postId);

    @Override
    default public void customize(QuerydslBindings bindings, QComment root) {
        bindings.including(root.id);
        bindings.including(root.post.id);
        bindings.including(root.user.id);
    }
}
