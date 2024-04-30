package Class.Calculator.Override

import Class.Calculator.Calculator

class Remainder() : Calculator
{
    override fun calculateResult(x:Double, y:Double): Double =
        if(y==0.0) Double.NaN
        else String.format("%.2f",x%y).toDouble()
}