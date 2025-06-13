package Practice_CRM_Test;

import org.testng.annotations.Test;

public class creatOrgTest {
	
	@Test(dataProvider = "productTestData", dataProviderClass = vTiger_CRM_genericDataProviderUtility.DataProviderUtility.class)
	public void readMultipleDataTest(String tcID, String testCaseName, String productName, String productCategory) {
		System.out.println("Product Name is: "+productName);
		System.out.println("Product Category is: "+productCategory);
		System.out.println("saurabh");
	}

}
