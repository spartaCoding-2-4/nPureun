package `class`

class CalculateUtile {

    /**계산함수*/
    private fun calculate(x: Double, y: Double, oper: String):Double
    {
        var result =0.0
        try {
            result=when (oper) {
                "+" -> Summation().calculateResult(x, y)
                "-" -> Subtraction().calculateResult(x, y)
                "*" -> Multiplication().calculateResult(x, y)
                "/" -> Division().calculateResult(x, y)
                "%" -> Remainder().calculateResult(x, y)
                else -> {
                    throw Error("올바르지 않은 연산자 입니다.")
                }
            }
        }
        catch (e:NumberFormatException) {
            println("올바르지 않은 숫자형식 입니다.")
        }
        catch (e: Error) {
            println(e.message)
        }
        return result
    }

    /**연쇄수식 계산*/
    fun postfixResult(postfix: List<String>) {
        val operators = listOf('(', ')', '+', '-', '*', '/', '%')

        val stack = mutableListOf<Double>()
        try {
            for (token in postfix) {
                when (token) {
                    in operators.toString() -> {
                        if(stack.size<2) throw Error("올바르지 않은 입력입니다.")
                        val right = stack.removeAt(stack.lastIndex)
                        val left = stack.removeAt(stack.lastIndex)
                        val result = calculate(left,right,token)
                        if(result.isNaN()||result.isInfinite())
                            throw Error("올바르지 않은 식입니다.")
                        else stack.add(result)
                    }
                    else -> {
                        stack.add(token.toDouble())
                    }
                }
            }
            if (stack.size != 1) throw Error("잘못된 계산입니다.")
            println("계산 결과: ${stack.first()}")
        } catch (e: Error) {
            println(e.message)
        }
        catch (e:NumberFormatException) {
            println("올바르지 않은 숫자형식 입니다.")
        }
    }

    /**단일수식 계산*/
    fun singleResult(calculate: List<String>) {
        val firstVal=0
        val lastVal=2
        val operator=1

        var result=0.0
        try {
            if (calculate.size != 3/* (x operator y) 구조가 아니면 */)
                throw Error("올바르지 않은 입력 입니다.")
            val x = calculate[firstVal].toDouble()
            val y = calculate[lastVal].toDouble()
            result=calculate(x,y,calculate[operator])
            if(result.isNaN()||result.isInfinite()) throw Error("올바르지 않은 식입니다.")
            else println("계산 결과: $result")
        }
        catch (e: Error) {
            println(e.message)
        }
        catch (e:NumberFormatException) {
            println("올바르지 않은 숫자형식 입니다.")
        }
    }
}