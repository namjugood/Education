// 함수 표현방법 #1
function add1(x,y){
    return x + y;
}
console.log(add1(3,5));

// 함수 표현 방법 #2
var add2 = function(x, y){
    return x + y;
}
console.log(add2(3,5));

// 함수 표현 방법 #3-1
const add3 = (x,y) => {
    return x + y;
};
console.log(add3(3,5));

// 함수 표현 방법 #3-2
const add4 = (x,y) => x+y;
console.log(add4(3,5));

// 함수 표현 방법 #3-3
const add5 = (x,y) => (x+y);
console.log(add5(3,5));

function not1(x){
    return !x;
}
console.log(not1(true));

const not2 = x => !x;
console.log(not2(true));