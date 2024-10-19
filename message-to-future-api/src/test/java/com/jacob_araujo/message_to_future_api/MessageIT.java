package com.jacob_araujo.message_to_future_api;

import com.jacob_araujo.message_to_future_api.web.dto.MessageCreateDto;
import com.jacob_araujo.message_to_future_api.web.dto.MessageResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/messages/messages-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/messages/messages-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MessageIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void createMessage_validData_returnMessageCreated201(){
        MessageResponseDto responseBody = testClient
                .post()
                .uri("/api/v1/messages")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@email.com", "123456"))
                .bodyValue(new MessageCreateDto("Oi", "Bob", "16/06/2025 14:00:00", "Rocket"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(MessageResponseDto.class)
                .returnResult()
                .getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getMessageText()).isEqualTo("Oi");
        org.assertj.core.api.Assertions.assertThat(responseBody.getRecipientName()).isEqualTo("Bob");
        org.assertj.core.api.Assertions.assertThat(responseBody.getNarrativeTheme()).isEqualTo("Rocket");
        org.assertj.core.api.Assertions.assertThat(responseBody.getOpeningDateTime()).isEqualTo(LocalDateTime.of(2025, 6, 16, 14, 0, 0));
        org.assertj.core.api.Assertions.assertThat(responseBody.getSenderUser()).isEqualTo("ana@email.com");
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatusMessage()).isEqualTo("PENDING");

    }
}