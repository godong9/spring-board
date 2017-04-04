package com.board.gd.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResult {
    @Autowired
    private ModelMapper modelMapper;

    private String status;
    private String msg;
    private User user;

    public static UserResult from(User user, String msg) {
        return UserResult.builder()
                .status(HttpStatus.OK.toString())
                .user(user)
                .msg(msg)
                .build();
    }

    public static UserResult from(HttpStatus status, String msg) {
        return UserResult.builder()
                .status(status.toString())
                .msg(msg)
                .build();
    }
}
