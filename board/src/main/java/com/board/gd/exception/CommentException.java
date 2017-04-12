package com.board.gd.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by gd.godong9 on 2017. 4. 12.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentException extends RuntimeException {
    private String message = "댓글 처리 중 에러가 발생하였습니다.";
}
