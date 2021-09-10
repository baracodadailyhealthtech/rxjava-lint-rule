package com.baracoda.lint_rules.rx.checker

abstract class RxMethodChecker {
    /**
     * List of methods return class names to be checked
     */
    protected abstract val returnClassNames: List<String>
    /**
     * List of correct suffixes for given [returnClassNames]
     */
    protected abstract val suffixes: List<String>
    /**
     * Specific message about why the current method name is incorrect
     */
    abstract val message: String

    fun isMethodReturningRxType(returnClassName: String): Boolean =
        returnClassNames.contains(returnClassName)

    fun checkMethodNameSuffix(methodName: String): Boolean =
        suffixes.any { methodName.endsWith(it) }
}
