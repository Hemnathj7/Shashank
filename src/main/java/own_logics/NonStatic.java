package own_logics;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

//import org.apache.commons.io.input.BufferedFileChannelInputStream;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("LIC9286_PJT1001_PE_NLP3db156ae-3de9-4254-8aa9-916c7d7a738f")
public class NonStatic implements Nlp {
    @InputParams({@InputParam(name = "JsonFilePath", type = "java.lang.String"), @InputParam(name = "FolderToStore", type = "java.lang.String")})
    

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
    {
  	  System.gc();
  	  Runtime.getRuntime().gc();
    }
    @Override
    public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
  	  System.gc();
  	  Runtime.getRuntime().gc();
        NlpResponseModel nlpResponseModel = new NlpResponseModel();
        Map<String, Object> attributes = nlpRequestModel.getAttributes();
        jsonFilePath = (String) attributes.get("JsonFilePath");
        String FolderToStore = (String) attributes.get("FolderToStore");
        excelFilePath=FolderToStore;

        try {
      	  System.gc();
      	  Runtime.getRuntime().gc();
      	  list=List.of("\"\"","null");
      	//  excelFilePath="";
      	  keyCellIndex=0;
      	  valueCellIndex=2;
      	  labelCellIndex=1;
      	  cellCount=0;
      	  actCellCount=0;
      	  flag=true;
      	  sh=null;
      	  CellStyle  styleNULL = null,styleHeader = null,styleValue=null;
      	  
      	  
      	  excelFilePath=FolderToStore;
      	  checkJson();
      	  nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully Converted to JSON to Excel");
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			log.info(exceptionAsString);
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed Converted to JSON to Excel"+ exceptionAsString);
		}

