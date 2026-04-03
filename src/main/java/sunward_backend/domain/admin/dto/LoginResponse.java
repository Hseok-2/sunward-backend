package sunward_backend.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {

    private String grantType; // "Bearer"
    private String accessToken; // 실제 API 호출시 사용하는 짧은 유효시간의 토큰
    private String refreshToken; // AccessToken 만료시 재발급을 위한 긴 유효시간의 토큰
}
