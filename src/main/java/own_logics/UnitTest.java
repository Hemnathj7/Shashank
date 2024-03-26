package own_logics;

import java.util.HashMap;
import java.util.Map;

import com.tyss.optimize.nlp.util.NlpRequestModel;

public class UnitTest {
public static void dummy()
	{
		System.out.println("Dummy Line");
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		NonStatic jvs=new NonStatic();
		 NlpRequestModel nlp = new NlpRequestModel();
		Map<String, Object> map =nlp.getAttributes();
		map.put("JsonFilePath", "C:\\Users\\User\\Downloads\\FireFlink JSON POC (2)\\JSON data shared\\Type A\\8009522168@nocash.mobikwik.json");
//jvs.execute()="C:\\Users\\User\\Downloads\\FireFlink JSON POC (2)\\JSON data shared\\Type A\\8347112339@nocash.mobikwik.com.json";
map.put("FolderToStore", "C:\\Users\\User\\Desktop\\");

jvs.execute(nlp);
Runtime rt = Runtime.getRuntime();
rt.exec(new String[] { "cmd.exe", "/c", "start", jvs.excelFilePath });
	}

}
