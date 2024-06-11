package com.malek.microfeedback.Controller;


import com.malek.microfeedback.entities.FeedbackEntity;
import com.malek.microfeedback.entities.ModelsDto.FeedbackDto;
import com.malek.microfeedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
@RequestMapping("api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<FeedbackEntity>> getAllFeedbacks() {
        List<FeedbackEntity> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackEntity> getFeedbackById(@PathVariable("id") int id) {
        FeedbackEntity feedback = feedbackService.getFeedbackById(id);
        if (feedback != null) {
            return ResponseEntity.ok(feedback);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addFeedback")
    public FeedbackEntity addFeedback(@RequestBody FeedbackDto feedbackDto) {
        FeedbackEntity feedback = modelMapper.map(feedbackDto, FeedbackEntity.class);
        feedbackService.addFeedback(feedback);
        return feedback;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable("id") int id) {
        FeedbackEntity feedback = feedbackService.getFeedbackById(id);
        if (feedback != null) {
            feedbackService.deleteFeedback(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}