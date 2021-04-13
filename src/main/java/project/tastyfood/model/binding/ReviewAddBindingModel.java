package project.tastyfood.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReviewAddBindingModel {
    private String review;
    private String assessment;

    public ReviewAddBindingModel() {
    }

    @Length(min=3, message = "Коментарът трябва да бъдат поне 3 символа.")
    @NotBlank(message = "Не трябва да бъде празно.")
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @NotNull(message = "Ти трябва да дадеш оценка")
    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }
}
