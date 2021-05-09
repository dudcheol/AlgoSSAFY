# AlgoSSAFY 면접스터디

## 🤝Rule

일주일에 2회 `직무`, `인성` 면접용 질문을 1~2개 준비합니다. 모임이 있는 날 준비해온 질문을 서로 공유하며 답변해보고 코칭, 피드백을 갖는 시간을 가집니다.

<hr>

### 이석재

```java
// Q1. 아래 4가지 차이를 아는 대로 말해줘
1. String a = "ssafy";
2. String b = new String("ssafy");
3. StringBuilder builder = new StringBuilder("ssafy").toString();
4. StringBuffer buffer = new StringBuffer("ssafy").toString();

// A1.
1. 리터럴로 문자열을 변수에 할당했다. 이 경우 문자열은 String Constant Pool에 할당되고, 그 주솟값이 변수에 담긴다.
2. new 키워드로 문자열을 할당했다. 이 경우 문자열은 heap에 새로운 Object로 할당되고, a와 다른 주솟값이 b에 담긴다.
3. StringBuilder를 사용해 문자열을 관리한다. 싱글 스레드에서의 정상적인 성능보장을 한다. 동적 배열로 배열 사이즈를 넘는 문자열이 추가되는 경우, 새 StringBuilder를 만들어 기존 문자열을 복사 후 append 작업을 수행한다.
4. StringBuffer를 사용해 문자열을 관리한다. 내부 synchronized 키워드로 데이터와 메서드가 관리되어 멀티 스레드에서도 성능 보장을 한다. 그 외에는 StringBuilder와 유사하다.

* 문자열의 단순 concat연산(str+str+str+...)은 새 Object를 Constant Pool에 생성하기 때문에 메모리 관점에서 비효율적이다.
* 내부적으로 + 연산이 많아지는 경우 StringBuilder를 생성해 append하는 식으로 구현이 되어있지만 빈번한 문자열 조작이 필요한 경운 StringBuilder, StringBuffer를 사용하는 것이 좋다.

// Q2. 추상 클래스 vs 인터페이스??
```

### 김한솔

```내용을 입력하세요```

### 박영철

```java
본인이 창의적이었다고 생각했던 경험을 하나 얘기해보세요
// 다른사람이 창의적이라고 생각하지 않더라도, 본인이 창의적이라고 생각하는 것을 말한다!
```

### 박유정

```당신은 어떤 개발자가 되고 싶으신가요?```

### 김청화

```살면서 가장 힘든 일이 무엇이었고 어떻게 해결했는가, 대인관계에서 갈등이 있었던 적이 있는가```
