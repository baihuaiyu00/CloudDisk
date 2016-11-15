<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/4/23
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        var totalFileLength, totalUploaded, fileCount, filesUploaded;

        function debug(s) {
            var debug = document.getElementById('debug');
            if(debug){
                debug.innerHTML = debug.innerHTML + '<br/>' + s;
            }
        }

        function onUploadComplete(e) {
            totalUploaded += document.getElementById('files').files[filesUploaded].size;
            filesUploaded++;
            debug('complete ' + filesUploaded + "of" + fileCount);
            debug('totalUploaded: ' + totalUploaded);
            if(filesUploaded < fileCount){
                uploadNext(e);
            }else{
                var bar = document.getElementById('bar');
                bar.style.width = '100%';
                bar.innerHTML = '100% complete';
                alert('Finished uploading file(s)');

            }

        }

        function onFileSelect(e) {
            var files = e.target.files;
            var output = [];
            fileCount = files.length;
            totalFileLength = 0;
            for(var i=0;i<fileCount;i++){
                var file = files[i];
                output.push(file.name,'(',
                        file.size,'bytes, ',
                        file.lastModifiedDate.toLocaleDateString(),')'
                );
                output.push('<br/>');
                debug('add ' + file.size);
                totalFileLength += file.size;
            }

            document.getElementById('selectedFiles').innerHTML = output.join('');
            debug('totalFileLength:' + totalFileLength);
        }

        function onUploadProgress(e) {
            if(e.lengthComputable){
                var percentComplete = parseInt((e.loaded + totalUploaded) * 100 / totalFileLength);
                var bar = document.getElementById('bar');
                bar.style.width = percentComplete+ '%';
                bar.innerHTML = percentComplete + ' % complete';
            }else{
                debug('unable to compute');
            }
        }

        function onUploadFailed(e) {
            alert("服务器繁忙！");
        }

        function uploadNext(e) {
            var xhr = new XMLHttpRequest();
            var fd = new FormData();
            var file = document.getElementById('files').files[filesUploaded];
            fd.append("multipartFile",file);
            xhr.upload.addEventListener("progress",onUploadProgress,false);
            xhr.addEventListener("load",onUploadComplete,false);
            xhr.addEventListener("error", onUploadFailed,false);
            xhr.open("POST","file_upload",true);
            debug('uploading' + file.name);
            xhr.send(fd);
        }

        function startUpload() {
            totalUploaded = filesUploaded = 0;
            uploadNext();
        }

        window.onload = function () {
            document.getElementById('files').addEventListener('change',onFileSelect,false);//当file栏里的数据发生变化时，调用时间 onFileSelect
            document.getElementById('uploadButton').addEventListener('click',startUpload,false);
        }
    </script>
    <title></title>
</head>
<body>
    <h1>文件上传</h1>
    <div id="progressBar" style='height: 20px;boder:2px solid green'>
        <div id='bar' style='height: 100%;background:#33dd33;width: 0%'>
        </div>
    </div>
    <form>
        <input type="file" id="files" multiple="multiple"/>
        <br/>
        <output id="selectedFiles"></output>
        <input id="uploadButton" type="button" value="上传"/>
    </form>
    <div id='debug' style='height: 100px;border: 2px solid green;overflow: auto;'>
    </div>

</body>

</html>


