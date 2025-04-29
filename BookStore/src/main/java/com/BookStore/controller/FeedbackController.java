package com.BookStore.controller;

import com.BookStore.dto.AdminReplyFeedbackRequest;
import com.BookStore.dto.DeleteFeedbackRequest;
import com.BookStore.dto.FeedbackRequestDto;
import com.BookStore.dto.FeedbackResponseDto;
import com.BookStore.repository.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/submit")
    public String submitFeedback(@RequestBody FeedbackRequestDto dto) {
        feedbackService.submitFeedback(dto);
        return "反馈已提交";
    }

    @GetMapping("/user/{userId}")
    public List<FeedbackResponseDto> getUserFeedback(@PathVariable Long userId) {
        return feedbackService.getFeedbackByUserId(userId);
    }
    @DeleteMapping("/delete")
    public String deleteFeedback(@RequestBody DeleteFeedbackRequest request) {
        feedbackService.deleteFeedback(request);
        return "删除成功";
    }

    @PostMapping("/adminReply")
    public String adminReplyFeedback(@RequestBody AdminReplyFeedbackRequest request) {
        feedbackService.replyToFeedback(request);
        return "回复成功";
    }
}
