<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<h2>Hello World!</h2>
<input id="button" type="button" value="请求">
<form id="one" action="twoAction" method ="post">
    <span>名字:</span><input name="name" type="text">
    <span style="display: block;"></span>
    <span>密码:</span><input name="password" type="text">
    <input id="button2" type="submit" value="另一个请求">
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#button").click(function () {
            $.ajax({
                type: "POST",
                url : "oneAction",
                data: $("#one").serialize(),
                async : false,
                success : function (data) {
                    console.info(data);
                    data.forEach(function (node) {
                        alert(node);
                    })
                }
            })
        })
    })
</script>
</body>
</html>
