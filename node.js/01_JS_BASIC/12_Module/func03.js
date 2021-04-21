// 구조분해 할당으로 변수 초기화
const {odd, even} = require('./Var');
// const {odd:odd,even:even} = require('./Var');
//console.log(odd);
//console.log(even);
// 모듈을 이용하면, 함수도 exports한 뒤 다른 파일에서 사용 가능
function checkOddOrEven(number){
    if( number % 2 ){
        return odd;
    }else{
        return even;
    }
}
module.exports = checkOddOrEven;