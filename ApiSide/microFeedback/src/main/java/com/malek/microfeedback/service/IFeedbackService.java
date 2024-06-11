package com.malek.microfeedback.service;

import com.malek.microfeedback.entities.FeedbackEntity;

import java.util.List;

public interface IFeedbackService {
    List<FeedbackEntity> getAllFeedbacks();
    FeedbackEntity getFeedbackById (int id);
    void addFeedback (FeedbackEntity feedback);

    void deleteFeedback(int id);

}
