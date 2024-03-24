package own_logics;



import java.nio.file.Files;
import java.nio.file.Paths;
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

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component("LIC9286_PJT1001_PE_NLPb8b13155-0bcb-4fea-9693-cd83f640ccd9")
public class JSONSchemaValidation implements Nlp {
	@InputParams({ @InputParam(name = "JSON String", type = "java.lang.String"),@InputParam(name = "Schema File Path", type = "java.lang.String") })
	 @ReturnType(name = "isValid", type = "java.lang.Boolean")
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
		
		String jsonString = (String) attributes.get("JSON String");
		String schemaFilePath = (String) attributes.get("Schema File Path");
  boolean json=false;
	List l=null;
  try {
	  String jsonSchema = new String(Files.readAllBytes(Paths.get("C:/Users/User/Desktop/JSON Sample Schema.json")));

      // Parse the JSON schema
      org.everit.json.schema.Schema schema = SchemaLoader.load(new JSONObject(jsonSchema));

      JSONObject jsonNode = new JSONObject(jsonString);
      schema.validate(jsonNode);
      // If validation succeeds, the JSON data is valid against the schema
      json=true;
     

nlpResponseModel.setStatus(CommonConstants.pass);
nlpResponseModel.setMessage("JSON data is valid against the schema.");
  

  } catch (ValidationException e) {
      json=false;
  	l=e.getAllMessages();
	nlpResponseModel.setStatus(CommonConstants.fail);
	nlpResponseModel.setMessage("Invalid JSON Format with "+l.size() +" errors:"+e.getAllMessages());
  

  } catch (Exception e) {
		nlpResponseModel.setStatus(CommonConstants.fail);
		nlpResponseModel.setMessage("Data is not in correct JSON format :"+e.getLocalizedMessage());
  }
		
		   nlpResponseModel.getAttributes().put("isValid", json);
		return nlpResponseModel;
	}
}
