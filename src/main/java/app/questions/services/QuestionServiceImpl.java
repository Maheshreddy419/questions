package app.questions.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.questions.documents.Question;
import app.questions.models.QuestionModel;
import app.questions.repositories.QuestionRepository;
import app.questions.utilities.QuestionModelToQuestionDocument;

/**
 * Created by vinod on 22/05/18.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;
    private QuestionModelToQuestionDocument questionModelToQuestionDocument;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionModelToQuestionDocument questionModelToQuestionDocument) {
        this.questionRepository = questionRepository;
        this.questionModelToQuestionDocument = questionModelToQuestionDocument;
    }


	@Override
	public Question saveOrUpdate(Question question) {
		questionRepository.save(question);   
		 return question;
	}


	@Override
	public Question saveOrUpdateQuestionModel(QuestionModel questionModel) {
		
		
		Question savedQuestion = saveOrUpdate(questionModelToQuestionDocument.convert(questionModel));
       
        return savedQuestion;
	}


	@Override
	public List<Question> listAll() {
		 List<Question> questions = new ArrayList<>();
		 
		 questionRepository.findAll().forEach(questions::add); //fun with Java 8
		 
	   return questions;
		
	}


	@Override
	public void saveQuestions(List<QuestionModel> questionsList) {
		 
		for(QuestionModel result: questionsList)
		 {
			saveOrUpdate(questionModelToQuestionDocument.convert(result));
			System.out.println(result.getQuestion());
			 
		 }
		
		System.out.println("Successfully Saved into DataBase");
	}



}
