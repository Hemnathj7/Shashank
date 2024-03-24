package own_logics;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC9286_PJT1001_PE_NLP5e3fbb97-f06b-4840-a2d0-840d1dd9383b")
public class XMLValidation implements Nlp {
	@InputParams({ @InputParam(name = "xml String", type = "java.lang.String") })
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
		
		String xmlString = (String) attributes.get("xml String");
		
boolean xml=false;
		 try {
			 
			 // Similarly use the commented line if you want to use the XML Data from a file
			//  String xmlData = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            org.w3c.dom.Document document = builder.parse(new org.xml.sax.InputSource(new java.io.StringReader(xmlString)));
	            xml=true;
	            nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Data is in correct XML format");
	        } catch (Exception e) {
	        	xml=false;
	        		nlpResponseModel.setStatus(CommonConstants.fail);
	    			nlpResponseModel.setMessage("Data is not in correct XML format :"+e.getLocalizedMessage());
	          
	        }
		   nlpResponseModel.getAttributes().put("isXML", xml);
		return nlpResponseModel;
	}
}
