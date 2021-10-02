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
import com.android.tools.lint.detector.api.JavaContext
import com.baracoda.lint_rules.rx.RxJavaUnconventionalMethodNamingIssue.ISSUE
import com.intellij.psi.PsiClassType
import org.jetbrains.uast.UMethod

@Suppress("UnstableApiUsage")
class SimpleRxNodeVisitor(private val context: JavaContext) : UElementHandler() {
    override fun visitMethod(node: UMethod) {
        if (node.returnClassName() == "Single") {
            if (!node.name.endsWith("Once")) {
                reportIssue(node)
            }
        }
    }

    private fun UMethod.returnClassName(): String =
        (returnTypeReference?.type as? PsiClassType)?.className ?: ""

    private fun reportIssue(node: UMethod) {
        context.report(
            issue = ISSUE,
            scopeClass = node,
            location = context.getNameLocation(node),
            message = """
                [Single] returning functions should be named with suffix Once. 
                Example: removeAccountOnce()
            """
        )
    }
}
