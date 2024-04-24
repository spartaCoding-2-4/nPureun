package `class`

//계산식 템플릿
// $(w-2ww + w( - 5ww) ) * ww3-(ww-8/ 2)w+(asd10ww%ww3)*ㅈㅈㅈ4d.ㄴ히ㅑ0+ w2.5ww-w..1w.w2
// $(-2+(-5))*3-(-8/2)+(10%3)*4.0+2.5-1.2
class Manager()
{
    /**사용자와의 대화 함수*/
    private fun inputCalculate(): String
    {
        print("식을 적으세요(앞에 $ 붙이면 연쇄수식 호출 / x= 종료) : ")
        val inputString=readln()
        return if('x' == inputString.first()) "Exit" else inputString
    }

    /** 계산기 메인 함수 */
    fun manual()
    {
        //시퀀스를 관리는 여기서 이루어지니까,
        //다른 함수가 시퀀스에 영향을 못주도록
        //유틸클래스를 함수 내부에서 호출
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