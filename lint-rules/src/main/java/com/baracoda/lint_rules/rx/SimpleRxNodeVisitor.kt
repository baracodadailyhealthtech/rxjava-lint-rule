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
