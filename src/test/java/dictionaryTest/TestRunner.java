package dictionaryTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DictionaryTester.class);

        // Print failed tests
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.getTestHeader());
        }

        // Print success tests
        System.out.println("[Successful: " + result.wasSuccessful() + "], [Total Tests: " + result.getRunCount() + "], [Fail Tests:" + result.getFailureCount()+"]");
    }
}  