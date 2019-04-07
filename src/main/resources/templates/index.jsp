<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=IOS-8859-1">
<title>Insert title here</title>
</head>

    <script type="text/javascript">
        function sendCode() {
            var number = window.document.getElementById("phoneNumber").value;
            window.open("code?phoneNumber=" + number);
            window.alert('发送验证码');
        }

    </script>

<body>

    <form action="SystemManage.jsp" method="post">
        <input type="text" id="phoneNumber" name="phoneNumber" placeholder="手机号码" pattern="^1[3|4|5|7|8]\d{9}$" required="required" >
        <br><br>
        <input type="text" name="code" placeholder="验证码" required="required" >
        <button type="button" onclick="sendCode()">发送验证码</button>
        <br><br>
        <button type="submit">登录</button>
    </form>

</body>
</html>
