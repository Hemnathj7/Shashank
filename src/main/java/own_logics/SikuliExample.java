package own_logics;
import org.apache.commons.math3.geometry.partitioning.Region.Location;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SikuliExample {



	    public static void main(String[] args) throws FindFailed, InterruptedException {
	        Screen screen = new Screen();
	        String imagePath = "C:\\Users\\User\\Downloads\\UserSpecifiedSystem_SAP.png"; // Replace with your image path
	        Thread.sleep(3000);
	        // Find the image on the screenSikuli_Type
	        Pattern imagePattern = new Pattern(imagePath);
	        screen.doubleClick();
	      //  screen.click(imagePattern.targetOffset(58, 7));
            screen.type("Sikuli_Type");
	        System.out.println("Clicked on the image!");
	    }
	}

