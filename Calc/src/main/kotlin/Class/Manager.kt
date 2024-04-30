package Class

import Class.Utils.CalculateUtile
import Class.Utils.StringUtile

class Manager() {
    private fun inputCalculate(): String {
        print("식을 적으세요(x= 종료) : ")
        val inputString = readln()
        return inputString
    }

    /** 계산기 메인 함수 */
    fun manual() {
        val stringUt = StringUtile()
        val calcUt = CalculateUtile()

        while (true) {
            var calculate = inputCalculate()
            if (calculate.first() == 'x') break

            calculate = stringUt.convertString(calculate)
            calcUt.postfixResult(stringUt.postfixConvert(calculate))
        }
        println("계산기를 종료합니다.")
    }
}