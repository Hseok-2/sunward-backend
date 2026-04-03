package sunward_backend.domain.inquiry.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunward_backend.domain.inquiry.dto.InquiryRequest;
import sunward_backend.domain.inquiry.dto.InquiryResponse;
import sunward_backend.domain.inquiry.dto.InquiryStatusRequest;
import sunward_backend.domain.inquiry.service.InquiryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inquiries")
public class InquiryController {

    private final InquiryService inquiryService;

    @PostMapping
    public ResponseEntity<Void> createInquiry(@RequestBody InquiryRequest request) {
        inquiryService.createInquiry(request);
        return ResponseEntity.ok().build();
    }

    // 문의글 조회 - 관리자
    @GetMapping
    public ResponseEntity<Page<InquiryResponse>> getInquiries(
            @RequestParam(defaultValue = "0") int page
    ) {
        Page<InquiryResponse> response = inquiryService.getInquiries(page);
        return ResponseEntity.ok(response);
    }

    // 문의글 상태 변경
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateInquiryStatus(
            @PathVariable Long id,
            @RequestBody InquiryStatusRequest request
    ) {
       inquiryService.updateInquiryStatus(id, request.getStatus());
       return ResponseEntity.ok().build();
    }
}
