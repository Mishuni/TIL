process.on('uncaughtException', (err)=>{
    console.error('uncaughtException',err);
});

setInterval(()=>{
    throw new Error('i will break the server');
}, 1000);

setTimeout(()=>{
    console.log('implementation');
},2000);