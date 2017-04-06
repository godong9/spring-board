package com.board.gd.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResult {
    private String status;
    private String msg;
    private JsonUser user;

    @Data
    private static class JsonUser {
        private Long id;
        private String name;
    }

    public static UserResult from(User user, String msg) {
        JsonUser jsonUser = new JsonUser();
        jsonUser.setId(user.getId());
        jsonUser.setName(user.getName());

        return UserResult.builder()
                .status(HttpStatus.OK.toString())
                .user(jsonUser)
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
