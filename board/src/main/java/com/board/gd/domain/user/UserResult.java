package com.board.gd.domain.user;

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
    private String message;
    private JsonUser user;

    public static UserResult from(User user, String message) {
        JsonUser jsonUser = new JsonUser();
        jsonUser.setId(user.getId());
        jsonUser.setName(user.getName());

        return UserResult.builder()
                .status(HttpStatus.OK.toString())
                .user(jsonUser)
                .message(message)
                .build();
    }

    public static UserResult from(HttpStatus status, String message) {
        return UserResult.builder()
                .status(status.toString())
                .message(message)
                .build();
    }
}
