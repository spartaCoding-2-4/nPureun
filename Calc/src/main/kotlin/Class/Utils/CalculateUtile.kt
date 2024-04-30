package Class.Utils

import Class.Calculator.Strategy
import java.util.Stack

class CalculateUtile {

    private fun calculate(y: Double, x: Double, oper: String): Double {
        val operation = Strategy.fromSymbol(oper)
            ?: throw IllegalArgumentException("올바르지 않은 연산자 입니다.")

        return operation.running(x, y)
    }

    fun postfixResult(postfix: List<String>) {
        val stack = Stack<Double>()

        try {
            for (token in postfix) {
                if (token in "(+-*/%)") {
                    if (stack.size < 2) throw IllegalArgumentException("올바르지 않은 입력입니다.")
                    val result = calculate(stack.pop(), stack.pop(), token)

                    if (result.isNaN() || result.isInfinite())
                        throw ArithmeticException("올바르지 않은 식입니다.")

                    stack.add(result)
                    continue
                }
                stack.add(token.toDouble())
            }
            if (stack.size != 1) throw ArithmeticException("잘못된 계산입니다.")
            println("계산 결과: ${stack.first()}")
        } catch (e: NumberFormatException) {
            println("올바르지 않은 숫자형식 입니다.")
            return
        } catch (e: RuntimeException) {
            println(e.message)
            return
        }
    }
}