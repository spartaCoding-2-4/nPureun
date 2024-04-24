package `class`

class StringUtile {

    /**의미없는 문자 필터링*/
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

    /**입력받은 문자열 변환*/
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

    /**공백문자 기준으로 파싱*/
    fun getParsingList(inputString: String):List<String>
    {
        return inputString.replace("\\s+".toRegex(), " ").trim().split(" ")
    }
}