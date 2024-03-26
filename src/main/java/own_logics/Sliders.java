package own_logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

@Component(value="Sliders")
public class Sliders implements Nlp {
	@InputParams({@InputParam(name = "elementName", type = "java.lang.String"), 
		@InputParam(name = "elementType", type = "java.lang.String"),
		@InputParam(name = "element", type = "org.openqa.selenium.WebElement"),
		@InputParam(name = "sliderRange", type = "java.lang.Integer")})
    //@ReturnType(name = "string3", type = "java.lang.String")


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
          Integer range = (Integer) attributes.get("sliderRange");
         WebElement slider = (WebElement) attributes.get("Element");
         WebDriver driver = (WebDriver) nlpRequestModel.getDriver().getSpecificIDriver();
    	 Actions act = new Actions(driver);
         // Your program element business logic goes here ...
      
         try {
            act.moveToElement(slider).clickAndHold().moveByOffset(range, 0).release().perform();
         	nlpResponseModel.setStatus(CommonConstants.pass);
   			nlpResponseModel.setMessage("Slider moved till "+range+"");
           }
           catch(Exception e) {
         	   nlpResponseModel.setStatus(CommonConstants.fail);
   			   nlpResponseModel.setMessage("Element not found");
           }
         //String string3 = "Return Value";
         //nlpResponseModel.getAttributes().put("string3", string3);
         return nlpResponseModel;
     }
 } 
