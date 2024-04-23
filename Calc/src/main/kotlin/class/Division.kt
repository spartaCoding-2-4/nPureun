class Division(x:Double, y:Double) :Calculator(x,y) {
    override fun calculateResult() : Double =
        if(x==0.0) Double.NaN
        else String.format("%.2f", x / y).toDouble()
}