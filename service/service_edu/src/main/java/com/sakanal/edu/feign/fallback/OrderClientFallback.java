package com.sakanal.edu.feign.fallback;

import com.sakanal.edu.feign.OrderClient;
import org.springframework.stereotype.Component;

@Component
public class OrderClientFallback implements OrderClient {
    @Override
    public Boolean isBuyCourse(String memberId, String courseId) {
        return false;
    }
}
