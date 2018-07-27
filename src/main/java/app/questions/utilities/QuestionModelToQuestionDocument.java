package app.questions.utilities;

import java.util.UUID;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import app.questions.documents.Question;
import app.questions.models.QuestionModel;

/**
 * Created by vinod on 22/05/18.
 */
@Component
public class QuestionModelToQuestionDocument implements Converter<QuestionModel, Question> {

    @Override
    public Question convert(QuestionModel questionDocument) {
    	Question question = new Question();
    	
    	UUID idOne = UUID.randomUUID();
		question.setQuestion_uid(idOne);
    	question.setQuestion(questionDocument.getQuestion());
    	question.setQuestion_desc(questionDocument.getQuestion_desc());
    	question.setQuestion_imageUrl(questionDocument.getQuestion_imageUrl());
    	question.setQuestion_tag(questionDocument.getQuestion_tag());
    	question.setQuestion_type(questionDocument.getQuestion_type());
    	
    	String[] question_answers= {"Answer1","Answer2"};
    	
    	question.setQuestion_answers(question_answers);
    	
        return question;
    }
}
