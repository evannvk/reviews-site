package org.wecancodeit;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository {

	private Map<Long, Review> reviewList = new HashMap<>();
	private Review reviewOne = new Review(1, "Review One", "category", "image url", "content");
	private Review reviewTwo = new Review(2, "review two", "category", "image url", "content");

	public ReviewRepository() {
		reviewList.put(reviewOne.getId(), reviewOne);
		reviewList.put(reviewTwo.getId(), reviewTwo);
	}

	// uses varargs for testing purposes to take on the necessary courses
	public ReviewRepository(Review... reviews) {
		for (Review review: reviews) {
			reviewList.put(review.getId(), review);
		}
	}

	public Review findOneReview(long id) {

		return reviewList.get(id);
	}

	public Collection<Review> findAllReviews() {

		return reviewList.values();
	}

}