        System.gc();
  	  Runtime.getRuntime().gc();
        return nlpResponseModel;
    }
	
	 List<String> list=List.of("\"\"","null");
	 int cellCount=0;
	 int actCellCount=0;
	 int keyCellIndex=0;
	 int valueCellIndex=2;
	 int labelCellIndex=1;
	 boolean flag=true;
		Sheet sh=null;
		 CellStyle styleNULL = null, styleHeader = null,styleValue=null;
		 String excelFilePath="";
				 //"./output/";
		  String jsonFilePath = "";
				  //"C:\\Users\\User\\Downloads\\JSON data shared\\Type a\\9022133610@nocash.mobikwik.com.json";
		
	public  void checkJson() {
		
		NonStatic f=new NonStatic();
		
		 Map<String, List<String>> map=new LinkedHashMap<>();
		
		
		String[] pathArr = jsonFilePath.split("\\\\");
		String excelFileName = pathArr[pathArr.length-1].replace(".json", ".xlsx");
		try {
			long startTime = System.currentTimeMillis();
	
			String initialJSON = new String(Files.readAllBytes(Paths.get(jsonFilePath)), StandardCharsets.UTF_8);
			if(initialJSON.getClass().toString().contains("String")){
				int cut = 0;
				for (int i = 0; i < initialJSON.length(); i++) {
					if (initialJSON.charAt(i) == '[' || initialJSON.charAt(i) == '{') {
						cut = i - 1;
						break;

					}
				}
				if(cut>=0)
					initialJSON = initialJSON.substring(cut);
			}
			String  jsonContent=initialJSON;
			//	String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)), StandardCharsets.UTF_8);
				String unescapedString = jsonContent.replace("\"{", "{").replace("}\"", "}").replace("\\\"", "\"");
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode root = objectMapper.readTree(unescapedString);
			
			f.extractKeyValue(root, "",map);
		//System.out.println(map);
			Map<String, List<String>> m=new TreeMap<>(map);
			System.out.println(m);
			Map<String, List<String>> fm=new LinkedHashMap<>();
			for(Entry<String, List<String>> entry:m.entrySet()) {
				String index=entry.getKey().substring(0,3).replaceAll("[^\\d]", "");
				String spath=entry.getKey().replaceAll("\\[\\d+\\]", "[*]");
				 List<String> list = fm.get(spath);
			     if (list==null) list=new ArrayList<>();	
			            list.add(index+entry.getValue().toString());
				fm.put(spath, list);
	         }
			//System.out.println(fm);
			
			f.writeInExcel(fm, excelFileName,f,excelFilePath);
			
			double timeInSec=(System.currentTimeMillis()-startTime)/1000;
			//System.out.println("Total Execution Time ::::::: "+timeInSec+" sec");

			//System.out.println("Total Cell count ::::::: "+(f.actCellCount));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void extractKeyValue(JsonNode node, String path, Map<String, List<String>> map ) {
        if (node.isObject()) {
            node.fields().forEachRemaining(entry -> {
                String key = entry.getKey();
                JsonNode value = entry.getValue();
                String newPath = path.isEmpty() ? key : path + "." + key;
                extractKeyValue(value, newPath,map);
            });
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                JsonNode arrayElement = node.get(i);
                extractKeyValue(arrayElement, path + "[" + i + "]",map);
            }
        } else {
            //System.out.println(path + ": " + node);
            String resultPath = path.substring(0,3)+path.substring(3).replaceAll("\\[\\d+\\]", "[*]");
            
            List<String> list = map.get(resultPath );
     if (list==null) list=new ArrayList<>();	
	
            list.add(node.toString());
            map.put(resultPath,list );
        }
    }
	
	public void writeInputPage(String excelFilePath, String sheetName, Map<String, List<String>> fmap) throws Exception {
		FileInputStream fis = new FileInputStream(excelFilePath);
        Workbook wb = new XSSFWorkbook(fis); 
    	  
    	   Sheet shPre = wb.createSheet(sheetName);
		
		CellStyle styleValue = wb.createCellStyle();
		styleValue.setBorderTop(BorderStyle.THIN);
		styleValue.setBorderBottom(BorderStyle.THIN);
		styleValue.setBorderLeft(BorderStyle.THIN);
		styleValue.setBorderRight(BorderStyle.THIN);
		
		CellStyle styleHeader=wb.createCellStyle();
		styleHeader.setBorderTop(BorderStyle.MEDIUM);
		styleHeader.setBorderBottom(BorderStyle.MEDIUM);
		styleHeader.setBorderLeft(BorderStyle.MEDIUM);
		styleHeader.setBorderRight(BorderStyle.MEDIUM);  
		
		Font font = wb.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(true);
		styleHeader.setFont(font);
		styleHeader.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		CellStyle styleNULL=wb.createCellStyle();
		styleNULL.setBorderTop(BorderStyle.THIN);
		styleNULL.setBorderBottom(BorderStyle.THIN);
		styleNULL.setBorderLeft(BorderStyle.THIN);
		styleNULL.setBorderRight(BorderStyle.THIN);
		styleNULL.setFillForegroundColor(IndexedColors.RED.getIndex());
		styleNULL.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		int position=1;
		Cell a = shPre.createRow(0).createCell(0);
		a.setCellValue("Position");
		a.setCellStyle(styleHeader);
		Cell b = shPre.getRow(0).createCell(1);
		b.setCellValue("Label");
		b.setCellStyle(styleHeader);
		Cell c = shPre.getRow(0).createCell(2);
		c.setCellValue("JSON PATH");
		c.setCellStyle(styleHeader);
		for(Entry<String, List<String>> entry:fmap.entrySet()) {
			String key = entry.getKey(); 
			String[] labelArr = key.replace("[*]", "").split("\\.");
			String label=labelArr[labelArr.length-1];
			Cell x = shPre.createRow(position).createCell(0);
			x.setCellValue(position);
			x.setCellStyle(styleValue);
			Cell y = shPre.getRow(position).createCell(1);
			y.setCellValue(label);
			y.setCellStyle(styleValue);
			Cell z = shPre.getRow(position).createCell(2);	
			z.setCellValue(key)
;
			z.setCellStyle(styleValue);
			position++;
		}
		wb.write(new FileOutputStream(excelFilePath));
		wb.close();
	}
	
	public Workbook openWorkBook(String excelFilePath, String sheetName,boolean flagSheet) throws Exception {
		 sh=null;
		   Workbook wb = null;
		  styleNULL = null;
		  styleHeader = null;
		  styleValue=null;
		  System.gc();
			FileInputStream fis = new FileInputStream(excelFilePath);
	         wb = new XSSFWorkbook(fis); 
	    	  
	    	 if(flagSheet) {
	    		 sh = wb.createSheet(sheetName);
	    	 }else {
	    		 sh = wb.getSheet(sheetName);
	    	 }
			
			 styleValue = wb.createCellStyle();
			styleValue.setBorderTop(BorderStyle.THIN);
			styleValue.setBorderBottom(BorderStyle.THIN);
			styleValue.setBorderLeft(BorderStyle.THIN);
			styleValue.setBorderRight(BorderStyle.THIN);
			
			 styleHeader=wb.createCellStyle();
			styleHeader.setBorderTop(BorderStyle.MEDIUM);
			styleHeader.setBorderBottom(BorderStyle.MEDIUM);
			styleHeader.setBorderLeft(BorderStyle.MEDIUM);
			styleHeader.setBorderRight(BorderStyle.MEDIUM);  
			
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setBold(true);
			styleHeader.setFont(font);
			styleHeader.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			 styleNULL=wb.createCellStyle();
			styleNULL.setBorderTop(BorderStyle.THIN);
			styleNULL.setBorderBottom(BorderStyle.THIN);
			styleNULL.setBorderLeft(BorderStyle.THIN);
			styleNULL.setBorderRight(BorderStyle.THIN);
			styleNULL.setFillForegroundColor(IndexedColors.RED.getIndex());
			styleNULL.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			return wb;
		
	}
	
	
