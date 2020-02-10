package org.wecancodeit;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.ReviewNotFoundException;
import org.wecancodeit.ReviewRepository;

@RequestMapping("/show-courses")
@Controller
public class ReviewController {


	@Resource
	private ReviewRepository reviewRepo;

	@GetMapping("")
	public String findAllCouses(Model model) {
		model.addAttribute("coursesModel", reviewRepo.findAllReviews());
		return "courses-template.html"; // src/main/resources/templates + template name + .html
	}

	@GetMapping("{id}")
	public String findOneCourse(@PathVariable(value = "id") Long id, Model model) throws ReviewNotFoundException {
		if (reviewRepo.findOneReview(id) == null) {
			throw new ReviewNotFoundException();
		}
		model.addAttribute("courseModel", reviewRepo.findOneReview(id));
		return "course-template";
	}

}
