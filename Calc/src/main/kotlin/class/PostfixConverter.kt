package `class`
import java.util.Stack

//계산식 템플릿
// $(w-2ww + w( - 5ww) ) * ww3-(ww-8/ 2)w+(asd10ww%ww3)*ㅈㅈㅈ4d.ㄴ히ㅑ0+ w2.5ww-w..1w.w2
// $(-2+(-5))*3-(-8/2)+(10%3)*4.0+2.5-1.2

class PostfixConverter {

   /**후위연산으로 변환*/
   fun convertCalc(inputString: String):List<String> {
      val operators = listOf('(', ')', '+', '-', '*', '/', '%')

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
      return postfix
   }

   /**후위연산 우선순위*/
   private fun getPriority(operator: String): Int =
      when (operator) {
         "(", ")" -> 0
         "+", "-" -> 1
         "*", "/", "%" -> 2
         else -> -1
      }
}
