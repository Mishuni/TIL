const crypto = require('crypto');
// bidirection cryptography
const cipher = crypto.createCipher('aes-256-cbc','key');
let result = cipher.update('statement','utf8','base64');
result += cipher.final('base64');
console.log('crypto: ',result);

const decipher = crypto.createDecipher('aes-256-cbc','key');
let result2 = decipher.update(result,'base64','utf8');
result2+= decipher.final('utf8');
console.log('decipher:',result2);
