package sunward_backend.global.error;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 에러 응답용 DTO
 */
@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timeStamp;
    private final int status; // http 상태 코드
    private final String error; // 에러 종류
    private final String message; // 에러 메세지
}
