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
@Component("LIC9286_PJT1001_PE_NLP0f405d90-e601-43f6-bf5f-14a898e79b30")
public class JSONToMap implements Nlp {
	@InputParams({ @InputParam(name = "Stream", type = "java.io.InputStream") })
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
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
			String jsonInput = "";
			String tempString;
			while ((tempString = bufferedReader.readLine()) != null) {
				jsonInput = jsonInput + tempString;
			}
		
			bufferedReader.close();
	        JSONObject jsonObject = new JSONObject(jsonInput);
	      

	        for (String key : jsonObject.keySet()) {
	            JSONObject obj = jsonObject.getJSONObject(key);
	            Map<String, Object> map = new HashMap<>();
	            for (String k : obj.keySet()) {
	                Object value = obj.get(k);
	                map.put(k, value);
	            }
	            result.put(key, map);
	        }

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage(result+" count is "+result.size());

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			log.info(exceptionAsString);
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed  " + exceptionAsString);
		}
		  nlpResponseModel.getAttributes().put("map", result);
		return nlpResponseModel;
	}
}