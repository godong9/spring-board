package com.board.gd.domain.user;

import lombok.Data;

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

    public static UserResult getUserResult(User user, Boolean isPaid) {
        UserResult userResult = new UserResult();
        userResult.setId(user.getId());
        userResult.setName(user.getName());
        userResult.setEmail(user.getEmail());
        userResult.setCompanyName(user.getCompany().getCompanyName());
        userResult.setIsPaid(isPaid);
        return userResult;
    }
}
