class Remainder(x:Double, y:Double) :Calculator(x,y)
{
    override fun calculateResult(): Double =
        if(y==0.0) Double.NaN
        else String.format("%.2f",x%y).toDouble()
}