package sunward_backend.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    // spring security는 권한 코드에 항상 "ROLE_" 접두사가 붙는 것을 기본 규칙으로 함.
    USER("ROLE_USER", "일반 사용자"),
    ADMIN("ROLE_ADMIN", "시스템 관리자");

    private final String key;
    private final String title;
}