public void writeOutputPage( String label, StringBuilder value) throws Exception {
	 
Cell headerCell,labelCell=null;
		
		try { 
			headerCell= sh.getRow(keyCellIndex).createCell(cellCount);
			}catch (Exception e) {
			 headerCell= sh.createRow(keyCellIndex).createCell(cellCount);
		}
		
		try {
			labelCell= sh.getRow(labelCellIndex).createCell(cellCount);
		}catch (Exception e) {
			labelCell= sh.createRow(labelCellIndex).createCell(cellCount);
		}
		
		headerCell.setCellValue(actCellCount+1);
		headerCell.setCellStyle(styleHeader);

		labelCell.setCellValue(label);
		labelCell.setCellStyle(styleHeader);
		//System.out.println(actCellCount+1);
		
			checkValueNullable(value,sh,styleValue,styleNULL);
		
	}
	public void saveData(Workbook wb,String excelFilePath) throws Exception {
		wb.write(new FileOutputStream(excelFilePath));
		wb.close();
	}
	
	
	public  void writeInExcel(Map<String, List<String>> fm,String excelFileName ,NonStatic fetchData, String excelFilePath) throws Exception {
		excelFilePath=excelFilePath+excelFileName;
		new XSSFWorkbook().write(new FileOutputStream(excelFilePath));
		String sheet="Output";
		String sheetPre="Input";
		
		fetchData.writeInputPage(excelFilePath, sheetPre, fm);
		
		int limiter=5000;
		int target=limiter;
		int count=0; 
		Workbook wb = null;
		for(Entry<String, List<String>> entry:fm.entrySet()) {
			String key = entry.getKey(); 
			StringBuilder value = new StringBuilder(entry.getValue().toString());
			String[] labelArr = key.replace("[*]", "").split("\\.");
			String label=labelArr[labelArr.length-1];
			
			if(cellCount==10000) {
				cellCount=0;
//				target=limiter;
				fetchData.saveData(wb,excelFilePath);
				count++;
				wb=null;
			}
			if (cellCount==0) {
				System.gc();
				wb=fetchData.openWorkBook(excelFilePath, sheet+count,true);
			}
			
				if(cellCount==target) {
				fetchData.saveData(wb,excelFilePath);
				wb=null;
				System.gc();
				wb=fetchData.openWorkBook(excelFilePath, sheet+count,false);
//				target+=limiter;
				}
			
		
			
			fetchData.writeOutputPage(label, value);
		cellCount++;
		actCellCount++;
		
		}
		
		fetchData.saveData(wb,excelFilePath);
		//Desktop.get//Desktop().open(new File(excelFilePath));
	}
	public void checkValueNullable(StringBuilder value, Sheet sh, CellStyle styleValue, CellStyle styleNull) {
		 List<String> arr = List.of(value.substring(1,value.length()-2).split("],"));
		//System.out.println(arr);
		 for (int i = 0; i < arr.size(); i++) {
			 List<String> innArr = List.of(arr.get(i).split("\\d+\\[")[1].split(","));
		//System.out.println(innArr);
			 for (int j = 0; j < innArr.size(); j++) {
		Cell valueCell=null;
		 String d = innArr.get(j).trim();
			try{
				valueCell=sh.getRow(valueCellIndex+j).createCell(cellCount);
			}catch (Exception e) {
				valueCell=sh.createRow(valueCellIndex+j).createCell(cellCount);
			}
			valueCell.setCellValue(d);
			if(!list.contains(d)) {
				valueCell.setCellStyle(styleValue);
			}else {
				valueCell.setCellStyle(styleNull);
			}
		}
		}
	}
}