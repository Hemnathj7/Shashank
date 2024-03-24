package own_logics;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.jayway.jsonpath.JsonPath;
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
@Component("LIC9286_PJT1001_PE_NLPaac484bc-60f3-4189-857c-4ac12dd29c02")
public class JSONToMapUsingPath implements Nlp {
	@InputParams({ @InputParam(name = "Stream", type = "java.io.InputStream") ,@InputParam(name = "JSON Path", type = "java.lang.String")})
    @ReturnType(name = "map", type = "java.util.Map")

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
		DesiredCapabilities cap = (DesiredCapabilities) attributes.get("Desired Capabilities");
		  Map<String, Map<String, Object>> result = new HashMap<>();
		  InputStream fis=(InputStream)attributes.get("Stream");
		  String path=(String)attributes.get("JSON Path");
		  String jsonInput = "";
		 
		  Map <String, Object> salDataMap =null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
			
			String tempString;
			while ((tempString = bufferedReader.readLine()) != null) {
				jsonInput = jsonInput + tempString;
			}
		
			bufferedReader.close();
		      
			salDataMap=   extractValuesFromJsonPath(jsonInput, path);

		        System.out.println("Values of salData:");
		        for (Map.Entry<String, Object> entry : salDataMap.entrySet()) {
		            System.out.println(entry.getKey() + ": " + entry.getValue());
		        }

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage(salDataMap+" count is "+salDataMap.size());

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			log.info(exceptionAsString);
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage(salDataMap+" Failed  " + exceptionAsString);
		}
		  nlpResponseModel.getAttributes().put("map", salDataMap);
		return nlpResponseModel;
	}
	  public static Map<String, Object> extractValuesFromJsonPath(String jsonString, String jsonPathExpression) {
	        Map<String, Object> resultMap = new HashMap<>();

	        Object result = JsonPath.read(jsonString, jsonPathExpression);
	        if (result instanceof Map) {
	            resultMap.putAll((Map<String, Object>) result);
	        }

	        return resultMap;
	    }
}