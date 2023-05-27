package com.example.RedisNotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;

import java.util.HashMap;
import java.util.Map;

// OrderService에서 발생한 이벤트를 받아서 처리
public class OrderEventStreamListener implements StreamListener<String , MapRecord<String, String, String>> {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        // 엔트리의 필드와 벨류를 맵으로 받음
        Map<String, String> map = message.getValue();

        String userId = (String) map.get("userId");
        String productId = (String) map.get("productId");

        // 주문 건에 대한 메일 발송 처리
        System.out.println("[Order consumed] userId: "+userId+" productId: "+productId);
    }
}
