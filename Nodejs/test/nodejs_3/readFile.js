const fs = require('fs');

fs.readFile('./readme.txt', (err,data)=>{
    if(err){
        throw err;
    }
    console.log(data);
    console.log(data.toString());
});

fs.writeFile('./writeme.txt','put any statement', (err)=>{
    if(err){
        throw err;
    }
    fs.readFile('./writeme.txt',(err,data)=>{
        if(err){
            throw err;
        }
        console.log(data.toString());
    });
});