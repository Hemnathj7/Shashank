package own_logics;
//package own_logics;
//
//
//
//
//import java.io.File;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import com.aspose.cells.CellsFactory;
//import com.aspose.cells.Color;
//import com.aspose.cells.JsonLayoutOptions;
//import com.aspose.cells.JsonUtility;
//import com.aspose.cells.Style;
//import com.aspose.cells.TextAlignmentType;
//import com.aspose.cells.Workbook;
//import com.aspose.cells.Worksheet;
//import com.aspose.cells.WorksheetCollection;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.tyss.optimize.common.util.CommonConstants;
//import com.tyss.optimize.nlp.util.Nlp;
//import com.tyss.optimize.nlp.util.NlpException;
//import com.tyss.optimize.nlp.util.NlpRequestModel;
//import com.tyss.optimize.nlp.util.NlpResponseModel;
//import com.tyss.optimize.nlp.util.annotation.InputParam;
//import com.tyss.optimize.nlp.util.annotation.InputParams;
//
//import lombok.extern.slf4j.Slf4j;
//
//
//import org.springframework.stereotype.Component;
//@Slf4j
//@Component("LIC9286_PJT1001_PE_NLPe9ca462a-2a8a-4df2-9633-b7fecf76cf51")
//public class JsonValuesStore implements Nlp {
//
//	@InputParams({ @InputParam(name = "JSON File Path", type = "java.lang.String"),
//			@InputParam(name = "Excel File Path", type = "java.lang.String") })
//
//	@Override
//	public List<String> getTestParameters() throws NlpException {
//		List<String> params = new ArrayList<>();
//		return params;
//	}
//
//	@Override
//	public StringBuilder getTestCode() throws NlpException {
//		StringBuilder sb = new StringBuilder();
//		return sb;
//	}
//
//	static String excelFilePath = "";
//	static String jsonFilePath = "";
//
//	@Override
//	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
//
//		NlpResponseModel nlpResponseModel = new NlpResponseModel();
//		Map<String, Object> attributes = nlpRequestModel.getAttributes();
//		jsonFilePath = (String) attributes.get("JSON File Path");
//		excelFilePath = (String) attributes.get("Excel File Path");
//
//		// Your program element business logic goes here ...
//		try {
//			JsontoExcel();
//			nlpResponseModel.setStatus(CommonConstants.pass);
//			nlpResponseModel.setMessage("Successfully Converted to JSON to Excel");
//		} catch (Exception e) {
//			StringWriter sw = new StringWriter();
//			e.printStackTrace(new PrintWriter(sw));
//			String exceptionAsString = sw.toString();
//			log.info(exceptionAsString);
//			nlpResponseModel.setStatus(CommonConstants.fail);
//			nlpResponseModel.setMessage("Failed Converted to JSON to Excel");
//		}
//
//		return nlpResponseModel;
//	}
//
//	// Json to excel
//	public static void JsontoExcel() throws Exception {
//		String initialJSON = checkFileisCorrectOrNot(jsonFilePath);
//		try {
//
//			JsonElement jsonElement = JsonParser.parseString(initialJSON);
//			Workbook workbook = new Workbook();
//
//			if (jsonElement.isJsonArray()) {
//				// Parse the JSON array
//				try {
//					JsonArray jsonArray = JsonParser.parseString(initialJSON).getAsJsonArray();
//					System.out.println(jsonArray.size());
//					System.out.println(1);
//					// Iterate through the JSON array
//					for (int i = 0; i < jsonArray.size(); i++) {
//						System.out.println("Inside of forloop iteration time" + i);
//						try {
//							JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
//							// Convert the JSON object to a JSON string
//							String jsonString = jsonObject.toString();
//
//							// Save the JSON string to a file
//							String fileName = "C:\\Users\\user\\Downloads\\test1.json";
//							// saveJsonToFile(jsonString, fileName);
//							WorksheetCollection worksheets = workbook.getWorksheets();
//
//							Worksheet worksheet1 = worksheets.add("Object" + i);
//							Worksheet worksheet = workbook.getWorksheets().get("Object" + i);
//
//							File file = new File(fileName);
//
//							// Set styles
//							CellsFactory factory = new CellsFactory();
//							Style style = factory.createStyle();
//							style.setHorizontalAlignment(TextAlignmentType.CENTER);
//							style.getFont().setColor(Color.getBlueViolet());
//							style.getFont().setBold(true);
//
//							// Set JsonLayoutOptions
//
//							// Import JSON data
//
//							JsonLayoutOptions options = new JsonLayoutOptions();
//							options.setTitleStyle(style);
//							options.setArrayAsTable(true);
//							JsonUtility.importData(jsonString, worksheet.getCells(), 0, 0, options);
//						} catch (Exception e) {
//
//						}
//					}
//				} catch (Exception e) {
//					// return;
//				}
//			} else if (jsonElement.isJsonObject()) {
//				// saveJsonToFile(initialJSON, "C:\\Users\\user\\Downloads\\test1.json");
//				WorksheetCollection worksheets = workbook.getWorksheets();
//
//				Worksheet worksheet1 = worksheets.add("SingleObject");
//				Worksheet worksheet = workbook.getWorksheets().get("SingleObject");
//
//				File file = new File("C:\\Users\\user\\Downloads\\test1.json");
//
//				// Set styles
//				CellsFactory factory = new CellsFactory();
//				Style style = factory.createStyle();
//				style.setHorizontalAlignment(TextAlignmentType.CENTER);
//				style.getFont().setColor(Color.getBlueViolet());
//				style.getFont().setBold(true);
//
//				// Set JsonLayoutOptions
//
//				// Import JSON data
//				try {
//					JsonLayoutOptions options = new JsonLayoutOptions();
//					options.setTitleStyle(style);
//					options.setArrayAsTable(true);
//					JsonUtility.importData(initialJSON, worksheet.getCells(), 0, 0, options);
//
//				} catch (Exception e) {
//
//				}
//			} else {
//
//			}
//			workbook.save(excelFilePath);
//
//		} catch (Exception e) {
//
//		}
//
//		deleteSheet();
//
//	}
//
//	// sheet deleting
//	static void deleteSheet() {
//		try {
//			FileInputStream fis = new FileInputStream(excelFilePath);
//			XSSFWorkbook workbook1 = new XSSFWorkbook(fis);
//
//			// Find the sheet to remove (e.g., "Evaluation Warning")
//			String sheetNameToRemove1 = "Sheet1";
//			int sheetIndexToRemove1 = workbook1.getSheetIndex(sheetNameToRemove1);
//
//			String sheetNameToRemove = "Evaluation Warning";
//			int sheetIndexToRemove = workbook1.getSheetIndex(sheetNameToRemove);
//
//			if (sheetIndexToRemove >= 0) {
//				// Remove the sheet
//				workbook1.removeSheetAt(sheetIndexToRemove);
//				workbook1.removeSheetAt(sheetIndexToRemove1);
//				// Save the modified workbook
//				FileOutputStream fos = new FileOutputStream(excelFilePath);
//				workbook1.write(fos);
//				fos.close();
//				System.out.println("deleted");
//			}
//
//			workbook1.close();
//			fis.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	// trimming extra space
//	private static String checkFileisCorrectOrNot(String jsonFilePath) throws IOException {
//		String initialJSON = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
//
//		try {
//			JsonElement jsonElement = JsonParser.parseString(initialJSON);
//
//		} catch (Exception e) {
//			int cut = 0;
//			for (int i = 0; i < initialJSON.length(); i++) {
//				if (initialJSON.charAt(i) == '[' || initialJSON.charAt(i) == '{') {
//					cut = i - 1;
//					break;
//
//				}
//			}
//			initialJSON = initialJSON.substring(cut);
//			// System.out.println(initialJSON);
//
//		}
//
//		return initialJSON;
//
//	}
//
//}
