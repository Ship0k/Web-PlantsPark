<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Update</title>
    <link rel="shortcut icon" href="images/favicon.png" type="image/png">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h4>Enter a new title for the plant</h4>
<form id="back2" name="back2" action="" method="get">
    <input type="text" name="name2">
    <input class="ok" type="button" value="OK" onclick="getServlet('/update')"><br>
    <br>
    <input class="ll" type="button" value="Back" onclick="getServlet('/home')">
</form>
</body>
</html>

<script>
    function getServlet(srvUrl) {
        var form = document.getElementById("back2");
        form.action = srvUrl;
        form.submit();
    }
</script>

