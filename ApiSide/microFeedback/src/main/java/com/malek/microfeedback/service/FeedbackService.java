package com.malek.microfeedback.service;


import com.malek.microfeedback.entities.FeedbackEntity;
import com.malek.microfeedback.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor


public class FeedbackService implements IFeedbackService {
    private final FeedbackRepository feedbackRepository;
    @Override
    public List<FeedbackEntity> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public FeedbackEntity getFeedbackById(int id) {
        return feedbackRepository.findById(id).orElse(null);
    }




    @Override
    public void addFeedback(FeedbackEntity feedback) {
        feedbackRepository.save(feedback);
    }





    @Override
    public void deleteFeedback(int id) {
        feedbackRepository.deleteById(id);

    }


}