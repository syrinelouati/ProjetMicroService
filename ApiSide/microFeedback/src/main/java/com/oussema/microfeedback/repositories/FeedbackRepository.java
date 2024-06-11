package com.oussema.microfeedback.repositories;


import com.oussema.microfeedback.entities.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity,Integer> {
    // boolean existsByIdAndFeedbacks_CreatedBy(Integer campPlaceId, User user);

}
