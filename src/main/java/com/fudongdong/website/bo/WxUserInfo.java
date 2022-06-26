package com.fudongdong.website.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信用户信息，目前只使用 openid
 * @author dongdong.fdd
 * @date 2022/6/26 20:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WxUserInfo {
    private String openid;
}
