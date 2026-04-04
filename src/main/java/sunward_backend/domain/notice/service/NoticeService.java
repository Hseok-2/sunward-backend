package sunward_backend.domain.notice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunward_backend.domain.notice.dto.NoticeRequest;
import sunward_backend.domain.notice.dto.NoticeResponse;
import sunward_backend.domain.notice.entity.Notice;
import sunward_backend.domain.notice.repository.NoticeRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;

    // 공지사항 목록 조회 (페이징 정렬 + 검색 + 상단 고정 정렬)
    public Page<NoticeResponse> getNotices(String keyWord, int page) {
        // 정렬 조건: 1순위 - 상단고정, 2순위 - 최신순
        Sort sort = Sort.by(
                Sort.Order.desc("isPinned"),
                Sort.Order.desc("createdAt")
        );
        PageRequest pageable = PageRequest.of(page, 10, sort);

        Page<Notice> noticepage;

        if(keyWord != null && !keyWord.trim().isEmpty()) {
            noticepage = noticeRepository.findByTitleContaining(keyWord, pageable);
        } else {
            noticepage = noticeRepository.findAll(pageable);
        }

        return noticepage.map(NoticeResponse::new);
    }

    // 공지사항 상세 조회 (조회수 증가)
    @Transactional
    public NoticeResponse getNoticeDetail(Long id) {
        Notice notice = validNotice(id);
        // 조회수 증가
        notice.incrementViewCount();
        return new NoticeResponse(notice);
    }

    // 공지사항 등록 - 관리자
    @Transactional
    public void createNotice(NoticeRequest request) {
        Notice notice = Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .isPinned(request.isPinned())
                .build();
        noticeRepository.save(notice);
    }

    // 공지사항 수정 - 관리자
    @Transactional
    public void updateNotice(Long id, NoticeRequest request) {
        Notice notice = validNotice(id);
        notice.updateNotice(request.getTitle(), request.getContent(), request.isPinned());
    }

    // 공지사항 삭제 - 관리자
    @Transactional
    public void deleteNotice(Long id) {
        Notice notice = validNotice(id);
        noticeRepository.delete(notice);
    }

    // 공통 valid 처리 메서드
    private Notice validNotice(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 없습니다. id: " + id));
        return notice;
    }
}
