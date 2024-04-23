import `class`.StackCalculator

class Manager()
{
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

    /**입력받은 문자열 변환함수*/
    private fun convertString(inputString: String): String {
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

    /**사용자와의 대화 함수*/
    private fun inputCalculate(): String
    {
        print("식을 적으세요(앞에 $ 붙이면 연쇄수식 호출 / x= 종료) : ")
        val inputString=readln()
        if(inputString.find { it=='x' }!=null)
            return "Exit"
        else if(inputString.isBlank()) return "-1"
        return convertString(inputString)
    }

    /**공백문자 기준으로 파싱*/
    private fun extractCalculate(inputString: String):List<String>
    {
        return inputString.replace("\\s+".toRegex(), " ").trim().split(" ")
    }

    /**도출된 리스트로 계산식 설정*/
    private fun getResult(calculate: List<String>) {
        val firstVal=0
        val lastVal=2
        val operator=1

        try {
            if(calculate.size!=3/* (x operator y) 구조가 아니면 */)
                throw Error("올바르지 않은 입력 입니다.")
            val x = calculate[firstVal].toDouble()
            val y = calculate[lastVal].toDouble()

            val result : Double =when (calculate[operator]) {
                "+" -> Summation(x, y).calculateResult()
                "-" -> Subtraction(x, y).calculateResult()
                "*" -> Multiplication(x, y).calculateResult()
                "/" -> Division(x, y).calculateResult()
                "%" -> Remainder(x, y).calculateResult()
                else -> {
                    throw Error("올바르지 않은 연산자 입니다.")
                }
            }
            if(result.isNaN()||result.isInfinite()) throw Error("올바르지 않은 식입니다.")
            else println("계산 결과: $result")
        }
        catch (e:NumberFormatException) {
            println("올바르지 않은 숫자형식 입니다.")
        }
        catch (e: Error) {
            println(e.message)
        }
    }

    /** 계산기 메인 함수 */
    fun manual()
    {
        while (true)
        {
            val calculate=inputCalculate()
            if(calculate=="Exit") break
            else if(calculate.first()=='$')
                StackCalculator().postfixCalc(calculate.substring(1))
            else getResult(extractCalculate(calculate))
        }
        println("계산기를 종료합니다.")
    }
}