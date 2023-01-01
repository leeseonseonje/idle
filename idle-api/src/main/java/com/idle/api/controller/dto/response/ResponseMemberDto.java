package com.idle.api.controller.dto.response;

import com.idle.member.Member;

public record ResponseMemberDto(
        Long memberId,

        String nickname,

        String accessToken
) {
    public static ResponseMemberDto toDto(Member member) {
        return new ResponseMemberDto(member.getId(), member.getNickname(), member.getAccessToken());
    }
}
