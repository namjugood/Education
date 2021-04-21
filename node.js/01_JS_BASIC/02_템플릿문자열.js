// 문자열들의 '+' 연산
// 기존의 연산과 출력방법
var num1 = 1;
var num2 = 2;
var result = 3;
var string1 = num1 + '더하기' + num2 + '는 \'' + result + '\'';
console.log(string1) 

// 업그레이드된 연산
const num3 = 1;
const num4 = 2;
const result2 = 3;
const string2 = `${num3} 더하기 ${num4} 는 '${result2}'`;
console.log(string2);