package com.ebs.springboot.domain.user;

import com.ebs.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userNm;

    @Column(nullable = false)
    private String emlAddr;

    @Column
    private String picture;

    @Column
    private String gender;

    @Column
    private String birthday;

    @Column(nullable = false)
    private String snsId;

    @Column
    private String snsSiteCd;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String userNm, String emlAddr, String picture, String gender, String birthday, Role role, String snsId, String snsSiteCd) {
        this.userNm = userNm;
        this.emlAddr = emlAddr;
        this.picture = picture;
        this.gender = gender;
        this.birthday = birthday;
        this.role = role;
        this.snsId = snsId;
        this.snsSiteCd = snsSiteCd;
    }

    public User update(String userNm, String picture) {
        this.userNm = userNm;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
