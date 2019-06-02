<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Insert</title>
    <link rel="shortcut icon" href="images/favicon.png" type="image/png">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h4>Enter plant title</h4>
<form id="back" name="back" action="" method="get">
    <input type="text" name="name">
    <input class="ok" type="button" value="OK" onclick="getServlet('/insert')"><br>
    <br>
    <input class="ll" type="button" value="Back" onclick="getServlet('/home')">
</form>
</body>
</html>

<script>
    function getServlet(srvUrl) {
        var form = document.getElementById("back");
        form.action = srvUrl;
        form.submit();
    }
</script>
