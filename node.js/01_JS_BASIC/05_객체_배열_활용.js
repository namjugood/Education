// 생성자 함수로 배열의 요소 추가
function Student(name, korean, math, english, science){
    this.name = name;    
    this.kor = korean;
    this.math = math;
    this.english = english;
    this.science = science;
    this.toString = function(){
        return this.name + ': '+this.getSum()+',  '+this.getAvg();
    }
    this.getAvg = function(){
        return this.getSum() /4;
    }
    this.getSum = function(){
        return this.kor + this.math + this.english + this.science;
    }
    this.toString = function(){
        return this.name+':  '+this.getSum()+',  '+this.getAvg();
    }
}

var students = [];
var obj1 = new Student('홍길동', 85,90,95,100);
students.push(obj1);
students.push(new Student('홍길서', 75,80,65,90));
students.push(new Student('홍길남', 80,95,75,80));
students.push(new Student('홍길북', 85,60,85,70));
students.push(new Student('김길동', 65,60,75,80));
students.push(new Student('이길동', 75,95,85,90));
students.push(new Student('박길동', 90,80,75,90));
console.log("이름   총점   평균");
for(var i in students){
    console.log(students[i],toString());
}

// 문자열의 연산과 함수 변수의 활용
var sayNode = function(){
    console.log('Node');
};  // 단순 함수를 sayNode 변수에 저장

var es = 'ES';  // 단순 문자열을 es변수에 저장
var oldObject = {    // 객체 생성
    sayJS: function(){  // 멤버메서드 : 함수 직접 정의
        console.log('JS');
    },
    // sayNode: sayNode,   // 멤버베서드 : 정의된 함수 불러와서 장착
    sayNode,  // 키 이름과 밸류 변수이름이 같으면 한번만 써도 무방합니다.
};
oldObject.sayNode();
oldObject.sayJS();

oldObject[es+'6'] = 'Fantastic'; // 'ES6'이라는 멤버변수 생성
// 'Fantastic'이라는 값으로 변수값 대입
console.log(oldObject.ES6);

const newObject = {
    sayJS(){
        console.log('JS');
    },
    sayNode,
    [es + 6]: 'Fantastic',
};
newObject.sayNode(); // Node
newObject.sayJS(); // JS
console.log(newObject.ES6); //Fanstastic


// 객체의 구조 분해
const sayJ = newObject.sayJS; // 객체 내의 함수를 별도의 변수에 저장
sayJ();
var sayN = newObject.sayNode;
sayN();
var es6 = newObject.ES6;
console.log(newObject.ES6)
console.log(es6)

// 객체의 구조 분해를 하지말아야 하는 경우
// this를 사용하는 객체는 구조분해를 하지 않는 것이 좋습니다
const candyMachine = {
    status:{
        name:'node',
        count:5
    },
    getCandy(){
        this.status.count--;
        return this.status.count;
    },
};
// 객체 내의 메서드가 구조분해되는 순간 안에 있던 this를 사용할 수 없게 되므로
// 그 안의 count 또한 없는 변수가 되어 에러를 발생합니다.
// this를 사용하는 객체는 분해하지 않는 것이 좋음
var count = candyMachine.status.count;
var getCandy = candyMachine.getCandy;
getCandy()
console.log(count);

// 필요한 데이터 또는 함수의 구조 분해를 한번에 실행할 수 있습니다
// 다만 아래 예제 또한 this를 사용하고 있어서 구조분해를 하지 않는 것이 좋습니다
const candyMachine = {
    status:{
        name:'node',
        count:5
    },
    getCandy(){
        this.status.count--;
        return this.status.count;
    },
};
const{getCandy, status:{count} } = candyMachine;
console.log(getCandy());

var array = ['nodejs', {}, 10, true];
var node = array[0];
var obj = array[1];
var bool = array[3];
console.log(node);

const array1 = ['nodejs', {}, 10, true];
const [node1, obj1, , bool1] = array1;
console.log(node1);