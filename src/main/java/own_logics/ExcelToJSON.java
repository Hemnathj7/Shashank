package own_logics;
import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component("LIC9286_PJT1001_PE_NLP73118d59-6d22-4d96-89fd-a0ac1ea54d42")
public class ExcelToJSON implements Nlp {
    @InputParams({@InputParam(name = "Excel File Stream", type = "java.io.InputStream"),@InputParam(name = "Sheet Name", type = "java.lang.String")})


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
	InputStream	stream=(InputStream)attributes.get("Excel File Stream");
	String	sheetName=(String)attributes.get("Sheet Name");

    System.setProperty("java.awt.headless", "false");
	 try {
		  
				 Workbook workbook = new XSSFWorkbook(stream);

	            // Specify the sheet index (0-based) or sheet name
	            Sheet sheet = workbook.getSheet(sheetName);

	            // Create a JSON object to store the data
	            JsonObject jsonObject = new JsonObject();

	            // Iterate through rows and columns to read data
	            Iterator<Row> rowIterator = sheet.iterator();

	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();

	                // Assuming the first column contains keys and the subsequent columns contain values
	                Cell keyCell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	                String key = keyCell.toString();

	                // Create a JSON array for the values
	                JsonArray valuesArray = new JsonArray();

	                // Iterate through cells and add values to the array
	                for (int i = 1; i < row.getLastCellNum(); i++) {
	                    Cell dataCell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	                    // Check if the cell contains a numeric value or a string
	                    if (dataCell.getCellType() == CellType.NUMERIC) {
	                        // Convert numeric values to strings
	                        valuesArray.add(new DataFormatter().formatCellValue(dataCell));
	                    } else {
	                        // For non-numeric values, add directly
	                        valuesArray.add(dataCell.toString());
	                    }
	                }

	                // If there is more than one value, add the array to the JSON object
	                if (valuesArray.size() > 1) {
	                    jsonObject.add(key, valuesArray);
	                } else {
	                    // If there is only one value, add it directly to the JSON object
	                    jsonObject.addProperty(key, valuesArray.get(0).getAsString());
	                }
	            }

	            // Convert the JSON object to a string
	            Gson gson = new Gson();
	            String json = gson.toJson(jsonObject);
	            System.out.println(json);

	            // Close resources
	            workbook.close();
	           stream.close();
	            
	            
	            Instant now = Instant.now();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMHHmmss").withZone(ZoneOffset.systemDefault());
	            String timestamp = formatter.format(now);
	            String fileName = "Parsed_json_" + timestamp + ".json";
	            Path filePath = Path.of(System.getProperty("user.dir").replaceAll("\\\\", "/")+"/"+fileName);
	System.out.println(filePath);
	            Files.writeString(filePath, json, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
	        	Desktop.getDesktop().open(new File(filePath.toString()));
	            nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Parsed file stored at "+filePath);

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			log.info(exceptionAsString);
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed  " + exceptionAsString);
		}
	    System.setProperty("java.awt.headless", "true");
		return nlpResponseModel;
		}
		
}
