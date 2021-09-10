package com.baracoda.lint_rules.rx.checker

class RxMaybeMethodChecker : RxMethodChecker() {
    override val returnClassNames: List<String> = listOf("Maybe")
    override val suffixes: List<String> = listOf("Maybe")

    override val message: String = """
        [Maybe] returning functions should be named with suffix Maybe. 
        Example: removeAccountMaybe()
    """
}
