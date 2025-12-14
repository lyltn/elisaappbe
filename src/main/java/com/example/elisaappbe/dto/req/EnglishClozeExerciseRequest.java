package com.example.elisaappbe.dto.req;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnglishClozeExerciseRequest {
    private String title;
    private String content;
    private List<EnglishClozeBlankRequest> blanks;
}
