package app.questions.documents;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by vinod on 22/05/18.
 */
@Document(collection = "Questions")
public class Question {
    @Id
    private UUID question_uid;
    private String question;
    private String question_imageUrl;
    private String question_desc;
    private String question_type;
    private String question_tag;
    private String[] question_answers;  
	
    
	public UUID getQuestion_uid() {
		return question_uid;
	}
	public void setQuestion_uid(UUID question_uid) {
		this.question_uid = question_uid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestion_imageUrl() {
		return question_imageUrl;
	}
	public void setQuestion_imageUrl(String question_imageUrl) {
		this.question_imageUrl = question_imageUrl;
	}
	public String getQuestion_desc() {
		return question_desc;
	}
	public void setQuestion_desc(String question_desc) {
		this.question_desc = question_desc;
	}
	public String getQuestion_type() {
		return question_type;
	}
	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}
	public String getQuestion_tag() {
		return question_tag;
	}
	public void setQuestion_tag(String question_tag) {
		this.question_tag = question_tag;
	}
	public String[] getQuestion_answers() {
		return question_answers;
	}
	public void setQuestion_answers(String[] question_answers) {
		this.question_answers = question_answers;
	}

    
}
