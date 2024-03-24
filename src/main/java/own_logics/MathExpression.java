package own_logics;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
@Component("LIC9286_PJT1001_PE_NLP081a1467-7d99-4a97-826b-e8ea7c22478b")
public class MathExpression implements Nlp {
    @InputParams({@InputParam(name = "Math Expression", type = "java.lang.String")})
    @ReturnType(name = "result", type = "java.lang.String")

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
          String mathExpression = (String) attributes.get("Math Expression");
        String s="";   
        ScriptEngineManager manager = new ScriptEngineManager();
try {
	//  ScriptEngineManager manager = new ScriptEngineManager();
      
      
      ScriptEngine engine = manager.getEngineByName("nashorn");
      StringBuilder modifiedString=new StringBuilder();
      for (char c : mathExpression.toCharArray()) {
          if (c != '=') {
              modifiedString.append(c);
          }
      }
      Object result = engine.eval(modifiedString.toString());
s=result.toString();
nlpResponseModel.setStatus(CommonConstants.pass);
nlpResponseModel.setMessage(mathExpression+" is evaluated as "+s);
}
catch(Exception e) {
		StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    String exceptionAsString = sw.toString();
    log.info(exceptionAsString);
	  nlpResponseModel.setStatus(CommonConstants.fail);
	  nlpResponseModel.setMessage("Failed To evaluate the Math Expression "+mathExpression+exceptionAsString);
}
      
          nlpResponseModel.getAttributes().put("result", s);
          return nlpResponseModel;
      }
}