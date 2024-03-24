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
@Component("LIC9286_PJT1001_PE_NLP3f6cef0a-5f67-41ce-b1aa-62c773dbbb23")
public class ListBoxCheckk implements Nlp {
    @InputParams({@InputParam(name = "elementXpath", type = "java.lang.String"),
    	@InputParam(name = "expectedText", type = "java.lang.String")})
    @ReturnType(name = "trueOrFalse", type = "java.lang.Boolean")

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
          String xpath = (String) attributes.get("elementXpath");
          String expectedText = (String) attributes.get("expectedText");
          Boolean isSelected = false;
          // Your program element business logic goes here ...
try {
	
	WebDriver driver = (WebDriver) nlpRequestModel.getWebDriver();
	 
	 WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
	// w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    
List<WebElement> l = driver.findElements(By.xpath(xpath));

List<String> textList=new ArrayList<>();

for(int i=0;i<l.size();i++) {
textList.add(l.get(i).getText().toLowerCase());
}
for(int j=0;j<textList.size();j++){
	if(textList.get(j).contains(expectedText.toLowerCase())) {
		isSelected=true;
		break;
	}
}
if(isSelected==true) {
nlpResponseModel.setStatus(CommonConstants.pass);
nlpResponseModel.setMessage(expectedText+" is found inside the ListBox "+ textList);
}else{
	nlpResponseModel.setStatus(CommonConstants.pass);
	nlpResponseModel.setMessage(expectedText+" is not found inside the ListBox "+ textList);
}
}
catch(Exception e) {
	isSelected=false;
	StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    String exceptionAsString = sw.toString();
    log.info(exceptionAsString);
	  nlpResponseModel.setStatus(CommonConstants.pass);
	  nlpResponseModel.setMessage("Failed To find the String inside the Listbox "+exceptionAsString);
}
        //  String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("trueOrFalse", isSelected);
          return nlpResponseModel;
      }
  } 