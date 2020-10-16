
function login() {
	if(document.getElementById("userid").value == "") {
		alert("아이디 입력하십시오.");
		return;
	} else if(document.getElementById("userpwd").value == "") {
		alert("비밀번호 입력하십시오.");
		return;
	} else {
		document.getElementById("loginform").action = "[$]\\{root\\}/member?act=login";
		document.getElementById("loginform").submit();
	}
}
	  
function moveJoin() {
	document.location.href = "${root}/member?act=join";
}	