const {odd, even} = require('./Var');
const checkNumber = require('./func03');

function checkStrOddOrEven(str){
    if(str.length % 2){
        return "글자개수가 "+odd;
    }else{
        return "글자개수가 "+even;
    }
}

console.log( checkNumber(10));
console.log( checkStrOddOrEven('Hello World'));