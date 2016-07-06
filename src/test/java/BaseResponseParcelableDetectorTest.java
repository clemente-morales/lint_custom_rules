import java.util.Arrays;
import java.util.List;

import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.TextFormat;
import com.lania.android.products.project.lint.checkers.BaseResponseParcelableDetector;

public class BaseResponseParcelableDetectorTest extends AbstractDetectorTest {

	@Override
	protected Detector getDetector() {
		return new BaseResponseParcelableDetector();
	}

	@Override
	protected List<Issue> getIssues() {
		return Arrays.asList(BaseResponseParcelableDetector.ISSUE);
	}

	@Override
	protected String getTestResourceDirectory() {
		return "base_response";
	}

	/**
	 * Test lint without issues with a valid Parcelable implementation in a subclass of BaseResponse.
	 */
	public void testValidParcelableImplementationForBaseResponse() throws Exception {
		String file = "ValidBaseResponse.java";
		String actualMessage = lintFiles(file);
		assertEquals(NO_WARNINGS, actualMessage);
	}

	/**
	 * Test lint with issues with invalid Parcelable implementation in a subclass of BaseResponse.
	 */
	public void testInvalidParcelableImplementationForBaseResponse() throws Exception {
		String file = "InvalidBaseResponse.java";
		String actualMessage = lintFiles(file);
		assertNotNull(actualMessage);
	}

}