package Class.Utils

import java.util.*

class StringUtile {
    private fun getPriority(operator: String): Int =
        when (operator) {
            "(", ")" -> 0
            "+", "-" -> 1
            "*", "/", "%" -> 2
            else -> -1
        }
    
    private fun stringFilter(inputString: String):String{
        val calc = StringBuilder()

        val operators = listOf('(', ')', '+', '-', '*', '/', '%')
        inputString.forEach {
            if(it.isDigit()||it in operators || it=='.')
                calc.append(it.toString())
        }

        println("문자열 필터링: $calc")
        return calc.toString()
    }

    fun convertString(inputString: String): String {
        val calc=stringFilter(inputString)
        var str = ""

        val operators = listOf('(', ')', '+', '-', '*', '/', '%')
        calc.forEachIndexed { i, it ->
            if (it == '.' &&//소수점 판별. 소수의 기본 형태는 0.0 이다.
                (i - 1 in calc.indices && calc[i - 1].isDigit()) &&
                (i + 1 in calc.indices && calc[i + 1].isDigit())) {
                str += it
            }
            else if (it in operators) {//연산자 판별
                str+= " "+
                if ((it == '-') && ((i == 0) || (calc[i - 1] in ("+-*/%")))) it
                else "$it "
            }
            else if (it.isDigit()) {//숫자 판별
                str += it
            }
        }
        println("문자열 포맷: $str")

        return str.ifBlank { "-1" }
    }

    fun postfixConvert(inputString: String):List<String> {
        val operators = listOf('(', ')', '+', '-', '*', '/', '%')

        val stack = Stack<String>()
        val postfix = mutableListOf<String>()
        inputString.split(" ").filter { it.isNotBlank() }.forEach { token ->
            if (token in operators.toString()) {
                if (token == "(")
                    stack.add(token)
                else if (token == ")") {
                    while (stack.isNotEmpty()) {
                        val op = stack.pop()
                        if (op == "(") break
                        else postfix.add(op)
                    }
                } else {
                    while (stack.isNotEmpty()) {
                        if (getPriority(token) <= getPriority(stack.peek()))
                            postfix.add(stack.pop())
                        else break
                    }
                    stack.add(token)
                }
            }
            else postfix.add(token)
        }
        while (stack.isNotEmpty()) postfix.add(stack.pop())
        println("후위연산 변환: $postfix")
        return postfix
    }
}