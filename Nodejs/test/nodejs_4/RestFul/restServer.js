const http = require('http');
const fs = require('fs');

const users={};

http.createServer( (req,res) => {
    // GET process start
    if(req.method === 'GET'){

        if(req.url==='/'){
            return fs.readFile('./restFront.html', (err,data)=>{
                if(err){
                    throw err;
                }
                res.end(data);
            });

        }

        else if(req.url==='/about'){
            return fs.readFile('./about.html', (err,data)=>{
                if(err){
                    throw err;
                }
                res.end(data);
            });
        }

        else if(req.url==='/users'){
            return res.end(JSON.stringify(users));
        }

        return fs.readFile(`.${req.url}`, (err,data)=>{
            if(err){
                res.writeHead(404,'NOT FOUND');
                return res.end('NOT FOUND');
            }
            return res.end(data);
        });
    }
    // GET process end

    // POST process start
    else if(req.method==='POST'){
        if(req.url === '/users'){
            let body ='';
            req.on('data',(data)=>{
                body += data;
            });
            return req.on('end',()=>{
                console.log('POST body: ', body);
                const {name} = JSON.parse(body);
                const id = new Date(); // + 의 뜻은?
                users[id] = name;
                res.writeHead(201);
                res.end('success to register');
            });
        }
    }
    // POST process end

    // PUT process start
    else if(req.method==='PUT'){
        if(req.url.startsWith('/users/')){
            const key = req.url.split('/')[2];
            let body = '';
            req.on('data',(data)=>{
                body+=data;
            });
            return req.on('end',()=>{
                console.log('PUT body: ', body);
                users[key]=JSON.parse(body).name;
                return res.end(JSON.stringify(users));
            });
        }
    }
    // PUT process end

    // DELETE process start
    else if(req.method==='DELETE'){
        if(req.url.startsWith('/users/')){
            const key = req.url.split('/')[2];
            delete users[key];
            return res.end(JSON.stringify(users));
        }
    }
    // DELETE process end


    res.writeHead(404,'NOT FOUND');
    return res.end('NOT FOUND');

}).listen(8085,()=>{
    console.log('8085 port is waiting for request...');
}); // end createServer