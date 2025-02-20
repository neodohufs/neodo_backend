package com.neodo.neodo_backend.speechBoardFeedback;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CustomStringToListDeserializer extends JsonDeserializer<List<String>> {
    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String text = p.getText();
        if (text.startsWith("[") && text.endsWith("]")) {
            text = text.substring(1, text.length() - 1); // 대괄호 제거
            return Arrays.asList(text.split(",\\s*")); // 쉼표 기준으로 분리
        }
        throw new IOException("JSON 배열 형식이 아닙니다.");
    }
}
