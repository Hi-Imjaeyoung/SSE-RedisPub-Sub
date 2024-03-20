package com.example.TheFit.sse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class SseEmittersService {
    private final EmitterRepository emitterRepository;

    public SseEmittersService(EmitterRepository emitterRepository) {
        this.emitterRepository = emitterRepository;
    }

    SseEmitter add(String email,SseEmitter emitter){
        // 현재 저장된 emitter의 수를 조회하여 자동 삭제를 확인
        //System.out.println(emitterRepository.getEmitterSize());
        emitterRepository.save(email,emitter);
        /*
        Register code to invoke when the async request completes.
        This method is called from a container thread when an async request completed for any reason including timeout and network error.
        This method is useful for detecting that a ResponseBodyEmitter instance is no longer usable.
        */
        emitter.onCompletion(()->{
            // 만일 emitter가 만료되면 삭제한다.
            // System.out.println(email);
            emitterRepository.deleteByEmail(email);
        });
        /*
        Register code to invoke when the async request times out. This method is called from a container thread when an async request times out.
         */
        emitter.onTimeout(()->{
            emitterRepository.get(email).complete();
        });
        return emitter;
    }

    SseEmitter get(String email){
        return emitterRepository.get(email);
    }

    boolean containKey(String email){
        return emitterRepository.containKey(email);
    }
}
