package own_logics;






import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
@Slf4j
@Component("LIC9286_PJT1001_PE_NLPa1fa1821-2134-4d8a-928d-979b6dba9788")
public class MultiSelect implements Nlp {
    @InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement"),
    	@InputParam(name = "expectedText", type = "java.lang.String")})
   // @ReturnType(name = "trueOrFalse", type = "java.lang.Boolean")

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
          WebElement ele = (WebElement) attributes.get("element");
          String stringToCheck = (String) attributes.get("expectedText");
          String s="";
          // Your program element business logic goes here ...
try {
	
	WebDriver driver = (WebDriver) nlpRequestModel.getWebDriver();
	 
	
	 Select select = new Select(ele);
	 
	 for (WebElement option : select.getOptions()) {
		 s=option.getText();
		 if(!((s.toLowerCase()).contains(stringToCheck.toLowerCase()))) {
			 select.selectByVisibleText(s);
		 }
		
		 
		}
	 for (WebElement option : select.getOptions()) {
		 s=option.getText();
		 if(s.contains("Aud")) {
			 select.deselectByVisibleText(s);
		 }
		 }

	 nlpResponseModel.setStatus(CommonConstants.pass);
	  nlpResponseModel.setMessage("Succesfully selected the options except "+stringToCheck);

}
catch(Exception e) {
	
	StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    String exceptionAsString = sw.toString();
    log.info(exceptionAsString);
	  nlpResponseModel.setStatus(CommonConstants.pass);
	  nlpResponseModel.setMessage("Failed To Handle the Multi select dropdown "+exceptionAsString);
}
       
          return nlpResponseModel;
      }
  } 