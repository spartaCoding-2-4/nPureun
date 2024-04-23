class Subtraction(x:Double, y:Double) :Calculator(x,y)
{
    override fun calculateResult() : Double =
        String.format("%.2f",x-y).toDouble()
}