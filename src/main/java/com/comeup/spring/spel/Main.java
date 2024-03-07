package com.comeup.spring.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author: qiu wanzi
 * @date: 2024年3月7日 0007
 * @version: 1.0
 * @description: TODO
 * note:
 * 文字表达式：SpEL 支持各种类型的文字表达式，包括字符串、数值、布尔值、null、正则表达式等。例如：'Hello World'，10，true，null，/^abc$/。
 *
 * 算术运算：SpEL 支持各种算术运算，包括加法、减法、乘法、除法、取模和幂运算。例如：1 + 1，10 - 2，2 * 3，10 / 2，5 % 2，2 ^ 3。
 *
 * 关系运算：SpEL 支持各种关系运算，包括等于、不等于、小于、小于等于、大于、大于等于。例如：1 == 1，1 != 2，1 < 2，1 <= 1，2 > 1，2 >= 2。
 *
 * 逻辑运算：SpEL 支持逻辑与、逻辑或和逻辑非运算。例如：true and false，true or false，!true。
 *
 * 条件运算：SpEL 支持条件运算，也称为三元运算。例如：1 > 2 ? 'yes' : 'no'。
 *
 * 正则表达式匹配：SpEL 支持正则表达式匹配。例如：'abc' matches '^a.*'。
 *
 * 集合选择和投影：SpEL 支持从集合或数组中选择或投影元素。例如：list.?[#this > 3]（从 list 中选择大于 3 的元素），list.![#this * 2]（从 list 中投影出每个元素的两倍）。
 *
 * 方法调用：SpEL 支持调用方法。例如：'Hello World'.toUpperCase()。
 *
 * 对象导航：SpEL 支持导航对象图，访问对象的属性和方法。例如：person.name，person.getAddress().city。
 *
 * 类型操作：SpEL 支持操作类型，包括类型比较和类型转换。例如：'abc' instanceof T(String)，10 instanceof T(Integer)，'123' as T(Integer)。
 */
public class Main {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        // 简单的文字表达式
        Expression exp = parser.parseExpression("'Hello World'");
        String message = exp.getValue(String.class);
        System.out.println(message);  // 输出: Hello World

        // 算术表达式
        exp = parser.parseExpression("5 * 2 + 1");
        Integer value = (Integer) exp.getValue();
        System.out.println(value);  // 输出: 8

        // 调用方法
        exp = parser.parseExpression("'Hello World ok'.length()");
        value = (Integer) exp.getValue();
        System.out.println(value);  // 输出: 11

        // 关系运算
        exp = parser.parseExpression("4 > 2");
        boolean result = (Boolean) exp.getValue();
        System.out.println(result);


    }
}
