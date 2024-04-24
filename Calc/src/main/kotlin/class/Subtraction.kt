package `class`

class Subtraction() : Calculator()
{
    override fun calculateResult(x:Double, y:Double) : Double =
        String.format("%.2f",x-y).toDouble()
}