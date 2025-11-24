package com.example.elisaappbe.service.englishNotification;

import com.example.elisaappbe.dto.req.EnglishNotificationRequest;
import com.example.elisaappbe.dto.resp.EnglishNotificationResponse;
import com.example.elisaappbe.model.EnglishNotification;
import com.example.elisaappbe.model.User;
import com.example.elisaappbe.repository.EnglishNotificationRepository;
import com.example.elisaappbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnglishNotificationServiceImpl implements EnglishNotificationService{

    @Autowired
    private EnglishNotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    private EnglishNotificationResponse toResponse(EnglishNotification vocab) {
        EnglishNotificationResponse resp = new EnglishNotificationResponse();
        resp.setNotificationId(vocab.getNotificationId());
        resp.setTitle(vocab.getTitle());
        resp.setContent(vocab.getContent());
        resp.setImageUrl(vocab.getImageUrl());
        resp.setSendTime(vocab.getSendTime());
        resp.setType(vocab.getType());
        resp.setIsRead(vocab.getIsRead());
        return resp;
    }

    @Override
    public List<EnglishNotificationResponse> getNotificationByUserId(long userId) {
        return notificationRepository.findByUser_UserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EnglishNotificationResponse createNotification(EnglishNotificationRequest res) {
        EnglishNotification newNotification = new EnglishNotification();
        Optional<User> getUser = userRepository.findById(res.getUserId());
        if(getUser.isPresent()){
            newNotification.setTitle(res.getTitle());
            newNotification.setContent(res.getContent());
            newNotification.setImageUrl(res.getImageUrl());
            newNotification.setTitle(res.getTitle());
            newNotification.setSendTime(LocalDateTime.now());
            newNotification.setType(res.getType());
            newNotification.setIsRead(false);
            newNotification.setUser(getUser.get());
            EnglishNotification saved = notificationRepository.save(newNotification);
            return new EnglishNotificationResponse(
                    saved.getNotificationId(),
                    saved.getTitle(),
                    saved.getContent(),
                    saved.getImageUrl(),
                    saved.getSendTime(),
                    saved.getType(),
                    saved.getIsRead()
            );
        }
        return null;
    }

    @Override
    @Transactional
    public List<EnglishNotificationResponse> updateIsRead(long userId) {
        notificationRepository.markAllAsRead(userId);
        return getNotificationByUserId(userId);
    }


}
