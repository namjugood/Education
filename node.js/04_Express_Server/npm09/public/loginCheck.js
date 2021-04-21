document.getElementById('login').addEventListener('submit', async(e) =>{
    e.preventDefault(); // submit 일시 정지
    const userid = e.target.userid.value;    
    const pwd    = e.target.pwd.value;
    if(!userid){ return alert('아이디를 확인하세요');  }
    if(!pwd){ return alert('비밀번호를 확인하세요'); }

    try {
        const res = await axios.post('/members/login', {userid, pwd});
        const mem = res.data;

        if(mem.userid==userid && mem.pwd==pwd){
            location.href='/main';  // 정상로그인
        }else if(mem.userid==userid && mem.pwd != pwd){
            // 비번오류
            let m = document.getElementById("msg");
            m.innerHTML = '비밀번호를 확인하세요';
        }else{
            // 아이디 오류
            let m = document.getElementById("msg");
            m.innerHTML = '아이디를 확인하세요';
        }
    } catch (err) {
        console.error(err);
    }
    e.target.pwd.value ='';
});
