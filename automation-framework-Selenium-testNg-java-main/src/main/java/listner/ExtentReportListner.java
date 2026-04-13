package listner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import DriverRepo.ConfigDriver;

public class ExtentReportListner implements ISuiteListener, ITestListener {

	private static final Logger log = LogManager.getLogger(ExtentReportListner.class);
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onStart(ISuite suite) {
		ExtentSparkReporter spark = new ExtentSparkReporter(Constants.extentReportsPath);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Test Report");
		spark.config().setReportName("LambdaTest Ecommerce - Test Results");

		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester", "Dhiraj");

		log.info("Extent Report initialized. Suite started: {}", suite.getName());
	}

	@Override
	public void onFinish(ISuite suite) {
		if (extent != null) {
			extent.flush();
		}
		log.info("Suite finished: {}. Extent Report flushed.", suite.getName());
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getDescription(),
				result.getMethod().getDescription());
		test.set(extentTest);
		log.info("Test started: {}", result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed");
		log.info("Test PASSED: {}", result.getName());
		System.out.println("Test Passed: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.error("Test FAILED: {} | Reason: {}", result.getName(), result.getThrowable().getMessage());
		try {
			String base64Screenshot = ((TakesScreenshot) ConfigDriver.getDriver()).getScreenshotAs(OutputType.BASE64);
			test.get().fail(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
			log.info("Screenshot captured for failed test: {}", result.getName());
		} catch (Exception e) {
			test.get().fail(result.getThrowable());
			log.warn("Could not capture screenshot: {}", e.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip(result.getThrowable());
		log.warn("Test SKIPPED: {}", result.getName());
		System.out.println("Test Skipped: " + result.getName());
	}

	public static ExtentTest getTest() {
		return test.get();
	}
}