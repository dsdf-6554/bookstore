package com.BookStore.service;

import com.BookStore.dto.AdminReplyFeedbackRequest;
import com.BookStore.dto.DeleteFeedbackRequest;
import com.BookStore.dto.FeedbackRequestDto;
import com.BookStore.dto.FeedbackResponseDto;
import com.BookStore.entity.Admin;
import com.BookStore.entity.Feedback;
import com.BookStore.entity.User;
import com.BookStore.repository.AdminRepository;
import com.BookStore.repository.FeedbackRepository;
import com.BookStore.repository.UserRepository;
import com.BookStore.repository.FeedbackService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;
    private final AdminRepository adminRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, AdminRepository adminRepository) {
        this.feedbackRepository = feedbackRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    @Transactional
    public void deleteFeedback(DeleteFeedbackRequest request) {
        Feedback feedback = feedbackRepository.findById(request.getFeedbackId())
                .orElseThrow(() -> new RuntimeException("反馈不存在"));
        if (!feedback.getUser().getUserId().equals(request.getUserId())) {
            throw new RuntimeException("无权限删除该反馈");
        }
        feedbackRepository.delete(feedback);
    }

    @Override
    @Transactional
    public void replyToFeedback(AdminReplyFeedbackRequest request) {
        Feedback reply = new Feedback();
        Feedback original = feedbackRepository.findById(request.getFeedbackId())
                .orElseThrow(() -> new RuntimeException("原始反馈不存在"));

        Admin admin = adminRepository.findById(request.getAdminId())
                .orElseThrow(() -> new RuntimeException("管理员不存在"));

        reply.setUser(original.getUser()); // 回复给该用户
        reply.setFeedbackContent("【管理员回复】：" + request.getReplyContent());
        reply.setFeedbackTime(new Date());

        feedbackRepository.save(reply);
    }
    @Override
    public void submitFeedback(FeedbackRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setFeedbackContent(dto.getFeedbackContent());
        feedback.setFeedbackTime(new Date());

        feedbackRepository.save(feedback);
    }

    @Override
    public List<FeedbackResponseDto> getFeedbackByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        List<Feedback> feedbackList = feedbackRepository.findByUser(user);

        return feedbackList.stream().map(feedback -> {
            FeedbackResponseDto dto = new FeedbackResponseDto();
            dto.setFeedbackId(feedback.getFeedbackId());
            dto.setUserId(userId);
            dto.setFeedbackContent(feedback.getFeedbackContent());
            dto.setFeedbackTime(feedback.getFeedbackTime());
            return dto;
        }).collect(Collectors.toList());
    }
}
