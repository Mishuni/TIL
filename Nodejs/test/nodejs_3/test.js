const timeout = setTimeout(()=>{
    console.log('1.5s 후 실행');
}, 1500);
let i = 1;
const interval = setInterval(()=>{
    if(i===5){
        console.log('stop!');
        process.exit();
    }
    console.log('1s interval ',i);
    ++i;
},1000);

const timeout2 = setTimeout(
    ()=>{
        console.log("it doesn't implement 3");
    }
, 3000);

setTimeout(()=>{
    clearTimeout(timeout2);
    //clearInterval(interval);
}, 5000);

const immediate = setImmediate(()=>{
    console.log('immediately implement');
});

const immediate2 = setImmediate(()=>{
    console.log('it doesn\'t implement');
});

clearImmediate(immediate2);

console.log(__filename);
console.log(__dirname);

process.nextTick(()=>{
    console.log('nextTick');
});

Promise.resolve().then(()=>console.log('promise'));