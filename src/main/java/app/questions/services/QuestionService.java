package app.questions.services;

import java.util.List;
import java.util.UUID;

import app.questions.documents.Question;
import app.questions.models.QuestionModel;

/**
 * Created by vinod on 22/05/18.
 */
public interface QuestionService {

	List<Question> listAll();

	Question saveOrUpdate(Question question);

	Question saveOrUpdateQuestionModel(QuestionModel questionModel);

	void saveQuestions(List<QuestionModel> questionsList);
}
