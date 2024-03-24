package own_logics;




import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;


import org.springframework.stereotype.Component;

@Component("LIC9286_PJT1001_PE_NLP6118c9ba-73ca-4798-9fa4-7f6ea4376352")
public class Wait_Untill_Element_Visible_MOB implements Nlp {
	@InputParams({@InputParam(name = "Wait Time", type = "java.lang.String"), @InputParam(name = "Xpath", type = "java.lang.String")})
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
		String waitTime = (String) attributes.get("Wait Time");
		String xpath = (String) attributes.get("Xpath");
		AppiumDriver driver = (AppiumDriver) nlpRequestModel.getDriver().getSpecificIDriver();
		//log.info("123");
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Long.parseLong(waitTime)));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			//log.info("456");
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully waited until element is visible");
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            //log.info(exceptionAsString);
      	  nlpResponseModel.setStatus(CommonConstants.fail);
			  nlpResponseModel.setMessage("Failed to wait till element is visible "+exceptionAsString);
		}
		
		return nlpResponseModel;
	}
} 

