const crypto = require('crypto');
console.log('base64:',crypto.createHash('sha512').update('password')
.digest('base64'));
//console.log('base64:',crypto.createHash('sha512').update('password').update('other')
//.digest('base64'));
//console.log('base64:',crypto.createHash('sha512').update('other')
//.digest('base64'));
console.log('hex:',crypto.createHash('sha512').update('password')
.digest('hex'));
//console.log('base64:',crypto.createHash('sha512').update('another')
//.digest('base64'));

// pbkdf2
crypto.randomBytes(64, (err,buf)=>{
    const salt = buf.toString('base64');
    console.log('salt: ',salt);
    crypto.pbkdf2('password',salt,100000,64, 'sha512',
    (err,key)=>{
        console.log('pw:',key.toString('base64'));
    });
});
