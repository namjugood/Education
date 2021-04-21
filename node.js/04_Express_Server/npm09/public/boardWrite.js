// 게시글 내용을 받아서 라우터에 보내고,
// 레코드 추가 후 돌아오면 main으로 이동
document.getElementById('write-form').addEventListener('submit', async(e) =>{
    e.preventDefault(); // submit 일시 정지
    
    const writer = e.target.writer.value;
    const subject = e.target.subject.value;
    const content = e.target.text.value;

    // 이미지 업로드
    const formData = new FormData(); // 폼데이터 객체 생성
    formData.append('writer', writer); 
    formData.append('subject', subject) ;
    formData.append('content', content);
    formData.append('image', e.target.image.files[0]); 


    if(!subject){ return alert('제목을 입력하세요'); }
    if(!content){ return alert('내용을 입력하세요'); }

    // 이미지 파일이 있을 경우 formData에 추가하는 내용
    // if(img){
    //     formData.append('image', e.target.image.files[0]);
    // }

    try {
        await axios.post('/boards/write', formData);
        location.href='/main';
    } catch (err) {
        console.error(err);
    }

});


