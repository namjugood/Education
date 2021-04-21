// 09. _promise의 다른형태
// 실패가능성이 있는 동작을 실행하고 결과를 갖고 있다가,
// 결과가 필요한 순간 객체를 호출하여 결과를 표시
const condition = true;
const promise = new Promise( (resolve, reject)=>{
    if(condition) resolve('성공');
    else reject('실패');
});

/*
// 기본구조
promise
    .then((message)=>{ console.log(message); })
    .catch((error)=>{console.error(error); })
    .finally(()=>{console.log('무조건 실행'); })
*/

async function abcd(){
    // promise의 결과값을 꺼내서 result에 대입
    // await : promise의 비동기실행을 기다리다가 필요할 때 결과값을 꺼내기 위한 키워드
    // await를 사용할 명령은 반드시 async로 만들어진 함수 안에서 사용해야 합니다.
    const result = await promise;
    console.log(result);
}


// Promise의 다른형태 #2
// 연속된 promise의 형태에는 적합하지 않으나 억지로 써야한다면 아래와 같이 사용
const promise = new Promise( (resolve, reject)=>{
    resolve('첫 번째 메시지');
    // else reject('실패');
});
async function thenfunc(){
    try{
        const result = await promise;
        console.log(result);
        // 리턴 = 두 번째 resolve 호출 : 새로운 promise 객체 내의 resolve가 호출됨
        return "두 번째 메시지";  
    }catch(error){
        console.error(error);
    }
}
thenfunc()
    .then( (result2)=>{
        console.log(result2);
    })
    .catch( (error)=>{
        console.error(error);
    });