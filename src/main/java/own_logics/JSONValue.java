package own_logics;




import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.text.Document;
import javax.validation.Validator;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC9286_PJT1001_PE_NLPed0be08c-02df-4388-b82a-9f5dbfb2e417")
public class JSONValue implements Nlp {
	@InputParams({ @InputParam(name = "Input Stream", type = "java.io.InputStream"),@InputParam(name = "JSON Path", type = "java.lang.String")})
	 @ReturnType(name = "value", type = "java.lang.String")
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
		
		InputStream stream = (InputStream) attributes.get("Input Stream");
		String path = (String) attributes.get("JSON Path");
String s="";
  try {
	  Object document = Configuration.defaultConfiguration().jsonProvider().parse(stream, "UTF-8");
	  Object j = JsonPath.read(document, path);
	s=j.toString();
     
      nlpResponseModel.setStatus(CommonConstants.pass);
		nlpResponseModel.setMessage("Data is valid against the XML schema");
  }
 catch (Exception e) {
	  nlpResponseModel.setStatus(CommonConstants.fail);
		nlpResponseModel.setMessage("Data is not in a XML format");
  }
		
		   nlpResponseModel.getAttributes().put("value", s);
		return nlpResponseModel;
	}
}
