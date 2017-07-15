package com.board.gd.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * Created by gd.godong9 on 2017. 4. 10.
 */

@Data
public class UserResult {
    private Long id;
    private String name;
    private String email;
    private String companyName;
    private Boolean isPaid;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date paidExpiredAt;

    public static UserResult getUserResult(User user, UserRole paidRole) {
        UserResult userResult = new UserResult();
        userResult.setId(user.getId());
        userResult.setName(user.getName());
        userResult.setEmail(user.getEmail());
        userResult.setCompanyName(user.getCompany().getCompanyName());
        userResult.setIsPaid(!Objects.isNull(paidRole));

        if (!Objects.isNull(paidRole)) {
            userResult.setPaidExpiredAt(paidRole.getExpiredAt());
        }
        return userResult;
    }
}
