package com.BookStore.repository;

import com.BookStore.dto.AdminReplyFeedbackRequest;
import com.BookStore.dto.DeleteFeedbackRequest;
import com.BookStore.dto.FeedbackRequestDto;
import com.BookStore.dto.FeedbackResponseDto;

import java.util.List;

public interface FeedbackService {
    void submitFeedback(FeedbackRequestDto dto);

    List<FeedbackResponseDto> getFeedbackByUserId(Long userId);
    void deleteFeedback(DeleteFeedbackRequest request);
    void replyToFeedback(AdminReplyFeedbackRequest request);
}
