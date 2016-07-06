package com.lania.android.products.project.lint.checkers;

import java.io.File;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Location;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.TextFormat;

import lombok.ast.AstVisitor;
import lombok.ast.ClassDeclaration;
import lombok.ast.ForwardingAstVisitor;
import lombok.ast.Node;
import lombok.ast.TypeReference;

public class BaseResponseParcelableDetector extends Detector implements Detector.JavaScanner {
	private static final Class<? extends Detector> DETECTOR_CLASS = BaseResponseParcelableDetector.class;
	private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;

	private static final Implementation IMPLEMENTATION = new Implementation(DETECTOR_CLASS, DETECTOR_SCOPE);

	private static final String ISSUE_ID = "InvalidParcelableImplementation";
	private static final String ISSUE_DESCRIPTION_FORMAT = "Invalid BaseResponse Parcelable Implementation in class %s";
	private static final String ISSUE_DESCRIPTION = "Invalid BaseResponse Parcelable Implementation in class";
	private static final String ISSUE_EXPLANATION = "If you extends BaseResponse you have to override the Parcelable interface";
	private static final Category ISSUE_CATEGORY = Category.CORRECTNESS;
	private static final int ISSUE_PRIORITY = 8;
	private static final Severity ISSUE_SEVERITY = Severity.ERROR;
	
	public static Issue ISSUE = Issue.create(ISSUE_ID, ISSUE_DESCRIPTION, ISSUE_EXPLANATION, ISSUE_CATEGORY,
			ISSUE_PRIORITY, ISSUE_SEVERITY, IMPLEMENTATION);

	public BaseResponseParcelableDetector() {

	}

	@Override
	public boolean appliesTo(@NonNull Context context, @NonNull File file) {
		return true;
	}

	@Override
	public EnumSet<Scope> getApplicableFiles() {
		return Scope.JAVA_FILE_SCOPE;
	}

	@Override
	public List<Class<? extends Node>> getApplicableNodeTypes() {
		return Arrays.<Class<? extends Node>>asList(ClassDeclaration.class);
	}

	@Override
	public AstVisitor createJavaVisitor(@NonNull JavaContext context) {
		return new BaseResponseVisitor(context);
	}

	private static class BaseResponseVisitor extends ForwardingAstVisitor {
		private static final String PARCELABLE_CREATOR_FIELD_NAME = "CREATOR";
		private static final String BASE_RESPONSE_TYPE_NAME = "BaseResponse";
		private final JavaContext context;

		public BaseResponseVisitor(JavaContext context) {
			this.context = context;
		}

		@Override
		public boolean visitClassDeclaration(ClassDeclaration node) {

			TypeReference typeReference = node.astExtending();

			if (typeReference == null)
				return true;

			String parentClass = typeReference.toString();

			if (BASE_RESPONSE_TYPE_NAME.equalsIgnoreCase(parentClass)) {
				if (!isBaseResponseSubclassImplementingParcelable(node)) {
					Issue issue = buildIncompleteParcelableImplementationIssue(node);
					context.report(issue, Location.create(context.file), issue.getBriefDescription(TextFormat.TEXT));
					return true;
				}
			}

			return false;
		}

		private Issue buildIncompleteParcelableImplementationIssue(ClassDeclaration node) {
			String issueDescription = String.format(ISSUE_DESCRIPTION_FORMAT, node.astName().toString());
			Issue issue = Issue.create(ISSUE_ID, issueDescription, ISSUE_EXPLANATION, ISSUE_CATEGORY,
					ISSUE_PRIORITY, ISSUE_SEVERITY, IMPLEMENTATION);
			return issue;
		}

		private boolean isBaseResponseSubclassImplementingParcelable(ClassDeclaration node) {
			String bodyResponse = node.astBody().toString();
			return bodyResponse != null && bodyResponse.contains(PARCELABLE_CREATOR_FIELD_NAME);
		}
	}
}
