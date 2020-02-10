package org.wecancodeit;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository {

	private Map<Long, Review> reviewList = new HashMap<>();
	private Review reviewOne = new Review(1L, "course one", "description");
	private Review reviewTwo = new Review(2L, "course two", "description");

	public ReviewRepository() {
		reviewList.put(reviewOne.getId(), reviewOne);
		reviewList.put(reviewTwo.getId(), reviewTwo);
	}

	// uses varargs for testing purposes to take on the necessary courses
	public ReviewRepository(Review... review) {
		for (Review reviews: review) {
			reviewList.put(reviews.getId(), reviews);
		}
	}

	public Review findOneReview(long id) {

		return reviewList.get(id);
	}

	public Collection<Review> findAllReviews() {

		return reviewList.values();
	}

}
