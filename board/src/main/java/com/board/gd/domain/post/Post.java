package com.board.gd.domain.post;

import com.board.gd.domain.board.Board;
import com.board.gd.domain.user.User;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by gd.godong9 on 2017. 4. 4.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "posts")
@EntityListeners({AuditingEntityListener.class})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PostType type;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "comment_count")
    private Long commentCount;

    @Column(name = "post_like_count")
    private Long postLikeCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = true)
    private Board board;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Transient
    private Boolean isLiked = false;
}
