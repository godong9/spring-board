package com.board.gd.domain.comment;

import com.board.gd.domain.post.Post;
import com.board.gd.domain.post.QPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;

/**
 * Created by gd.godong9 on 2017. 4. 12.
 */
public interface CommentRepository extends JpaRepository<Comment, Long>, QueryDslPredicateExecutor<Comment>, QuerydslBinderCustomizer<QComment> {
    List<Comment> findByPostId(Long postId);

    @Override
    default public void customize(QuerydslBindings bindings, QComment root) {
        bindings.including(root.id);
        bindings.including(root.post.id);
        bindings.including(root.user.id);
    }
}
