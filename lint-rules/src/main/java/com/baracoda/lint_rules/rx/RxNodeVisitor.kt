package com.baracoda.lint_rules.rx

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.JavaContext
import com.baracoda.lint_rules.rx.RxJavaUnconventionalMethodNamingIssue.ISSUE

import com.baracoda.lint_rules.rx.checker.RxCompletableMethodChecker
import com.baracoda.lint_rules.rx.checker.RxMaybeMethodChecker
import com.baracoda.lint_rules.rx.checker.RxMethodChecker
import com.baracoda.lint_rules.rx.checker.RxSingleMethodChecker
import com.baracoda.lint_rules.rx.checker.RxStreamMethodChecker
import com.intellij.psi.PsiClassType
import org.jetbrains.uast.UMethod

@Suppress("UnstableApiUsage")
class RxNodeVisitor(private val context: JavaContext) : UElementHandler() {
    private val methodCheckers = listOf(
        RxSingleMethodChecker(),
        RxCompletableMethodChecker(),
        RxMaybeMethodChecker(),
        RxStreamMethodChecker()
    )

    override fun visitMethod(node: UMethod) {
        val returnClassName = node.returnClassName()

        methodCheckers.firstOrNull { it.isMethodReturningRxType(returnClassName) }
            ?.checkMethod(node)
    }

    private fun RxMethodChecker.checkMethod(node: UMethod) =
        checkMethodNameSuffix(node.pureMethodName())
            .also { methodNameCorrect ->
                if (!methodNameCorrect) {
                    reportIssue(node)
                }
            }

    private fun RxMethodChecker.reportIssue(node: UMethod) {
        context.report(
            issue = ISSUE,
            scopeClass = node,
            location = context.getNameLocation(node),
            message = this.message
        )
    }

    private fun UMethod.returnClassName(): String =
        (returnTypeReference?.type as? PsiClassType)?.className ?: ""

    private fun UMethod.pureMethodName() =
        name.split(KOTLIN_BYTECODE_DELIMITER)[0]

    companion object {
        private const val KOTLIN_BYTECODE_DELIMITER = "$"
    }
}
