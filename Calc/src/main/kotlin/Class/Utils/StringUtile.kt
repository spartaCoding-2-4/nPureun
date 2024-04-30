package Class.Utils

import java.util.*

class StringUtile {
    
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
            if (it == '.' &&
                (i - 1 in calc.indices && calc[i - 1].isDigit()) &&
                (i + 1 in calc.indices && calc[i + 1].isDigit())
            ) {
                str += it
            } else if (it in operators) {
                str += " "
                if (i == 0 && it == '-') str += it
                else if (it == '-' && calc[i - 1] in operators)
                    str += it
                else str += "$it "
            } else if (it.isDigit()) {
                str += it
            }
        }
        println("문자열 포맷: $str")

        //여기서 사용자 설정 다시 받음
        return if (str.isBlank()) "-1" else
            if (inputString.first() == '$') "$$str" else str
    }

    fun getParsingList(inputString: String):List<String>
    {
        return inputString.replace("\\s+".toRegex(), " ").trim().split(" ")
    }

    fun postfixConvert(inputString: String):List<String> {
        val operators = listOf('(', ')', '+', '-', '*', '/', '%')

        val stack = Stack<String>()
        val postfix = mutableListOf<String>()
        inputString.split(" ").forEach { token ->
            if (token.isNotBlank()) {
                if (token in operators.toString()) {
                    if (token == "(") stack.add(token)
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
                } else postfix.add(token)
            }
        }
        while (stack.isNotEmpty()) postfix.add(stack.pop())
        println("후위연산 변환: $postfix")
        return postfix
    }

    private fun getPriority(operator: String): Int =
        when (operator) {
            "(", ")" -> 0
            "+", "-" -> 1
            "*", "/", "%" -> 2
            else -> -1
        }
}