package Class.Calculator

import Class.Calculator.Override.*

enum class Strategy(val symbol: String, private val strategy: Calculator){
    SUM("+", Summation()),
    SUB("-", Subtraction()),
    MUL("*", Multiplication()),
    DIV("/", Division()),
    REM("%", Remainder());

    companion object{
        fun fromSymbol(symbol: String): Strategy? =
            Strategy.entries.find { it.symbol == symbol }
    }

    fun running(x:Double, y:Double):Double=strategy.calculateResult(x,y)
}