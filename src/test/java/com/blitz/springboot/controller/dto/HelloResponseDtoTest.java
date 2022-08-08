package com.blitz.springboot.controller.dto;


import com.blitz.springboot.service.dto.HelloResponseDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() throws Exception {
        // given
        String name = "TEST";
        int amount = 1000000000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

    }
}