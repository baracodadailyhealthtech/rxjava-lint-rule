/*
 * Copyright (C) 2021 Baracoda Daily Healthtech
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */

package com.baracoda.lint_rules.rx

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UMethod

@Suppress("UnstableApiUsage")
object RxJavaUnconventionalMethodNamingIssue {
    /**
     * The fixed id of the issue
     */
    private const val ID = "RxJavaUnconventionalMethodNamingIssue"

    /**
     * The priority, a number from 1 to 10 with 10 being most important/severe
     */
    private const val PRIORITY = 7

    /**
     * Description short summary (typically 5-6 words or less), typically describing
     * the problem rather than the fix (e.g. "Missing minSdkVersion")
     */
    private const val DESCRIPTION = "Wrong Rx function name suffix."

    /**
     * A full explanation of the issue, with suggestions for how to fix it
     */
    private const val EXPLANATION = """
        Function is wrongly named. Please make sure that function name matches rules described in 
        https://upday.github.io/blog/reactive_frustrations_1/#reasoning-about-the-code
    """

    /**
     * The associated category, if any @see [Category]
     */
    private val CATEGORY = Category.CUSTOM_LINT_CHECKS

    /**
     * The default severity of the issue
     */
    private val SEVERITY = Severity.WARNING

    val ISSUE = Issue.create(
        ID,
        DESCRIPTION,
        EXPLANATION,
        CATEGORY,
        PRIORITY,
        SEVERITY,
        Implementation(RxIssueDetector::class.java, Scope.JAVA_FILE_SCOPE)
    )

    class RxIssueDetector : Detector(), Detector.UastScanner {
        override fun getApplicableUastTypes(): List<Class<out UElement>> =
            listOf(UMethod::class.java)

        override fun createUastHandler(context: JavaContext): UElementHandler =
            RxNodeVisitor(context)
    }
}
