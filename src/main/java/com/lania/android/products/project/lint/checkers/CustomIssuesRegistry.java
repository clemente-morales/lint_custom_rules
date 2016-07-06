package com.lania.android.products.project.lint.checkers;

import java.util.Arrays;
import java.util.List;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

public class CustomIssuesRegistry extends IssueRegistry {

    private List<Issue> customIssues = Arrays.asList(
    		BaseResponseParcelableDetector.ISSUE   
    );

    public CustomIssuesRegistry() {
    }
    

    @Override
    public List<Issue> getIssues() {
        return customIssues;
    }

}