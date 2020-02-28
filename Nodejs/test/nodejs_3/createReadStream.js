const fs = require('fs');

const readStream = fs.createReadStream('./readme.txt',{highWaterMark:16});
const data = [];
readStream.on('data',(chunk)=>{
    data.push(chunk);
    console.log('data: ',chunk,chunk.length);
    console.log(chunk.toString());

});

readStream.on('end',()=>{
    console.log('end: ',Buffer.concat(data).toString());
});

readStream.on('error',(err)=>{
    console.log('error:',err);
});

// pipe
//const readStream = fs.createReadStream('./readme.txt');
const writeStream = fs.createWriteStream('writeme.txt');
readStream.pipe(writeStream);