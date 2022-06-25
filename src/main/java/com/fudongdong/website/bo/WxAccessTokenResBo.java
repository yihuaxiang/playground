package com.fudongdong.website.bo;

import lombok.Data;

/**
 * @author dongdong.fdd
 * @date 2022/6/25 22:26
 */
@Data
public class WxAccessTokenResBo extends WxResBo {
    /**
     * access_token
     */
    private String access_token;

    /**
     * 过期时间，单位：秒
     */
    private Long expires_in;
}
