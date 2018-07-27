package app.questions.utilities;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import app.questions.documents.Question;
import app.questions.models.QuestionModel;

/**
 * Created by vinod on 22/05/18.
 */
@Component
public class QuestionDocumentToQuestionModel implements Converter<Question, QuestionModel> {
    @Override
    public QuestionModel convert(Question question) {
    
    	QuestionModel questionForm=new QuestionModel();
    	
    	questionForm.setQuestion_uid(question.getQuestion_uid());
    	questionForm.setQuestion(question.getQuestion());
    	questionForm.setQuestion_desc(question.getQuestion_desc());
    	questionForm.setQuestion_imageUrl(question.getQuestion_imageUrl());
    	
        return questionForm;
    }
}
