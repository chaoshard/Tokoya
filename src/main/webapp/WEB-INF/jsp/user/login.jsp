<!-- 
 * @Class Name : login.jsp
 * @Description : 
 * @
 * @	修正日			修正者		修正内容
 * @ 	---------		---------		-------------------------------
 * @ 	2017.07.12		山木雄矢		最初作成
 * 
 * @author 山木雄矢
 * @since 2017.07.12
 * @version 0.1
 *
 *  Copyright (C) by NuriNubi All right reserved.
 * -->
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/js/jquery-1.10.2.js"/>"></script>
<script src="<c:url value="/js/jquery-ui-1.10.4.custom.js"/>"></script>
<!-- 
< %@include file="../sample/head.jsp"  %>
 -->
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#register").on("click", function() {
			location.assign("/register");
		});
		$("#cancle").on("click", function() {
			location.assign("/home");
		});
	});
</script>
</head>
<body>
	<h2>ログイン</h2>
	<div class="container">
		<div class="loginform">
			<form id="logfrm" action="${url}" method="POST">
				<h2>ログイン</h2>
				<!-- 入力内容 -->
				<p>
				  <span>ユーザーID</span>
				  <input type="text" name="userid" placeholder="Userid">
  			</p>
				<p>
					<span>パスワード</span> <input type="password" name="userpass"
						placeholder="Password">
				</p>
				<p>
					<label for="remember"> <input type="checkbox" id="remember"
						value="remember" /> <span>ログイン情報保持</span>
					</label>
				</p>
				<!-- 各種ボタン -->
				<input type="button" id="login" value="ログイン">
				<button type="submit" name="newmenber">新規登録</button>
				<button type="submit" name="cancel">キャンセル</button>
			</form>
		</div>
	</div>
</body>
</html>