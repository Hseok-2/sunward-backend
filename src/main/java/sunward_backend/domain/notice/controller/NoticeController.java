package sunward_backend.domain.notice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunward_backend.domain.notice.dto.NoticeRequest;
import sunward_backend.domain.notice.dto.NoticeResponse;
import sunward_backend.domain.notice.service.NoticeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeController {

    private final NoticeService noticeService;

    // 공지사항 목록 조회
    @Operation(summary = "공지사항 목록 전체 조회")
    @GetMapping
    public ResponseEntity<Page<NoticeResponse>> getNotices(
            @RequestParam(required = false) String keyWord,
            @RequestParam(defaultValue = "0") int page) {

        Page<NoticeResponse> response = noticeService.getNotices(keyWord, page);
        return ResponseEntity.ok(response);
    }

    // 공지사항 상세 조회
    @Operation(summary = "공지사항 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponse> getNoticeDetail(@PathVariable Long id) {
        NoticeResponse response = noticeService.getNoticeDetail(id);
        return ResponseEntity.ok(response);
    }

    // 공지사항 등록 - 관리자
    @Operation(summary = "공지사항 등록")
    @PostMapping
    public ResponseEntity<Void> createNotice(@RequestBody NoticeRequest request) {
        noticeService.createNotice(request);
        return ResponseEntity.ok().build();
    }

    // 공지사항 수정 - 관리자
    @Operation(summary = "공지사항 수정")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNotice(@PathVariable Long id, @RequestBody NoticeRequest request) {
        noticeService.updateNotice(id, request);
        return ResponseEntity.ok().build();
    }

    // 공지사항 삭제 - 관리자
    @Operation(summary = "공지사항 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.ok().build();
    }
}

