package `class`

class Summation() : Calculator()
{
    override fun calculateResult(x:Double, y:Double):Double =
        String.format("%.2f",x+y).toDouble()
}