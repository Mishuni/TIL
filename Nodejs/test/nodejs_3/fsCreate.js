const fs = require('fs');

fs.access('./folder',fs.constants.F_OK|
fs.constants.R_OK|fs.constants.W_OK,(err)=>{
    if(err){
        if(err.code==='ENOENT'){
            console.log('there is no folder like that');
            fs.mkdir('./folder',(err)=>{
                if(err){
                    throw err;
                }
                console.log('success to create the folder');
                fs.open('./folder/file.js','w',(err,fd)=>{
                    if(err){
                        throw err;
                    }
                    console.log('success to create the new file that is empty',fd);
                    fs.rename('./folder/file.js','./folder/newfile.js',(err)=>{
                        if(err){
                            throw err;
                        }
                        console.log('success to rename the file');
                    });
                });
            });
        }
        else{
            throw err;
        }
    }else{
        console.log('there is alread existed');
    }
});

fs.readdir('folder',(err,dir)=>{
    if(err){
        throw err;
    }
    console.log('comfirm the folder ',dir);
    fs.unlink('./folder/newfile.js',(err)=>{
        if(err){
            throw err;
        }
        console.log('success to remove file');
        fs.rmdir('./folder',(err)=>{
            if(err){
                throw err;
            }
            console.log("success to remove the folder");
        });
    });
});
