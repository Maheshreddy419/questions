package app.questions.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.questions.documents.Question;

/**
 * Created by vinod on 22/05/18.
 */

public interface QuestionRepository extends MongoRepository<Question, UUID> {
	
	
}
