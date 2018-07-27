package app.questions.controllers;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.media.jfxmedia.logging.Logger;

import app.questions.documents.Question;
import app.questions.models.QuestionModel;
import app.questions.services.QuestionService;
import app.questions.utilities.QuestionDocumentToQuestionModel;

/**
 * Created by vinod on 22/05/18.
 */
@Controller
public class QuestionController {
	
	
	private static String UPLOADED_FOLDER = "D:\\";
	
	private QuestionService questionService;
	private QuestionDocumentToQuestionModel questionDocumentToQuestionModel;
	private QuestionModel questionModel;
	private List<QuestionModel> questionsList=new ArrayList<QuestionModel>();
	 

	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	@Autowired
	 public void setQuestionDocumentToQuestionModel(QuestionDocumentToQuestionModel questionDocumentToQuestionModel) {
		this.questionDocumentToQuestionModel = questionDocumentToQuestionModel;
	}



	@RequestMapping("/")
	    public String redirtHome(){
		 
	        return "questionviews/home";
	    }
	 
	 @RequestMapping("/question/new")
	    public String newProduct(Model model){
	       model.addAttribute("questionModel", new QuestionModel());
	        return "questionviews/questionform";
	    }
	 
	 @RequestMapping(value = "/question", method = RequestMethod.POST)
	    public String saveOrUpdateQuestion(@Valid QuestionModel questionModel, BindingResult bindingResult ){

		 Question savedQuestion= questionService.saveOrUpdateQuestionModel(questionModel);
		 
		 System.out.println("Saved Question  "+savedQuestion.getQuestion() );
	    
	        return "questionviews/displayquestions";
		
	 }
	 
	 @RequestMapping({"/questionviews/displayquestions", "/question"})
	    public String listQuestions(Model model){
	        model.addAttribute("questions", questionService.listAll());
	        
	        for (Question question: questionService.listAll()){
				 System.out.println("Question Name    "+question.getQuestion());
			 }
	        return "questionviews/displayquestions";
	    }
	 @RequestMapping("/display")
	    public String display(){
		 
		 for (Question question: questionService.listAll()){
			 System.out.println("Question Name    "+question.getQuestion());
		 }
	        return "questionviews/displayquestions";
	    }
	 
	 @RequestMapping("/file")
	    public String fileUpload(){
	    	
	       return "questionviews/fileupload";
	    }
	 
	 @PostMapping("/upload") 
	    public String singleFileUpload(@RequestParam("file") MultipartFile file,
	                                   RedirectAttributes redirectAttributes) {
		 int i=0;

	        if (file.isEmpty()) {
	            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
	            System.out.println("It is an empty file");
	            return "questionviews/fileupload";
	        }

	        try {

	            byte[] bytes = file.getBytes();
	            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	            Files.write(path, bytes);
	            
	            System.out.println("File uploaded successfully"+path);
	            
	            // Creating a Workbook from an Excel file (.xls or .xlsx)
	            Workbook workbook = WorkbookFactory.create(new File(path.toString()));

	            // Retrieving the number of sheets in the Workbook
	            System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

	            // Getting the Sheet at index zero
	            Sheet sheet = workbook.getSheetAt(0);
	            
	            // Create a DataFormatter to format and get each cell's value as String
	            DataFormatter dataFormatter = new DataFormatter();

	          for (Row row: sheet) {
	            	questionModel=new QuestionModel();
	            	if(i!=0) {
	            	int rownumber=0;
	                for(Cell cell: row) {
	                    String cellValue = dataFormatter.formatCellValue(cell);
	                    rownumber=rownumber+1;
	                    
	                    if(rownumber==1) {
	                    	questionModel.setQuestion(cellValue);
	                    }
	                    if(rownumber==2) {
	                    	questionModel.setQuestion_desc(cellValue);
	                    }
	                    if(rownumber==3) {
	                    	questionModel.setQuestion_type(cellValue);
	                    }
	                    if(rownumber==4) {
	                    	questionModel.setQuestion_tag(cellValue);
	                    }
	                    if(rownumber==5) {
	                    	questionModel.setQuestion_imageUrl(cellValue);
	                    }
	                    if(rownumber==6) {
	                    	questionModel.setQuestion_answers(cellValue.split(":"));
	                    }
	                }
	            	}
	            	i++;
	                
	                questionsList.add(questionModel);
	            
	            }

	            workbook.close();
	                    
	        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
	            e.printStackTrace();
	        }
	        
	        
	        questionService.saveQuestions(questionsList);
	        return "questionviews/home";
	    }


}
