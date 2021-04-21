// require를 이용해서 os 객체를 불러옵니다
const os = require('os');

console.log('운영체제 정보----------------------------------------------');
console.log('os.arch(): ', os.arch()); // 운영체제 설계 및 운용 방식
console.log('os.platform(): ', os.platform()); // 운영체제 기반 플랫폼
console.log('os.type(): ', os.type()); // 운영체제 종류
console.log('os.uptime(): ', os.uptime()); // 운영체제 부팅 후 흐른 시간
console.log('os.hostname(): ', os.hostname()); // 컴퓨터 이름
console.log('os.release(): ', os.release()); // 운영체제 버전

console.log('경로------------------------------------------------------');
console.log('os.homedir(): ', os.homedir()); // 사용자 홈 디렉토리(폴더)
console.log('os.tmpdir(): ', os.tmpdir()); // 사용자 임시 디렉토리(폴더)

console.log('cpu정보---------------------------------------------------');
console.log('os.cpus(): ', os.cpus()); // cpu 및 코어 정보
console.log('os.cpus().length: ', os.cpus().length); // cpu 코어 개수

console.log('메모리 정보------------------------------------------------');
console.log('os.freemem(): ', os.freemem()); // 사용가능 메모리
console.log('os.totalmem(): ', os.totalmem()); // 전체 메모리