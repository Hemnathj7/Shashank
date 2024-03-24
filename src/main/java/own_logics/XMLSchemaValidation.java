package own_logics;



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

import org.springframework.stereotype.Component;

@Component("LIC9286_PJT1001_PE_NLP357b680a-9642-4a15-a668-68397beb72e0")
public class XMLSchemaValidation implements Nlp {
	@InputParams({ @InputParam(name = "XML String", type = "java.lang.String"),@InputParam(name = "Schema File Path", type = "java.lang.String") })
	 @ReturnType(name = "isXML", type = "java.lang.Boolean")
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
		
		String xmlData = (String) attributes.get("XML String");
		String schemaFilePath = (String) attributes.get("Schema File Path");
  boolean xml=false;
  try {
      // Create a SchemaFactory and load the schema file
      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema = schemaFactory.newSchema(new StreamSource(schemaFilePath));

      // Create a validator for the schema
      javax.xml.validation.Validator validator = schema.newValidator();

      // Validate the XML data against the schema
      Source source = new StreamSource(new java.io.StringReader(xmlData));
      validator.validate(source);

      // If validation succeeds, the XML data is valid against the schema
      xml=true;
      nlpResponseModel.setStatus(CommonConstants.pass);
		nlpResponseModel.setMessage("Data is valid against the XML schema");

  } catch (SAXException e) {
	  xml=false;
		nlpResponseModel.setStatus(CommonConstants.fail);
		nlpResponseModel.setMessage("XML data is not valid against the schema. Errors: :"+e.getLocalizedMessage());
     
  } catch (Exception e) {
	  nlpResponseModel.setStatus(CommonConstants.fail);
		nlpResponseModel.setMessage("Data is not in a XML format");
  }
		
		   nlpResponseModel.getAttributes().put("isXML", xml);
		return nlpResponseModel;
	}
}
