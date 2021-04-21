// 템플릿 문자열 연산을 이용하여 num1 변수에 2000, num2 변수에 3을 저장하며, text 변수에 2000원짜리 모자를
// 3개 구입하여 6000원을 지출하였습니다 를 저장하여 console.log로 출력하세요
const num1 = 2000;
const num2 = 3;
const text=`${num1}짜리 모자를 ${num2}개 구입하여 ${num1*num2}원을 지출하였습니다`;
console.log(text);


// 상품번호, 상품명, 판매가격, 원가의 멤버변수 값을 전달인수로 전달받아 객체를 생성하는 생성자 함수를 만드세요
// (멤버함수:getPrice():가격리턴(), getCost() : 원가리턴, toString() : 모든 내용출력 포함)
function Production(pNum, pName, sPrice, bPrice){
    this.num = pNum;
    this.pname = pName;    
    this.sell = sPrice;
    this.buy = bPrice;

    this.getPrice = function(){
        return this.sell;
    }
    this.getCost = function(){
        return this.buy;
    }
    this.toString = function(){
        console.log(`상품번호 : ${this.num}`);
        console.log(`상품명 : ${this.pname} `);
        console.log(`판매가격 : ${this.sell} `);
        console.log(`원가 : ${this.buy} `);
    }
}

var obj = new Production(1, "악어백", 100, 50);
console.log(obj.toString());


// 위에서 만든 생성자에 프로토타입을 이용하여, 원가와 판매가격으로 마진(이익)을 리턴해주는 getMargin() 멤버함수를
// 추가하세요
function Production(pNum, pName, sPrice, bPrice){
    this.num = pNum;
    this.pname = pName;    
    this.sell = sPrice;
    this.buy = bPrice;

    this.getPrice = function(){
        return this.sell;
    }
    this.getCost = function(){
        return this.buy;
    }
    this.toString = function(){
        console.log("상품번호 : "+num+", 상품명 : "+pname+", 판매가격 : "+sell+", 원가 : "+buy);
    }
}
Production.prototype.getMargin = function(){
    return this.getPrice()-this.getCost();
}

var obj = new Production(1, "악어백", 1000, 50);
obj.toString();
console.log(obj.getMargin());

// 만든 생성자 함수의 이름을 변경하고, 변경한 새로운 생성자에 화살표함수로 변경하세요
Production1 = function(pNum, pName, sPrice, bPrice){
    this.num = pNum;
    this.pname = pName;    
    this.sell = sPrice;
    this.buy = bPrice;

    this.getPrice(()=>{
        return this.sell;
    });
    this.getCost(()=>{
        return this.buy;
    });
    this.toString(()=>{
        console.log(`상품번호 : ${this.num}`);
        console.log(`상품명 : ${this.pname}`);
        console.log(`판매가격 : ${this.sell}`);
        console.log(`원가 : ${this.buy}`);
    });
};
pd.prototype.getMargin = function(){
    return this.sell-this.buy;
}
// 생성자함수 없이 위 문제의 멤버를 모두 갖고 값을 초기화한 객체를 만드세요.(멤버함수 포함)
const Production2 = {
    num = 1,
    pname = "악어백",
    sell = 1000,
    buy = 450,
    getPrice:function(){ return this.sell; },
    getCost:function(){ return this.buy; },
    toString:function(){
        console.log(`상품번호 : ${this.num}`);
        console.log(`상품명 : ${this.pname}`);
        console.log(`판매가격 : ${this.sell}`);
        console.log(`원가 : ${this.buy}`);
    }
}
// 만든 객체에 멤버함수 getMargin()을 추가하세요
Production2.getMargin=function(){
    return this.sell-this.buy;
}

// Promise 객체에 k변수(미리 선언하고 초기화되어있음)에 들어있는 값이 짝수면, resolve 호출, 홀수면 reject를 호출하여
// 짝수입니다 또는 홀수입니다를 출력하는 Promise객체와 결과를 출력하세요
const k = 20;
const pm = new Promise( (resolve, reject) => {
    if(k%2==0){
        resolve('짝수입니다');
    }else{
        reject('홀수입니다');
    }
} );

pm
    .then( (message)=>{ 
        console.log(message); // 성공(resolve) 된 경우 실행
     })
    .catch( (error)=>{  // 실패(reject) 된 경우 실행
        console.log(error);
     });
    

// Promise 를 이용해 연속 .then으로 짝수입니다를 3번 출력하여 코딩하세요

pm
     .then((msg1)=>{
        console.log(msg1);
        return new Promise((resolve, reject)=>{
           resolve('짝수입니다') 
        });
     })
     .then((msg2)=>{
        console.log(msg2);
        return new Promise((resolve, reject)=>{
           resolve('짝수입니다') 
        });
     })
     .then((msg3)=>{
        console.log(msg3)
     })
     .catch( (error) => {
        console.error(error);
    });

