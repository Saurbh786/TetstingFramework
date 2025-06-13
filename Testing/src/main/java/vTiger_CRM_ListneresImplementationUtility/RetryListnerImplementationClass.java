package vTiger_CRM_ListneresImplementationUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListnerImplementationClass implements IRetryAnalyzer {

	@Override
	public boolean retry(ITestResult result) {
		int count = 0;
		int limitCount = 5;
		if(count<limitCount) {
			count++;
			return true;
		}
		return false;
	}

}
