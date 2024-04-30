package Class.Calculator.Override

import Class.Calculator.Calculator

class Division() : Calculator{
    override fun calculateResult(x:Double, y:Double) : Double =
        if(x==0.0) Double.NaN
        else String.format("%.2f", x / y).toDouble()
}