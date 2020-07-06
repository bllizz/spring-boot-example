package com.ebs.springboot.config.auth.dto;

import com.ebs.springboot.domain.user.Role;
import com.ebs.springboot.domain.user.SnsType;
import com.ebs.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String userNm;
    private String emlAddr;
    private String picture;
    private String gender;
    private String birthday;
    private String snsId;
    private String snsSiteCd;

    /**
     * @param attributes
     * @param nameAttributeKey
     * @param userNm
     * @param emlAddr
     * @param picture
     */
    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String userNm, String emlAddr, String picture, String gender, String birthday, String snsId, String snsSiteCd) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.userNm = userNm;
        this.emlAddr = emlAddr;
        this.picture = picture;
        this.gender = gender;
        this.birthday = birthday;
        this.snsId = snsId;
        this.snsSiteCd = snsSiteCd;
    }

    /**
     * @param registrationId
     * @param userNameAttributeName
     * @param attributes
     * @return
     */
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes, registrationId);

        }
        return ofGoogle(userNameAttributeName, attributes, registrationId);
    }

    /**
     * @param userNameAttributeName
     * @param attributes
     * @return
     */
    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes, String registrationId) {
        return OAuthAttributes.builder()
                .userNm((String) attributes.get("name"))
                .emlAddr((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .snsId((String) attributes.get("sub"))
                .snsSiteCd(SnsType.valueOf(registrationId).getValue())
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes, String registrationId) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .userNm((String) response.get("name"))
                .emlAddr((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .gender((String) response.get("gender"))
                .birthday((String) response.get("birthday"))
                .snsId((String) response.get("id"))
                .snsSiteCd(SnsType.valueOf(registrationId).getValue())
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    /**
     * @return
     */
    public User toEntity() {
        return User.builder()
                .userNm(userNm)
                .emlAddr(emlAddr)
                .picture(picture)
                .gender(gender)
                .birthday(birthday)
                .snsId(snsId)
                .snsSiteCd(snsSiteCd)
                .role(Role.GUEST)
                .build();
    }
}
