package own_logics;

import static io.appium.java_client.touch.WaitOptions.waitOptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component("LIC9286_PJT1001_PE_NLPd176dc47-9b23-482b-922d-47e51abf212e")
public class Select_Yearr implements Nlp {
	@InputParams({ @InputParam(name = "xPath", type = "java.lang.String"),
			@InputParam(name = "Year", type = "java.lang.String"),
			@InputParam(name = "Swipe Count", type = "java.lang.Integer") })

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
		String se = (String) attributes.get("xPath");
		Integer swipeCount = (Integer) attributes.get("Swipe Count");
		String year = (String) attributes.get("Year");
		AppiumDriver driver = (AppiumDriver) nlpRequestModel.getAndroidDriver();
		int swipeDuration = 250;

		// Your program element business logic goes here ...
		try {
			List<WebElement> listofELe = driver
					.findElements(By.xpath(se));
			WebElement start = listofELe.get(0);
			WebElement end = listofELe.get(listofELe.size() - 2);
			int x = (int) (start.getLocation().x);
			int y = (int) (start.getLocation().y);
			int x1 = (int) (end.getLocation().x);
			int y1 = (int) (end.getLocation().y);

			// PointOption startPoint = PointOption.point(x, y);
			// PointOption endPoint = PointOption.point(x1, y1);
//			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//			org.openqa.selenium.interactions.Sequence swipe = new org.openqa.selenium.interactions.Sequence(finger, 1);
			int count = 0;
			int maxSwipeCount =swipeCount ;
			while (driver.findElements(By.xpath("//android.widget.TextView[@text=" + "'" + year + "'" + "]")).isEmpty()) {
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				org.openqa.selenium.interactions.Sequence swipe = new org.openqa.selenium.interactions.Sequence(finger, 1);
				System.out.println(count);
				swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
				swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				swipe.addAction(new Pause(finger, Duration.ofMillis(swipeDuration)));
				swipe.addAction(finger.createPointerMove(Duration.ofMillis(swipeDuration), PointerInput.Origin.viewport(), x1, y1));
//				swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				driver.perform(Arrays.asList(swipe));
				
				if (++count >= maxSwipeCount) {

					break;
				}
				

			}

			driver.findElement(By.xpath("//android.widget.TextView[@text=" + "'" + year + "'" + "]")).click();
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Year has been selected Successfully");

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			log.info(exceptionAsString);
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to  swipe and select year " + exceptionAsString);

		}

		return nlpResponseModel;
	}
}
