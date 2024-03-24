package own_logics;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.springframework.stereotype.Component;

@Slf4j
@Component("LIC9286_PJT1001_PE_NLP1a270170-7632-4343-9e1f-85f8ab668eaa")
public class ClickOrTypeOnImage implements Nlp {
    @InputParams({@InputParam(name = "Image Path", type = "java.lang.String"),@InputParam(name = "Action", type = "java.lang.String"),@InputParam(name = "Text To Type", type = "java.lang.String")})
   
      @Override
      public List<String> getTestParameters() throws NlpException {
        List<String> params = new ArrayList<>();
        return params;
      }

      @Override
      public StringBuilder getTestCode() throws NlpException {
        StringBuilder sb = new StringBuilder();
        return sb;
      }
      @Override
      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
        
          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          Map<String, Object> attributes = nlpRequestModel.getAttributes();
          String imagePath = (String) attributes.get("Image Path");
          String action = (String) attributes.get("Action");
          String textToType = (String) attributes.get("Text To Type");
          System.setProperty("java.awt.headless", "false");
         try
         {
        	    Screen screen = new Screen();
    	        Pattern imagePattern = new Pattern(imagePath);

   if ((action.toLowerCase()).contains("click")){
		screen.click(imagePattern);  
		  nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Performed Click operation");	

   }
   else if((action.toLowerCase()).contains("type"))	
   {
	  
       Region elementRegion = screen.find(imagePattern);
       elementRegion.type(textToType);
       nlpResponseModel.setStatus(CommonConstants.pass);
		nlpResponseModel.setMessage("Performed Type operation");	
   }
          
	        
         }
        catch(Exception e) {
        	StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            log.info(exceptionAsString);
      	    nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to perform action "+exceptionAsString);	
        }
         
         
         

        

          return nlpResponseModel;
      }
  } 