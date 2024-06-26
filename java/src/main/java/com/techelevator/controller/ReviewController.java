package com.techelevator.controller;

import com.techelevator.dao.LandmarkDao;
import com.techelevator.dao.ReviewDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Landmark;
import com.techelevator.model.Review;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ReviewController {

    final LandmarkDao landmarkDao;
    final UserDao userDao;
    final ReviewDao reviewDao;

    public ReviewController(LandmarkDao landmarkDao, UserDao userDao, ReviewDao reviewDao) {
        this.landmarkDao = landmarkDao;
        this.userDao = userDao;
        this.reviewDao = reviewDao;
    }

    @GetMapping("/reviews/{landmarkId}")
    public List<Review> getAllReviews(@PathVariable int landmarkId) {
        try {
            return this.reviewDao.getAllReviews(landmarkId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot connect to server");
        }
    }
    @PostMapping("/reviews")
    public Review createReview(@RequestBody Review review, Principal principal) {
        User user = this.userDao.getUserByUsername(principal.getName());
        try {
            return this.reviewDao.createReview(review, user.getId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot connect to server");
        }
    }
}
