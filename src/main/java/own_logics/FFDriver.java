package own_logics;



import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.BaseActivateApplicationOptions;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.stereotype.Component;

@Component("LIC9286_PJT1001_PE_NLPecf0f764-23df-4303-a955-c63edd92dd25")
public class FFDriver implements Nlp {
  

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
    //      XCUIApplication
          AndroidDriver d=null;
      
       
          try {
        	  WebDriverManager.firefoxdriver().setup();
        	  FirefoxOptions  options = new FirefoxOptions ();

        	  options.addArguments("--remote-allow-origins=*");
        	  options.addArguments("--start-maximized");

        	  options.addArguments("--use-fake-ui-for-media-stream");
        	  FirefoxDriver driver=new FirefoxDriver(options);
        		if(driver==null)
    			{
    				nlpResponseModel.setStatus(CommonConstants.fail);
    				nlpResponseModel.setMessage("driver is null");
    			}
        	
    			else {
    				nlpResponseModel.setWebDriver(WebDriver(driver));
    				nlpResponseModel.setStatus(CommonConstants.pass);
    				nlpResponseModel.setMessage("The Firefox Browser is successfully opened");
    			}
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to open FireFox Driver");
		}

          return nlpResponseModel;
          } 
      	private org.openqa.selenium.WebDriver WebDriver(FirefoxDriver driver) {
		// TODO Auto-generated method stub
		return driver;
	}

	
    
  } 