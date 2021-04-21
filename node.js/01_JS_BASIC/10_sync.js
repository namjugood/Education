function first(){
    second();
    console.log('첫 번째');
}
function second(){
    third();
    console.log('두 번째');
}
function third(){
    console.log('세 번째');
}
first();


function longRunningTask(){
    // 오래걸리는 작업
    console.log('작업 끝');
}
console.log('시작');
longRunningTask();
console.log('다음 작업');
// 시작-작업끝-다음작업 의 순서는 절대 바뀌지 않습니다.
// 순서대로 실행하는 작업 : 동기실행