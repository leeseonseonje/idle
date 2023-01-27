package com.idle.oauth.api;

import com.idle.oauth.api.dto.ResponseToken;
import com.idle.oauth.api.dto.ResponseUserId;

public interface OauthLoginApi {

    ResponseToken getToken(String code);

    ResponseUserId getMember(String type, String accessToken);

    ResponseToken tokenReissue(String refreshToken);
}
