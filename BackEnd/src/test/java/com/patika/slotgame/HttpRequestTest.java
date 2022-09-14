package com.patika.slotgame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patika.slotgame.auth.AuthException;
import com.patika.slotgame.auth.AuthResponse;
import com.patika.slotgame.game.dto.GameDto;
import com.patika.slotgame.user.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HttpRequestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static String token;

    @Test
    @Order(1)
    void registerShouldHaveSuccessMessage() throws Exception {

        User user = new User();
        user.setUsername("Yunus");
        user.setPassword("123456");
        user.setCredits(100);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"" + user.getUsername() + "\",\"password\":\"" + user.getPassword() + "\",\"credits\":\"" + user.getCredits() + "\"}");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"message\":\"User Created Successfully\"}"));

    }

    @Test
    @Order(2)
    void loginShouldHaveToken() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"Yunus\",\"password\":\"123456\"}");

        var mockResponse = mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.token").isString())
                .andExpect(jsonPath("$.user.username").value("Yunus"))
                .andReturn().getResponse();
        token = objectMapper.readValue(mockResponse.getContentAsString(), AuthResponse.class).getToken();
    }

    @Test
    @Order(3)
    void invalidCredentialsShouldReturnUnauthorized() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"Yunus\",\"password\":\"12345\"}");

        mockMvc.perform(mockRequest)
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(4)
    void playShouldChangeCredit() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/play")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"Yunus\"}");

        int credit = 10;
        for (int i = 0; i < 2; i++) {
            var mockResponse = mockMvc.perform(mockRequest)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.userCredits").exists())
                    .andReturn().getResponse();
            credit = objectMapper.readValue(mockResponse.getContentAsString(), GameDto.class).getUserCredits();

        }
        assertThat(credit).isNotEqualTo(10);
    }

    @Test
    @Order(5)
    void playWithWrongTokenShouldThrowAuthResponseError() throws Exception {
        String wrongToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3cm9uZ2RhdGEiLCJleHAiOjE2NjA4Mjk5MzV9.rppNee8g9BXK7A11DOWNwSh1bglcj-0YCI7pTUCwVrWC-XmcPCAfyUDfeMRDQ619wB_u3XLlfEdR6IJGoIqxhg";
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/play")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + wrongToken)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"Yunus\"}");

        assertThrows(AuthException.class, () -> mockMvc.perform(mockRequest));

    }

    @Test
    @Order(6)
    void loanShouldIncreaseCredit() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/loan")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"Yunus\"}");

        var mockResponse = mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userCredits").exists())
                .andReturn().getResponse();

        var credit = objectMapper.readValue(mockResponse.getContentAsString(), GameDto.class).getUserCredits();

        assertThat(credit).isPositive();
    }

    @Test
    @Order(7)
    void cashoutShouldResetCreditToInitial() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/cashout")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"Yunus\"}");
            //                .andExpect(jsonPath("$.user.username").value("Yunus"))
        var mockResponse = mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userCredits").exists())
                .andReturn().getResponse();
        var credit = objectMapper.readValue(mockResponse.getContentAsString(), GameDto.class).getUserCredits();
        assertThat(credit).isEqualTo(10);
    }
}
