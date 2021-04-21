async function getUser(){
    try{
        const res = await axios.get('/users'); // get method로 '/users'요청
        const users = res.data; // 요청에 대한 반환(리턴)값
        const list = document.getElementById('list'); // id가 list인 태그 저장
        list.innerHTML = '';
        // users의 키값들을 전달인수로 익명함수를 키값의 개수만큼 실행
        // 내용은 list div에 리턴된 res값을 화면에 표시
        Object.keys(users).map(function(key){
            // 태그들을 생성해서 users 객체의 값들을 하나하나 추가하는 동작
            // div태그 생성 <div></div>
            const userDiv = document.createElement('div');  
            // span 태그 생성 <span></span>
            const span = document.createElement('span');       
            // span 태그안에 키값으로 얻어낸 users값을 삽입
            span.textContent = users[key];
            // users값들 하나하나 옆에 버튼 추가(수정버튼 생성)
            const edit = document.createElement('button');
            edit.textContent = '수정';
            // 수정버튼 클릭시 실행
            edit.addEventListener('click', async ()=>{
                const name = prompt('바꿀 이름을 입력하세요');
                if(!name){
                    return alert('이름을 반드시 입력하셔야 합니다');
                }
                try{
                    await axios.put('/user' + key, {name});
                    getUser();
                }catch(err){
                    console.error(err);
                }
            });
            // 삭제버튼 생성
            const remove = document.createElement('button');
            remove.textContent = '삭제';
            remove.addEventListener('click', async ()=>{ // 삭제버튼 클릭
                try{
                    await axios.delete('/user/' + key);
                    getUser();
                }catch(err){
                    console.error(err);
                }
            });
            userDiv.appendChild(span);      // div 안에 span 삽입
            userDiv.appendChild(edit);      // div 안에 edit 버튼 삽입
            userDiv.appendChild(remove);    // div 안에 remove 버튼 삽입
            list.appendChild(userDiv);      // div 태그를 list 태그에 삽입
            console.log(res.data);

        });
    }catch(err){
        console.log(err);
    }
    
}

// 화면 로딩 시 getUser 호출
window.onload = getUser;

// eventlistener를 붙여 form이 submit 될 때의 기능을 추가합니다.
// form이 제출(submit)될 때 실행됩니다.
document.getElementById('form').addEventListener('submit', async (e)=>{
    e.preventDefault(); // 중간점검을 위한 submit 동작 일시중지
    const name = e.target.username.value; // 입력란에 전달된 값을 변수에 저장
    if(!name){
        return alert('이름을 입력하세요');
    }
    try{
        // submit의 재실행 역할 시작 : post 방식의 요청 + parameter:name도 같이 전송
        await axios.post('/user', {name}); // submit의 재실행 역할 시작
        getUser();
    }catch(err){
        console.error(err);
    }
    e.target.username.value = '';
});