package `class`
import Division
import Multiplication
import Remainder
import Subtraction
import Summation
import java.util.Stack

//계산식 템플릿
// $(w-2ww + w( - 5ww) ) * ww3-(ww-8/ 2)w+(asd10ww%ww3)*ㅈㅈㅈ4d.ㄴ히ㅑ0+ w2.5ww-w..1w.w2
// $(-2+(-5))*3-(-8/2)+(10%3)*4.0+2.5-1.2

class StackCalculator {
   private val operators = listOf('(', ')', '+', '-', '*', '/', '%')
   fun convertCalc(inputString: String) {
      val stack = Stack<String>()
      val postfix = mutableListOf<String>()
      inputString.split(" ").forEach { token ->
         if (token.isNotBlank()) {
            if (token in operators.toString()) {
               if (token == "(") stack.add(token)
               else if (token == ")") {
                  while (stack.isNotEmpty()) {
                     val op = stack.pop()
                     if (op == "(") break
                     else postfix.add(op)
                  }
               } else {
                  while (stack.isNotEmpty()) {
                     if (getPriority(token) <= getPriority(stack.peek()))
                        postfix.add(stack.pop())
                     else break
                  }
                  stack.add(token)
               }
            } else postfix.add(token)
         }
      }
      while (stack.isNotEmpty()) postfix.add(stack.pop())
      println("후위연산 변환: $postfix")
      postfixCalc(postfix)
   }

   private fun getPriority(operator: String): Int =
      when (operator) {
         "(", ")" -> 0
         "+", "-" -> 1
         "*", "/", "%" -> 2
         else -> -1
      }

   private fun postfixCalc(postfix: List<String>) {
      val stack = mutableListOf<Double>()
      try {
         for (token in postfix) {
            when (token) {
               in operators.toString() -> {
                  if(stack.size<2) throw Error("올바르지 않은 입력입니다.")
                  val right = stack.removeAt(stack.lastIndex)
                  val left = stack.removeAt(stack.lastIndex)
                  val result = when (token) {
                     "+" -> Summation(left, right).calculateResult()
                     "-" -> Subtraction(left, right).calculateResult()
                     "*" -> Multiplication(left, right).calculateResult()
                     "/" -> Division(left, right).calculateResult()
                     "%" -> Remainder(left, right).calculateResult()
                     else -> throw Error("잘못된 문자가 있습니다.")
                  }
                  stack.add(result)
               }

               else -> {
                  stack.add(token.toDouble())
               }
            }
         }
         if (stack.size != 1) {
            throw Error("잘못된 계산입니다.")
         }
         if(stack.first().isNaN()||stack.first().isInfinite()) throw Error("올바르지 않은 식입니다.")
         println("계산 결과: ${stack.first()}")
      } catch (e: Error) {
         println(e.message)
      }
   }
}
