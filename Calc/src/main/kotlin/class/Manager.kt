package `class`

class Manager()
{
    private fun inputCalculate(): String
    {
        print("식을 적으세요(앞에 $ 붙이면 연쇄수식 호출 / x= 종료) : ")
        val inputString=readln()
        return if('x' == inputString.first()) "Exit" else inputString
    }

    /** 계산기 메인 함수 */
    fun manual()
    {
        val stringUt= StringUtile()
        val calcUt= CalculateUtile()
        while (true)
        {
            var calculate=inputCalculate()
            if(calculate=="Exit") break

            calculate=stringUt.convertString(calculate)
            if(calculate.first()=='$')
                calcUt.postfixResult(stringUt.postfixConvert(calculate.substring(1)))
            else calcUt.singleResult(stringUt.getParsingList(calculate))
        }
        println("계산기를 종료합니다.")
    }
}