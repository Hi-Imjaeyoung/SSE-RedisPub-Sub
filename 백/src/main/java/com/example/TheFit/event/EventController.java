package com.example.TheFit.event;

import com.example.TheFit.common.ErrorCode;
import com.example.TheFit.common.TheFitBizException;
import com.example.TheFit.sse.FeedBackNotificationRes;
import com.example.TheFit.sse.SseController;
import com.example.TheFit.user.dto.UserIdPassword;
import com.example.TheFit.user.repo.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    private final SseController sseController;
    private final UserRepository userRepository;
    public EventController(SseController sseController, UserRepository userRepository) {
        this.sseController = sseController;
        this.userRepository = userRepository;
    }

    // SSE를 사용하여 실시간 정보 보내기
    @GetMapping("/calltest1")
    public void callTrainer1(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserIdPassword userIdPassword = userRepository.findByEmail(authentication.getName()).orElseThrow(
                ()->new TheFitBizException(ErrorCode.NOT_FOUND_MEMBER)
        );
        FeedBackNotificationRes feedBackNotificationRes = new FeedBackNotificationRes("test1@naver.com","diet","2024-03-19",userIdPassword.getName());
        sseController.sendLastInfoToTrainer("test1@naver.com",feedBackNotificationRes);
    }

    // redis pub/sub을 통해서 실시간 정보 보내기
    @GetMapping("/redispubsub")
    public void callTrainer2(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserIdPassword userIdPassword = userRepository.findByEmail(authentication.getName()).orElseThrow(
                ()->new TheFitBizException(ErrorCode.NOT_FOUND_MEMBER)
        );
        FeedBackNotificationRes feedBackNotificationRes = new FeedBackNotificationRes("test1@naver.com","diet","2024-03-19",userIdPassword.getName());
        sseController.sendToTrainer("test1@naver.com","test1@naver.com","diet","2024-03-19");
    }

}
