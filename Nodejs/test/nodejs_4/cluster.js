const cluster = require('cluster');
const http = require('http');
const numCPUs = require('os').cpus().length;

if(cluster.isMaster){
    console.log(`master process id: ${process.pid}`);
    for(let i=0; i<numCPUs; ++i){
        cluster.fork();
    }

    cluster.on('exit',(worker,code,signal)=>{
        console.log(`${worker.process.pid}번 워커가 종료.`);
        //cluster.fork();
    });
}
else{
    http.createServer((req,res)=>{
        res.write('<h1>hello node!</h1>');
        res.end('<p>Hello Cluster!</p>');
        setTimeout(()=>{
            process.exit(1);
        },1000);
    }).listen(8085);

    console.log(`${process.pid}번 워커 실행.`);
}
