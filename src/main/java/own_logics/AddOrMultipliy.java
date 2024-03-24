package own_logics;



import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component("LIC9286_PJT1001_PE_NLP3b0e770a-1447-4fee-a4c0-f90e1f65d1f9")
public class AddOrMultipliy implements Nlp {
	@InputParams({ @InputParam(name = "Input1", type = "java.lang.String"),
			@InputParam(name = "Input2", type = "java.lang.String"),
			@InputParam(name = "Operator", type = "java.lang.String") })
	@ReturnType(name = "output", type = "java.lang.String")
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
		
		String Input1 = (String) attributes.get("Input1");
		String Input2 = (String) attributes.get("Input2");
		String Operator = (String) attributes.get("Operator");
		
		String outPut="";
		
		try {
			
			outPut=AddorMul(Input1,Input2,Operator);
			
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage(Input1+" "+Operator+" "+Input2+" ="+outPut);
			
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            log.info(exceptionAsString);
      	    nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to perform action "+Input1+" "+Operator+" "+Input2+" "+exceptionAsString);
			
		}
		nlpResponseModel.getAttributes().put("output",outPut );
		return nlpResponseModel;
	}
	public  String AddorMul(String input1, String Input2, String operator) {
		if(operator.equalsIgnoreCase("+")) {
			return (Long.parseLong(input1)+Long.parseLong(Input2))+"";
		}
		else if(operator.equalsIgnoreCase("-")) {
			return (Long.parseLong(input1)-Long.parseLong(Input2))+"";
		}
		
		return (Long.parseLong(input1)*Long.parseLong(Input2))+"";
	}

}