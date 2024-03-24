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

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component("LIC9286_PJT1001_PE_NLP4c83880c-a6b7-4680-a8b7-2a2937544272")
public class GetGeographicalCoOrdinates implements Nlp {
    @InputParams({@InputParam(name = "CoOrdinateNeeded", type = "java.lang.String")})
    @ReturnType(name = "coOrdinateValue", type = "java.lang.Double")

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
          String s = (String) attributes.get("CoOrdinateNeeded");
          String stringToCheck = (String) attributes.get("expectedText");
          Double d=null;    
          
try {
	
	AndroidDriver driver = (AndroidDriver) nlpRequestModel.getAndroidDriver();
	 
	
     if("latitude".contains(s.toLowerCase())) {
  	   d= driver.getLocationContext().location().getLatitude();
  	 nlpResponseModel.setStatus(CommonConstants.pass);
	  nlpResponseModel.setMessage("The latitude is "+d);
     }
     else if("longitude".contains(s.toLowerCase())) {
  	   d= driver.getLocationContext().location().getLongitude();
  	 nlpResponseModel.setStatus(CommonConstants.pass);
	  nlpResponseModel.setMessage("The longitude is "+d);
     }
     else if("altitude".contains(s.toLowerCase())) {
  	   d= driver.getLocationContext().location().getAltitude();
  	 nlpResponseModel.setStatus(CommonConstants.pass);
	  nlpResponseModel.setMessage("The altitude is "+d);
     }

	

}
catch(Exception e) {
	
	StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    String exceptionAsString = sw.toString();
    log.info(exceptionAsString);
	  nlpResponseModel.setStatus(CommonConstants.pass);
	  nlpResponseModel.setMessage("Failed To get geographical co-ordinate "+exceptionAsString);
}
nlpResponseModel.getAttributes().put("coOrdinateValue", d);
          return nlpResponseModel;
      }
  } 