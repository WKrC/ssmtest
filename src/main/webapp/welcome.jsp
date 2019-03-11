<%--
  Created by IntelliJ IDEA.
  User: wukangrong
  Date: 2019/3/8
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hello World!</title>
    <link rel="stylesheet" type="text/css" href="static/assets/css/base.css" />
</head>
<body>
<!-- COIDEA:header START -->
<header class="coidea-header">

</header>
<!-- COIDEA:header END -->

<!-- COIDEA:demo START -->
<section id="ci-particles">
    <canvas id="canvas" ></canvas>
    <h2 id="headline">Welcome!</h2>
</section>
<!-- COIDEA:demo END -->

<script src="static/assets/js/demo.js"></script>
<script type="text/javascript">
    $(function(){
        var flag = $("#headline").innerText;
        function test(flag){
            switch (flag){
                case "Welcome!":
                    $("#headline").innerHTML = "3";
                    break;
                case "3":
                    $("#headline").innerHTML = "2";
                    break;
                case "2":
                    $("#headline").innerHTML = "1";
                    break;
                case "1":
                    break;
            }
            history.go(0);//可以换成上一篇博客的任何一种方法。
        }
        setInterval(test, 1000);
    })
</script>
</body>
</html>
