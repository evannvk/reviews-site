package org.wecancodeit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.wecancodeit.ReviewController;
import org.wecancodeit.Review;
import org.wecancodeit.ReviewRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMVCTest {

	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private Review reviewOne;
	
	@Mock
	private Review reviewTwo;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@Test
	public void shouldGetStatusOfOkWhenNavigationToAllReviews() throws Exception {
		this.mockMvc.perform(get("/reviews")).andExpect(status().isOk()).andExpect(view().name("reviews-headpage"));
	}
	
	@Test
	public void shouldGetStatusOfOkWhenNavigatingToReviewOnePage() throws Exception {
		when(reviewRepo.findOneReview(1)).thenReturn(reviewOne);
		this.mockMvc.perform(get("/review?id=1")).andExpect(status().isOk()).andExpect(view().name("review-template.html"));
	}
	
	@Test
	public void shouldAddAllReviewsToTheModel() throws Exception {
		when(reviewRepo.findAllReviews()).thenReturn(Arrays.asList(reviewOne, reviewTwo));
		this.mockMvc.perform(get("/reviews")).andExpect(model().attribute("reviewsModel", hasSize(2)));
	}
	
	@Test
	public void shouldAddSingleReviewToTheModel() throws Exception {
		when(reviewRepo.findOneReview(1)).thenReturn(reviewOne);
		this.mockMvc.perform(get("/review?id=1")).andExpect(model().attribute("reviewModel", is(reviewOne)));
	}
	
	@Test
	public void shouldReturnNotFoundForBadRequest() throws Exception {
		Long badId = (long) 5;
		when(reviewRepo.findOneReview(badId)).thenReturn(null);
		this.mockMvc.perform(get("/review?id=5")).andExpect(status().isNotFound());
	}

}
