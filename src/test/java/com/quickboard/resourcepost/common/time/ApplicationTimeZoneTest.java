package com.quickboard.resourcepost.common.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.TimeZone;

//todo 타임존 테스트: 나중에 무거워지면 테스트 삭제 or 다른 방법 찾기
@SpringBootTest
public class ApplicationTimeZoneTest {
    @Test
    void timeZone() {
        Assertions.assertEquals("UTC", TimeZone.getDefault().getID());
    }
}
