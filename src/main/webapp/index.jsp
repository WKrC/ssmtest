<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<h2>Hello World!</h2>
<input id="button" type="button" value="请求">
<form action="twoAction" method ="post">
    <input id="button2" type="submit" value="另一个请求">
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#button").click(function () {
            $.ajax({
                type: "POST",
                url : "oneAction",
                async : false,
                success : function (data) {
                    data.forEach(function (node) {
                        console.info(node);
                    })
                }
            })
        })
    })
</script>
</body>
</html>
