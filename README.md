# vo-binding-validation
値オブジェク変オブジェクトのまま、formバインディングとバリデーションをStringのような組み込み型のようなものとして使えるようにする。

## 問題というか好み
* Direct Field Accessを利用して、username.valueなどのようにするのもいいが、デフォルトコンストラクタが必須になる。利用する時にどれを使えば良いのか悩むのが嫌。
* templateにusername.valueなんかが出るのが嫌。
* 様々な状況でformのプロパティが必須でないか必須であるかは変化する。Formを変えたり、group機能を使ってvalidationをグループ化したい。

多少面倒だが、許容範囲となる方法と思うのでやって見た。

## validation
* 独自Validationとして@ValueObjectアノテーションを作成。Validator内部でValidationを実行し、その結果を出力

## binding
* ConditionalGenericConverterを実装したConverterを作成。@ValueObjectアノテーションに値オブジェクトの「コンストラクタ引数の型」
付与して、コンストラクタを使って文字列から値オブジェクトを生成。
* 空文字からnullにConverter内で変換。
* 「コンストラクタ引数の型」毎にConverterFactoryを作成（これは面倒い）

## 制約
* 引数一つのコンストラクタを持つ値オブジェクトだけが対象
* 値オブジェクトにはtoStringはそのまま入力値として表示させるようにオーバーライド必須。値オブジェクトを不変オブジェクトのまま、formバインディングとバリデーションをStringのような組み込み型のようなものとして使えるようにする。

## 利用例
Formクラス
```java
package com.deffence1776.example.presentation;
import com.deffence1776.example.domain.Age;
import com.deffence1776.example.domain.Username;
import com.deffence1776.example.fw.validations.ValueObject;
import javax.validation.constraints.NotNull;

public class SampleForm {

    @ValueObject(emptyToNull = false)
    Username username;

    @ValueObject(parameterType = Integer.class,emptyToNull = true)
    @NotNull
    Age age;

    //setter　getterは省略
    
}
```

テンプレート
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>フォーム</title>
</head>
<body>
<span  th:text="${msg}">msg</span>

<form action="#" th:action="@{/}" th:object="${sampleForm}" method="post">
    <label for="username">ユーザー名</label>
    <input  id="username" type="text"  th:field="*{username}"  >
    <span th:if="${#fields.hasErrors('username')}" th:errors="*{username}">username error</span>
    <br/>
    <label for="age">年齢</label>
    <input  id="age" type="text"  th:field="*{age}"  >
    <span th:if="${#fields.hasErrors('age')}" th:errors="*{age}">age error</span>
    <input type="submit" value="送信">
</form>
</body>
</html>
```
