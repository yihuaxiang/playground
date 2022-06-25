package com.fudongdong.website.bo;

import lombok.Data;

/**
 * @author dongdong.fdd
 * @date 2022/6/25 22:26
 */
@Data
public class WxJsapiTicketResBo extends WxResBo {
    /**
     * jsapi ticket
     */
    private String ticket;

    /**
     * 过期时间，单位：秒
     */
    private Long expires_in;
}
