package com.malek.microfeedback.entities.ModelsDto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {

    private Integer idFeedback;
    private int rating;
    private String comment ;
    private Date createAt ;
}