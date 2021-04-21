// 11. 배열
// 다양한 자료를 하나의 범주 안에 넣고, 인덱싱(번호)을 이용하여 컨트롤하는 변수
var array = [273, 'string', true, function(){}, {}, [150,170]];
console.log(array[0]);
console.log(array[1]);
console.log(array[2]);
console.log(array[3]);
console.log(array[4]);
console.log(array[5]);

var arr=['a','b','c'];
console.log(arr);

arr.push('d'); // 배열의 끝에 요소를 추가
console.log(arr);
arr.unshift('A'); // 배열의 앞쪽에 요소를 추가
console.log(arr);
arr.splice(2,0,'B'); // index 2('b')의 위치에 요소를 추가
console.log(arr);


var arr=['a','b','c','d'];
arr.splice(2, 0, 'C', 'D'); //index 2의 위치에 2개의 요소를 추가
console.log(arr);

var arr = ['a','b','c','e','f'];
// 배열의 마지막 요소를 제거
var popped = arr.pop(); // 제거한 요소를 반환받을 수 있음
console.log(arr);
console.log(popped);

var arr = ['a','b','c','e','f'];
// 배열의 첫번째 요소를 제거
var shifted = arr.shift(); // 제거한 요소를 반환받을 수 있음
console.log(arr);
console.log(shifted);

arr = ['a','b','c','e','f'];
// index 2부터 1개의 요소('b', 'c')를 제거
arr.splice(1,2);
console.log(arr);

// delete로 배열의 요소를 삭제할 경우, 값은 삭제되고 자리요소는 존재합니다.
var arr = ['a','b','c','e','f'];
delete arr[1];
console.log(arr);



