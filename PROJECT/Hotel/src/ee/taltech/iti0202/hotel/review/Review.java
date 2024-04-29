package ee.taltech.iti0202.hotel.review;

import ee.taltech.iti0202.hotel.hotel.Hotel;

public class Review {
    private final Integer score;
    private final String review;
    private final Hotel hotel;

    /**
     * Constructs a new Review object.
     * @param review The review which was written.
     * @param score The review score. Must be between 1 - 5, otherwise throws IllegalArgumentException.
     * @param hotel The hotel what got the review.
     */
    public Review(String review, Integer score, Hotel hotel) {
        this.review = review;
        if (score >= 1 && score <= 5) {
            this.score = score;
        } else {
            throw new IllegalArgumentException();
        }
        this.hotel = hotel;
    }

    /**
     * This method is used to get review String written by client.
     * @return the review as a String.
     */
    public String getReview() {
        return review;
    }

    /**
     * This method is used to get review as score written by client .
     * @return the score as an Integer.
     */
    public Integer getScore() {
        return score;
    }

    /**
     * This method is used to get hotel object that the review is written about.
     * @return the hotel object that the review is written about.
     */
    public Hotel getHotel() {
        return hotel;
    }
}
