// 비동기실행
function longRunningTask(){
    // 오래걸리는 작업
    console.log('작업 끝');
}
console.log('시작');
// 두 번째 요소로 지정한 시간뒤에 첫 번째 요소로 지정된 함수 호출
setTimeout(longRunningTask, 1000)// => 바로 실행하라는 명령
console.log('다음 작업');
// 시작-작업끝-다음작업 의 순서는 절대 바뀌지 않습니다.