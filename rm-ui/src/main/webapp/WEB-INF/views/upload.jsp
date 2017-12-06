<html>
<body>

<center>
        <h1>File Upload to Database Demo</h1>
        <form method="post" action="uploadServlet" modelAttribute="fileBucket" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>File to upload: </td>
                    <td><input type="file" name="file" size="50"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Upload">
                    </td>
                </tr>
            </table>
        </form>
</center>
    
</body>
</html>
