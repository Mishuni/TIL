const os = require('os');

console.log('------os info-------');
console.log(os.arch());
console.log(os.platform());
console.log(os.type());
console.log(os.uptime());
console.log(os.hostname());
console.log(os.release());

console.log('------os path-------');
console.log(os.homedir());
console.log(os.tmpdir());

console.log('------cpu info-------');
console.log(os.cpus());
console.log(os.cpus().length);

console.log('------mem info-------');
console.log(os.freemem());
console.log(os.totalmem());
//console.log(os.constants);