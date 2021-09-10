package com.baracoda.lint_rules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.baracoda.lint_rules.rx.RxJavaUnconventionalMethodNamingIssue

class CustomLintRegistry : IssueRegistry() {
    override val issues = listOf(RxJavaUnconventionalMethodNamingIssue.ISSUE)

    override val api: Int = CURRENT_API

    // this should match our AGP version check the Api.kt file from Google source to check the version to use
    // otherwise our custom rules are skip
    override val minApi: Int = 6
}
