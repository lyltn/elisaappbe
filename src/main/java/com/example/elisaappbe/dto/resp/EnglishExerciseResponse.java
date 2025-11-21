package com.example.elisaappbe.dto.resp;

import com.example.elisaappbe.model.EnglishMultipleChoiceQuestion;
import com.example.elisaappbe.model.EnglishSentenceRewritingQuestion;
import lombok.Data;

import java.util.List;

@Data
public class EnglishExerciseResponse {
    private List<EnglishMultipleChoiceResponse> listMultipleChoice;
    private List<EnglishSentenceRewritingResponse> listSentenceRewriting;
}
