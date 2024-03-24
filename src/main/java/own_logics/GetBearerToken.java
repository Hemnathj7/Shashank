package own_logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

public class GetBearerToken implements Nlp {
	@ReturnType(name = "filteredLogs", type = "java.lang.String")

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
		  String lastMatch = "";

		try {

			// Retrieve network logs
			WebDriver driver = nlpRequestModel.getWebDriver();
			LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);
			
			List<String> filteredLogs = new ArrayList<>();
			for (LogEntry entry : logEntries) {
				String message = entry.getMessage();
				
				if (message.contains("Bearer")) {
					filteredLogs.add(message);
				}
			}
			int lastIndex = filteredLogs.size()-1;
			String s=filteredLogs.get(lastIndex);

   Pattern pattern = Pattern.compile("Bearer\\s+(.*?)(?=\")");
		       
		        Matcher matcher = pattern.matcher(s);
		      
		        while (matcher.find()) {
		            lastMatch = matcher.group(1);
		        }
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully Fetched Bearer Token");

		} catch (Exception e) {

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Failed To Fetch Bearer Token");
		}
		nlpResponseModel.getAttributes().put("filteredLogs", lastMatch);
		return nlpResponseModel;
	}
}