const string = 'abc';
const num = 1 ;
const boolean =  true;
const obj = {
    outside:{

        inside:{
            key:'value',
        },
    },

};

console.time('total time');
console.log('nomal log');
console.log(string,num,boolean,obj);
console.error('error is console.error');
console.dir(obj,{colors:true, depth:3});
console.dir(obj,{colors:true, depth:0});

console.time('time measurement');
for(let i=0; i<100000; ++i){
    continue;
}
console.timeEnd('time measurement');

function b(){
    console.trace('error location');
}
function a(){
    b();
}

a();
console.timeEnd('total time');