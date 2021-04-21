// 1. 객체의 생성 - key(요소이름) : 멤버 변수 값
var product = {name:'냉장고', 제조사:'한국'};
// 변수에 값을 하나만 넣는게 아니라 위와 같이 키:값 으로 구성된 데이터
// 하나 이상을 저장하면 그때부터 데이터는 오브젝트(object)가 됩니다
// 모든 속성데이터는 중괄호({ })로 묶이고, 각 데이터는 콤마(,)로 구분
console.log(product['제조사']);

// 2. 객체의 속성과 메서드
    // - 속성 : 객체 내부에 있는 하나하나의 값.
    // - 객체의 속성이 가질 수 있는 자료형
    var object = {
        useNumber:273,
        useString:'문자열',
        useBoolean:true,
        useArray:[52,385,103,58],
        // 메서드 : 객체 속성 중 함수자료형인 속성
        useMethod:function(){
            console.log('멤버함수를 실행합니다');
        }
    };
    object.useMethod(); // 함수의 실행은 괄호를 붙여서 사용
    // console.log(object.useMethod); // 함수 이름이 보여짐 
    // console.log(object.useMethod()); // 함수가 실행되고 리턴값이 없어 undefined가 출력

// -----------------------------------------------------------------------
    var person = {
        name:'홍길동',
        eat:function(food){
            console.log('음식:' + food);
        }
    };
    console.log(person.name);
    person.eat('스파게티');
    
// -----------------------------------------------------------------------
    
    // 멤버함수가 멤버 변수로의 접근
    // - this 키워드 : 자바스크립트는 멤버변수에 접근을 위해서 반드시
    // this 키워드를 써야합니다

    var person = {
        name:'홍길동',
        eat:function(food){
            // console.log(this.name+'이/가'+food+'을/를 먹었습니다.');
            console.log(`${this.name} '이/가 ' ${food} '을/를 먹었습니다.'`);
        }
    };
    person.eat('김밥');


// 3. 객체와 반복문
var product = {
    name : 'Eclipse & Tomcat',
    price : 'Free',
    language : '한국어',
    supportOS : 'win32/64',
    subscripton : true
};
// 객체 이름을 반복문에 대입하여 각 멤버 변수들의 값에 접근합니다.
for(var key in product){
    var output = `${key} : ${product[key]}`;
    console.log(output);
} 

// 4. 객체와 관련된 키워드
var student = {
    이름:'홍길동',
    국어:92, 수학:98, 영어:96, 과학:98
};
// -in 키워드 : 해당 키가 객체 안에 있는 지 확인
var output='';
output += "'이름' in student :" + ('이름' in student)+'\n';
output += "'성별' in student :" + ('성별' in student);
console.log(output);

// -with 키워드 : 복잡하게 사용해야하는 코드를 짧게 줄여주는 키워드
// .with 키워드를 사용하지 않는 경우
var student = {
    이름:'홍길동',
    국어:92, 수학:98, 영어:96, 과학:98
};
var write = '';
write += '이름 : ' + student.이름+'\n'
write += '국어 : ' + student.국어+'\n'
write += '수학 : ' + student.수학+'\n'
write += '영어 : ' + student.영어+'\n'
write += '과학 : ' + student.과학+'\n'
console.log(write);

// .with 키워드를 사용한 경우
var write='';
with(student){
    write += '이름 : '+ 이름 + '\n';
    write += '국어 : '+ 국어 + '\n';
    write += '수학 : '+ 수학 + '\n';
    write += '영어 : '+ 영어 + '\n';
    write += '과학 : '+ 과학 + '\n';
}
console.log(write);

// 5. 객체의 속성 추가와 제거
// - 동적 속성 추가/제거 : 처음 객체를 생성하는 시점 이후에 객체의 속성을
// 추가하거나 제거할 수 있습니다.

// 빈 객체를 생성
var student = {};
// 객체 생성 이후 동적으로 속성(멤버변수)를 추가할 수 있습니다.
student.이름 = '홍길동';
student.취미 = '악기';
student.특기 = '프로그래밍';
student.장래희망 = '훌륭한 프로그래머';
for(var key in student){
    console.log(`${key} : ${student[key]}`);
}
// 객체 안에 변수와 함수를 선언하는 경우
// var student = {이름:'홍길동', toString:function(){}};

