import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.android.tools.lint.detector.api.Issue;
import com.lania.android.products.project.lint.checkers.BaseResponseParcelableDetector;
import com.lania.android.products.project.lint.checkers.CustomIssuesRegistry;

public class CustomIssuesRegistryTest {
	private CustomIssuesRegistry customIssueRegistry;

	@Before
	public void setUp() throws Exception {
		customIssueRegistry = new CustomIssuesRegistry();
	}

	@Test
	public void testNumberOfIssues() throws Exception {
		int size = customIssueRegistry.getIssues().size();
		assertThat(size).isEqualTo(1);
	}

	@Test
	public void testGetIssues() throws Exception {
		List<Issue> actual = customIssueRegistry.getIssues();
		assertThat(actual).contains(BaseResponseParcelableDetector.ISSUE);
	}
}