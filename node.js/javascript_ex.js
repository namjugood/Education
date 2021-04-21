const k = 20;
const pm = new Promise( (resolve, reject) => {
    if(k%2==0){
        resolve('짝수입니다');
    }else{
        reject('홀수입니다');
    }
} );

pm
     .then((msg1)=>{
        console.log(msg1);
        return new Promise((resolve, reject)=>{
           resolve('짝수입니다') 
        });
     })
     .then((msg2)=>{
        console.log(msg2);
        return new Promise((resolve, reject)=>{
           resolve('짝수입니다') 
        });
     })
     .then((msg3)=>{
        console.log(msg3)
     })
     .catch( (error) => {
        console.error(error);
    });

