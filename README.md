# 계산기 프로그램 설명서
---
<details>
<summary>콘솔창에서 수식쓰기</summary> 

### 단일 수식
![alt text](image.png)

단일수식을 작성 후 엔터키를 누르면 계산합니다.

### 연쇄 수식
![alt text](image-1.png)

연쇄수식은 앞에 "$"를 붙여 호출합니다.

</details>

---
<details>
<summary>예외처리</summary> 

![alt text](image-2.png)

잘못된 식이면, 에러메세지를 띄웁니다.

![alt text](image-3.png)

입력이 잘못되었다면, 해당 메세지를 띄웁니다.

![alt text](image-4.png)

숫자가 아닌 값을 계산을 하려하면, 해당 메세지를 띄웁니다.

</details>

---
<details>
<summary>부가기능</summary> 

![alt text](image-5.png)

의미 없는 문자가 들어가 있어도, 계산식을 추출해 냅니다.

![alt text](image-6.png)

음수와 소수에 대한 계산이 가능합니다. (소수둘째자리까지 표현하며, 코틀린의 StringFormat에 따릅니다.)

</details>



