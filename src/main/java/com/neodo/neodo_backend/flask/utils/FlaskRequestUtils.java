package com.neodo.neodo_backend.flask.utils;

import com.neodo.neodo_backend.exception.impl.FlaskException;
import com.neodo.neodo_backend.speechBoardFeedback.dto.request.SpeechBoardFeedbackRequest;
import com.neodo.neodo_backend.speechBoardFeedback.dto.response.SpeechBoardFeedbackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum.EXTERNAL_SERVICE_ERROR;
import static com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum.RESPONSE_NOT_VALID;

@Component
@RequiredArgsConstructor
public class FlaskRequestUtils {
    private final RestTemplate restTemplate;

    @Value("${flask.server.url}")
    private String flaskServerUrl; // final 제거

    public SpeechBoardFeedbackResponse requestSpeechBoardFeedback(SpeechBoardFeedbackRequest speechBoardFeedbackRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SpeechBoardFeedbackRequest> requestEntity = new HttpEntity<>(speechBoardFeedbackRequest, headers);

        try {
            ResponseEntity<SpeechBoardFeedbackResponse> responseEntity = restTemplate.exchange(
                    flaskServerUrl,
                    HttpMethod.POST,
                    requestEntity,
                    SpeechBoardFeedbackResponse.class
            );

            if (responseEntity.getBody() == null) throw new FlaskException(RESPONSE_NOT_VALID);

            return responseEntity.getBody();

        } catch (Exception e) {
            throw new FlaskException(EXTERNAL_SERVICE_ERROR);
        }
    }
}
