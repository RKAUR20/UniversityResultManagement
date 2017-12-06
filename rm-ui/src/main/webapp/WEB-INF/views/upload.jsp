<html>

<script type="text/javascript">
    function fileValidation() {
        var fileUpload = document.getElementById("fileUpload");
        var file_name = fileUpload.value;
        if (file_name.substr(file_name.lastIndexOf('.') + 1).toLowerCase() != "xml") {
        	alert('Please upload file having extensions .xml only.');
        	return;
        }
    }
</script>

<body>

<center>
        <h1>File Upload to Database Demo</h1>
        <form method="post" modelAttribute="fileBucket" action="uploadServlet" enctype="multipart/form-data" onsubmit="return fileValidation()">
            <table border="0">
                <tr>
                    <td>File to upload: </td>
                    <td><input type="file" id="fileUpload" name="file" required="required"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Upload">
                    </td>
                </tr>
            </table>
        </form>
        <br>Click <a href="home">here</a> to go back to home page.
</center>
    
</body>
</html>
