package services;

import model.users.Teacher;

public class RatingService {
    public void rateTeacher(Teacher teacher, double rating) {
        teacher.setRating(rating);
    }
}