// 동적으로 메서드 추가
student.toString = function(){
    for(var key in this){
        if(key != 'toString'){
            console.log(`${key} : ${student[key]}`);
        }
    }
}
student.toString();

// 객체의 속성 제거
delete(student.장래희망);
student.toString();

// 6. 생성자 함수
// - new 키워드를 사용해 객체를 생성할 수 있는 함수
// - 생성자 함수를 사용한 객체의 생성과 추력
// - 그냥 함수를 사용해 객체를 리턴하는 방법과 차이가 없어 보임.
function Student(name, korean, math, english, science){
    // 속성

    this.name = name;
    this.kor = korean;
    this.math = math;
    this.english = english;
    this.science = science;
    // 메서드
    this.getSum = function(){
        return this.kor + this.math + this.english + this.science;
    }    
    this.getAvg = function(){
        return this.getSum() / 4;
    }
    this.toString = function(){
        return this.name + ':       ' + this.getSum() + '         ' + this.getAvg();
    }    
}


console.log(' 이름         총점        평균');
console.log(obj1.toString());

// 7. 프로토타입
// - 생성자 함수를 사용해 생성된 객체가 공통으로 가지는 공간
// - 자바스크립트의 모든 함수는 변수 prototype을 갖습니다
// - 그리고 prototype은 객체입니다
function Student(name, korean, math, english, science){
    // 속성
    this.name = name;    
    this.kor = korean;    
    this.math = math;    
    this.english = english;    
    this.science = science;
}
// 모든 함수에 존재하는 프로토타입은 특히 객체의 생성자로 사용할 때  용도가 확실해집니다.
// 가장 간단한 표현 : 생성자 함수에 메서드 추가용 키워드
Student.prototype.getSum = function(){
    return this.kor+this.math+this.english+this.science;
}
Student.prototype.getAvg = function(){
    return this.getSum() / 4;
}
Student.prototype.toString = function(){
    return this.name+' : '+this.getSum()+', '+this.getAvg();
}
std1 = new Student('홍길동', 88, 999, 77, 66);
console.log(std1.toString());
// 아래와 같이 객체를 생성한 후에 멤버메서드를 추가하든, 위와 같이 메서드 추가 후 객체 만드는 방법
// 생성자의 외부에서 메서드 추가시 사용(기본)
// std1 = new Student('홍길동', 88, 99, 7, 66); // 객체 생성
// std1.toString = function(){}

// 8. instanceof 키워드
// - 인스턴스 : 생성자 함수를 통해 만들어진 객체
// - 해당 객체가 어떠한 생성자 함수를 통해 생성되었는지를 확인할 때 사용하는 키워드

function Student(name){ this.name = name; };
var std2 = new Student('홍길동');
console.log( std2 instanceof Student);
console.log( std2 instanceof Number);

// 9. 상속
function Rectangle(w, h){
    var width = w;
    var height = h;
    this.getWidth = function() {return width;}
    this.getHeight = function() {return height;}
    this.setWidth = function(value){ width = value; }
    this.setHeight = function(value){ height = value;}    
}
Rectangle.prototype.getArea = function(){
    return this.getWidth() * this.getHeight();
}
var rectangle = new Rectangle(5, 7);
rectangle.setWidth(8);
rectangle.setHeight(9);
console.log('AREA : '+rectangle.getArea());

// Rectangle의 생성자 상속
function Square(length){
    this.base = Rectangle;
    // 전달된 length값을 base 생성자의 w, h에 같은 값으로 전달
    this.base(length, length); 
}
// 추가로 프로토타입 복사
Square.prototype = Rectangle.prototype;

var rectangle = new Rectangle(5,7);
var square = new Square(5);

console.log(rectangle.getArea());
console.log(square.getArea());

// 10. Object 객체
// - toString() 메서드.
// - 객체를 문자열로 변환할 때 자동으로 호출
var object = new Object();
console.log(object);
console.log(object.toString());
// - toString() 메서드의 재정의
var student = {
    name:'홍길동',
    grade:'고등학교1학년',
    toString:function(){
        return this.name+':'+this.grade;
    }
};
console.log(student)