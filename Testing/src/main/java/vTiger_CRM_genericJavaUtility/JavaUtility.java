package vTiger_CRM_genericJavaUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	// Get random number
		public int getRandomNUmber() {
			Random ran = new Random();
			int random = ran.nextInt(5000);
			return random;

		}

		// Capture system date
		public String getSystemDateyyyyMMdd() {
			Date dateobj = new Date();
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			String date = simple.format(dateobj);
			return date;

		}

		// Get the required date
		public String getRequiredDateyyyyMMdd(int days) {
			Date dateobj = new Date();
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			simple.format(dateobj);
			Calendar cal = simple.getCalendar();
			cal.add(Calendar.DAY_OF_MONTH, days);
			String reqDate = simple.format(cal.getTime());
			return reqDate;
		}

		public String getSystemDateAndTimeForScreenshot() {
			String dateAndTime = new Date().toString().replace(" ", "_").replace(":", "_");
			return dateAndTime;
		}

}
