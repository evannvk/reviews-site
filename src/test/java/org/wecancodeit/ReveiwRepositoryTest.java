package org.wecancodeit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

import java.util.Collection;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.wecancodeit.Review;
import org.wecancodeit.ReviewRepository;

public class ReveiwRepositoryTest {

	@Resource
	private ReviewRepository underTest;

	private Review reviewOne = new Review(1L, "review name", "description");
	private Review reviewTwo = new Review(2L, "review name", "description");

	@Test
	public void shouldFindCourseOneById() {
		underTest = new ReviewRepository(reviewOne);
		Review foundReview = underTest.findOneReview(1L);
		assertThat(foundReview, is(reviewOne));
	}

	@Test
	public void shouldFindCourseTwoById() {
		underTest = new ReviewRepository(reviewTwo);
		Review foundReviews = underTest.findOneReview(2L);
		assertThat(foundReviews, is(reviewTwo));
	}

	@Test
	public void shouldFindAllCourses() {
		underTest = new ReviewRepository(reviewOne, reviewTwo);
		Collection<Review> foundReviews = underTest.findAllReviews();
		assertThat(foundReviews, containsInAnyOrder(reviewOne, reviewTwo));
	}

}
