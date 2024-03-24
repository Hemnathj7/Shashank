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
import org.openqa.selenium.html5.Location;
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

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component("LIC9286_PJT1001_PE_NLPb46c85f1-3a4b-4125-8a52-2a64fc5aba70")
public class SetMockLocation implements Nlp {
    @InputParams({@InputParam(name = "latitude", type = "java.lang.Double"),
    	@InputParam(name = "longitude", type = "java.lang.Double"),
    	@InputParam(name = "altitude", type = "java.lang.Double")})
  //  @ReturnType(name = "trueOrFalse", type = "java.lang.Boolean")

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
          Double latitude = (Double) attributes.get("latitude");
          Double longitude = (Double) attributes.get("longitude");
          Double altitude = (Double) attributes.get("altitude");
       
try {
	AndroidDriver driver = nlpRequestModel.getAndroidDriver();
	  driver.setLocation(new Location(latitude, longitude, altitude));
nlpResponseModel.setStatus(CommonConstants.pass);
nlpResponseModel.setMessage("Location is successfully set to "+latitude+","+longitude+" and "+altitude);
}
catch(Exception e) {
	StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    String exceptionAsString = sw.toString();
    log.info(exceptionAsString);
	  nlpResponseModel.setStatus(CommonConstants.pass);
	  nlpResponseModel.setMessage("Failed To set the location "+exceptionAsString);
}
          return nlpResponseModel;
      }
  } 