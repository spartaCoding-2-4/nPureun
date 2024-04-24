![header](https://capsule-render.vercel.app/api?type=waving&text=n.Pureun&height=200&color=73C2FB&&animation=fadeIn&fontColor=003153)

# 🖥 계산기 프로그램 🖥
### 계산기 프로그램을 설명합니다

---

## 🔷 목차 🔷
[📒 기능과 사용법](#-기능과-사용법)

[🌃 프로그램 구조](#-프로그램-구조)

[🪐 기능 설명](#-기능-설명)

---
## 📒 기능과 사용법

프로그램의 사용법과, 기능을 소개합니다.

### 👾 사용법

단일 수식은 단순 문자열을 입력하고, 연쇄수식은 앞에 "$"를 붙입니다.

```ruby
///단일수식///
식을 적으세요(앞에 $ 붙이면 연쇄수식 호출 / x= 종료) : 1+1
문자열 필터링 : 1+1
문자열 포맷 : 1 + 1
계산 결과 : 2.0

///연쇄수식///
식을 적으세요(앞에 $ 붙이면 연쇄수식 호출 / x= 종료) : $2+2
문자열 필터링 : 2+2
문자열 포맷 : 2 + 2
후위연산 변환 : [2, 2, +]
계산 결과 : 4.0
```

### 👾 부가기능

음수와 소수점에 대응가능하고, 의미없는 문자를 필터링해 식을 추출합니다.

```ruby
///식 추출///
식을 적으세요(앞에 $ 붙이면 연쇄수식 호출 / x= 종료) : asd1+asd1
문자열 필터링 : 1+1
문자열 포맷 : 1 + 1
계산 결과 : 2.0

///음수, 소수///
식을 적으세요(앞에 $ 붙이면 연쇄수식 호출 / x= 종료) : -0.1+-0.1
문자열 필터링 : -0.1+-0.1
문자열 포맷 :  -0.1 +  -0.1
계산 결과 : -0.2
```

### 👾 예외처리

잘못된 식이나, 입력에 대한 에러메세지를 띄웁니다.

```ruby
식을 적으세요(앞에 $ 붙이면 연쇄수식 호출 / x= 종료) : asd+asd
문자열 필터링 : +
문자열 포맷 :  + 
올바르지 않은 입력 입니다.

식을 적으세요(앞에 $ 붙이면 연쇄수식 호출 / x= 종료) : +-+
문자열 필터링 : +-+
문자열 포맷 :  +  - + 
올바르지 않은 숫자형식 입니다.

식을 적으세요(앞에 $ 붙이면 연쇄수식 호출 / x= 종료) : 1/0
문자열 필터링 : 1/0
문자열 포맷 : 1 / 0
올바르지 않은 식입니다.
```

---
## 🌃 프로그램 구조

프로그램의 기능별 호출구조를 간략히 도식화 했습니다.

![image-9](https://github.com/npureaun/ReadMeUtile/assets/98468118/bd65669b-1fda-4046-b580-93b3c3f30ad0)

---
## 🪐 기능 설명

기능을 구현한 함수와 로직을 설명합니다.

### 👾 abstract class Calculator()
<details>
<summary><code>Calculator Class</code>는 추상클래스로 되어 있고, 추상함수 하나만을 가지고 있으며,
  
  자식클래스에서 <code>Override</code>하여 함수를 구체화 합니다.</summary>

+ #### <code>abstract fun calculateResult(x:Double, y:Double): Double</code>

  = 추상함수로 각각의 연산자에 해당하는 자식클래스에서 <code>Override</code>됩니다.

+ #### <code>override fun calculateResult(x:Double, y:Double):Double = String.format("%.2f").toDouble()</code>

  = <code>Override</code>에 결과를 낼때, 소수형식은 코틀린의 <code>StringFormat</code>에 따릅니다.

</details>

### 👾 class Manager()
<details>
<summary>프로그램의 <code>Main Class</code> 입니다. </summary>

+ #### <code>fun manual()</code>

    = 프로그램의 <code>main</code>이 되는 함수입니다. 계산기의 시퀀스를 관리합니다.
    <details>
    <summary><code>row code</code></summary>
    
    ```kotlin
     while (true)
          {
              var calculate=inputCalculate()
              if(calculate=="Exit") break
    
              calculate=stringUt.convertString(calculate)
              if(calculate.first()=='$')
                  calcUt.postfixResult(stringUt.postfixConvert(calculate.substring(1)))
              else calcUt.singleResult(stringUt.getParsingList(calculate))
          }
    ```
    
    </details>


+ #### <code>private fun inputCalculate(): String</code>

  = 사용자와 대화를 하는 함수입니다. 해당함수에서 입력을 받습니다.

</details>

### 👾 Class CalculateUtile()

<details>
<summary><code>Calculator Class</code>와의 연결을 수행하며, 적절한 호출로 단일, 연쇄 식을 적용합니다.</summary>

+ #### <code>private fun calculate(x: Double, y: Double, oper: String):Double</code>

  = 전달 받은 인자를 통해 <code>추상클래스</code>를 호출하여 결과를 리턴합니다. 직접적인 계산은 이 함수틑 통합니다.
  <details>
  <summary><code>row code</code></summary>
    
  ```kotlin
      try {
              result=when (oper) {
                  "+" -> Summation().calculateResult(x, y)
                  "-" -> Subtraction().calculateResult(x, y)
                  "*" -> Multiplication().calculateResult(x, y)
                  "/" -> Division().calculateResult(x, y)
                  "%" -> Remainder().calculateResult(x, y)
                  else -> {
                      throw Error("올바르지 않은 연산자 입니다.")
                  }
              }
          }
  ```
  
  </details>

+ #### <code>fun postfixResult(postfix: List<String>)</code>

  = 연쇄수식의 계산 알고리즘을 통해 <code>calculate</code>함수틑 통한 값들의 최종적 결과를 출력합니다.
  <details>
  <summary><code>row code</code></summary>
    
  ```kotlin
      for (token in postfix) {
                  when (token) {
                      in operators.toString() -> {
                          if(stack.size<2) throw Error("올바르지 않은 입력입니다.")
                          val right = stack.removeAt(stack.lastIndex)
                          val left = stack.removeAt(stack.lastIndex)
                          val result = calculate(left,right,token)
                          if(result.isNaN()||result.isInfinite())
                              throw Error("올바르지 않은 식입니다.")
                          else stack.add(result)
                      }
                      else -> {
                          stack.add(token.toDouble())
                      }
                  }
              }
  ```
  
  </details>

+ #### <code>fun singleResult(calculate: List<String>)</code>

  =  <code>calculate</code>함수틑 통해 단일수식의 최종결과를 출력합니다.
  <details>
  <summary><code>row code</code></summary>
    
  ```kotlin
     try {
              if (calculate.size != 3/* (x operator y) 구조가 아니면 */)
                  throw Error("올바르지 않은 입력 입니다.")
              val x = calculate[firstVal].toDouble()
              val y = calculate[lastVal].toDouble()
              result=calculate(x,y,calculate[operator])
              if(result.isNaN()||result.isInfinite()) throw Error("올바르지 않은 식입니다.")
              else println("계산 결과: $result")
          }
  ```
  
  </details>
  
</details>

### 👾 class StringUtile()
<details>
<summary>문자열 <code>포맷과 변환</code>을 수행합니다.</summary>

+ #### <code>private fun stringFilter(inputString: String):String</code>

  = 입력받은 문자열을 <code>1차적</code>으로 <code>필터링</code> 합니다. 의미없는 문자를 무시합니다.
  <details>
  <summary><code>row code</code></summary>
    
  ```kotlin
     val operators = listOf('(', ')', '+', '-', '*', '/', '%')
          inputString.forEach {
              if(it.isDigit()||it in operators || it=='.')
                  calc.append(it.toString())
          }
  ```

  </details>

+ #### <code>fun convertString(inputString: String): String</code>

  = <code>stringFilter</code>적용된 문자열에서 <code>연산자</code>와 <code>숫자</code>를 추출합니다.

  해당 함수에서 소수점과 음수에 대한 대응을 하고, 최종적으로 파싱가능한 계산 식을 도출해 냅니다.
  <details>
  <summary><code>row code</code></summary>
  
  ```kotlin
    calc.forEachIndexed { i, it ->
            if (it == '.' &&
                (i - 1 in calc.indices && calc[i - 1].isDigit()) &&
                (i + 1 in calc.indices && calc[i + 1].isDigit())
            ) {
                str += it
            } else if (it in operators) {
                str += " "
                if (i == 0 && it == '-') str += it
                else if (it == '-' && calc[i - 1] in operators)
                    str += it
                else str += "$it "
            } else if (it.isDigit()) {
                str += it
            }
        }
  ```
  
  </details>

+ #### <code>fun getParsingList(inputString: String):List<String></code>

  = 파싱가능한 문자열을 <code>List</code> 형태로 반환하여 핸들링이 용이하게 합니다.
  
+ #### <code>fun postfixConvert(inputString: String):List<String></code>

  = 파싱가능한 문자열을 알고리즘을 거쳐 <code>postfix</code>를 도출한 <code>List</code>로 반환합니다.
  <details>
  <summary><code>row code</code></summary>
  
  ```kotlin
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
  ```
  
  </details>

+ #### <code>private fun getPriority(operator: String): Int</code>

  = 후위연산 변환 알고리즘에 필요한 <code>Priority</code>함수입니다.
  <details>
  <summary><code>row code</code></summary>
  
  ```kotlin
     when (operator) {
            "(", ")" -> 0
            "+", "-" -> 1
            "*", "/", "%" -> 2
            else -> -1
        }
  ```
  
  </details>
  

</details>






