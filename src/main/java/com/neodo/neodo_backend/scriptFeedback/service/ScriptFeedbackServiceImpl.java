package com.neodo.neodo_backend.scriptFeedback.service;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.ResourceException;
import com.neodo.neodo_backend.external.flask.utils.FlaskRequestUtils;
import com.neodo.neodo_backend.script.infrastructure.entity.ScriptEntity;
import com.neodo.neodo_backend.script.service.port.ScriptRepository;
import com.neodo.neodo_backend.scriptFeedback.controller.port.ScriptFeedbackService;
import com.neodo.neodo_backend.scriptFeedback.dto.request.ScriptFeedbackRequest;
import com.neodo.neodo_backend.scriptFeedback.dto.response.ScriptFeedbackResponse;
import com.neodo.neodo_backend.scriptFeedback.infrastructure.entity.ScriptFeedbackEntity;
import com.neodo.neodo_backend.scriptFeedback.service.port.ScriptFeedbackRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ScriptFeedbackServiceImpl implements ScriptFeedbackService {

    private final FlaskRequestUtils flaskRequestUtils;
    private final ScriptRepository scriptRepository;
    private final ScriptFeedbackRepository scriptFeedbackRepository;

    @Override
    public ScriptFeedbackResponse getFeedback(Long scriptId) {
        ScriptEntity scriptEntity = getScriptEntity(scriptId);

        return scriptFeedbackRepository.findByScriptEntity_Id(scriptEntity.getId())
                .map(this::buildFeedbackResponseFromEntity)
                .orElseGet(() -> getFeedbackFromFlask(scriptEntity));
    }

    @Override
    public ScriptFeedbackResponse getEditFeedback(Long scriptId) {
        ScriptEntity scriptEntity = getScriptEntity(scriptId);
        ScriptFeedbackEntity scriptFeedbackEntity = scriptFeedbackRepository.findByScriptEntity_Id(scriptEntity.getId())
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));
        return getEditedFeedbackFromFlask(scriptEntity, scriptFeedbackEntity);
    }

    private ScriptEntity getScriptEntity(Long scriptId) {
        return scriptRepository.findById(scriptId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));
    }

    private ScriptFeedbackResponse buildFeedbackResponseFromEntity(ScriptFeedbackEntity feedbackEntity) {
        return ScriptFeedbackResponse.builder()
                .feedback(feedbackEntity.getFeedback())
                .build();
    }

    private ScriptFeedbackResponse getFeedbackFromFlask(ScriptEntity scriptEntity) {
        ScriptFeedbackRequest request = ScriptFeedbackRequest.builder()
                .script(scriptEntity.getScript())
                .build();

        ScriptFeedbackResponse scriptFeedbackResponse = flaskRequestUtils.requestScriptFeedback(request);

        saveFeedback(scriptEntity, scriptFeedbackResponse);

        return scriptFeedbackResponse;
    }

    private void saveFeedback(ScriptEntity scriptEntity, ScriptFeedbackResponse scriptFeedbackResponse) {
        ScriptFeedbackEntity feedbackEntity = ScriptFeedbackEntity.builder()
                .scriptEntity(scriptEntity)
                .feedback(scriptFeedbackResponse.getFeedback())
                .build();

        scriptFeedbackRepository.save(feedbackEntity);
    }

    private ScriptFeedbackResponse getEditedFeedbackFromFlask(ScriptEntity scriptEntity, ScriptFeedbackEntity scriptFeedbackEntity) {
        ScriptFeedbackRequest request = ScriptFeedbackRequest.builder()
                .script(scriptEntity.getEditedScript())
                .build();

        ScriptFeedbackResponse scriptFeedbackResponse = flaskRequestUtils.requestScriptFeedback(request);

        scriptFeedbackEntity.setFeedback(scriptFeedbackResponse.getFeedback());

        return scriptFeedbackResponse;
    }
}
