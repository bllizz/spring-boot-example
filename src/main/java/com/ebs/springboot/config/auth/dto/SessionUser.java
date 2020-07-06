package com.ebs.springboot.config.auth.dto;

import com.ebs.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String userNm;
    private String emlAddr;
    private String picture;

    /**
     * @param user
     */
    public SessionUser(User user) {
        this.userNm = user.getUserNm();
        this.emlAddr = user.getEmlAddr();
        this.picture = user.getPicture();
    }
}
