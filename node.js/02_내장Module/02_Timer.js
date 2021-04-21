// 지정된 시간 후에 한 번 실행
const timeout = setTimeout(()=>{
    console.log('1.5초 후에 실행')
}, 1500);
// 지정된 사간마다 반복실행
const interval = setInterval(()=>{
    console.log('1초 마다 실행')
}, 1000);
// 타이머 종료
clearTimeout(timeout); // 아직 지정된 시간이 지나지 않았으면 실행 전 종료
clearInterval(interval); // 반복실행 종료

// 즉시 실행(백그라운드에서 실행되는 것과 거쳐오는 것이 차이가 있음)
const immediate = setImmediate(()=>{
    console.log('즉시 실행');
});

// 즉시 실행 종료
clearImmediate(immediate);