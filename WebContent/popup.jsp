<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<title>Insert title here</title>
</head>
<script type="text/javascript"> 
function setCookie(name, value, expiredays)
{
 var todayDate = new Date();
 todayDate.setDate(todayDate.getDate()+expiredays);
 document.cookie = name+"="+escape(value)+"; path=/; expires="+todayDate.toGMTString() +";"
}
function closeWin()
{
 if(document.pop.close.checked)
 {
  setCookie("popup","on",1);
  self.close();
 } 
}
</script>
<body >

<h2>Todat's Product</h2><br/><hr/>
<i><marquee bgcolor="#FF0000"><h3>�Ｚ ��Ʈ�� ��Ư�� 70% ��� ����!!!!</h3></marquee></i><hr/>


<form name="pop">
�Ϸ絿�� â �ݱ� <input name="close" type="checkbox" value="" onclick="javascript:closeWin()">
</form>

</body>
</html>