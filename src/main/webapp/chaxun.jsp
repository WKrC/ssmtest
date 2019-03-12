<%--
  Created by IntelliJ IDEA.
  User: wukangrong
  Date: 2019/3/12
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="static/my/css/donghua.css" />
</head>
<body>
<div id="top-head-inner">
    <div id="nav-toggle">
        <div>
            <span></span>
            <span></span>
            <span></span>
        </div>
    </div>
    <nav id="global-nav">
        <ul>
            <li><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp寄件人&nbsp:</span>&nbsp<input type="text"></li>
            <li><span>寄件人手机&nbsp:</span>&nbsp<input type="text"></li>
            <li><span>寄件人地址&nbsp:</span>&nbsp<input type="text"></li>
            <li><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp收件人&nbsp:</span>&nbsp<input type="text"></li>
            <li><span>收件人手机&nbsp:</span>&nbsp<input type="text"></li>
            <li><span>收件人地址&nbsp:</span>&nbsp<input type="text"></li>
            <li><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp备注&nbsp:</span>&nbsp<input type="text"></li>
            <li><span>&nbsp&nbsp&nbsp</span><input id="submitbutton" type="button"  value="提交"></li>
        </ul>
    </nav>
    <div id="nav-bg"></div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    $('#nav-toggle').click(function(){
        $('body').toggleClass('open');
    });
</script>
</body>
</html>
