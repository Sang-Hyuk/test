<%@ page contentType="text/html; charset=euc-kr" %>

<html>
<head>
<title>Model2 MVC Shop</title>
</head>
<script type="text/javascript">
function notice_getCookie(name)
{
var nameOfCookie = name +"=";
var x = 0;
while(x <= document.cookie.length)
{
 var y = (x+nameOfCookie.length);
 if(document.cookie.substring(x,y)==nameOfCookie){
  if((endOfCookie=document.cookie.indexOf(";",y))== -1)
   endOfCookie = document.cookie.length;
  return unescape(document.cookie.substring(y,endOfCookie));
 }
 x = document.cookie.indexOf(" ",x)+1;
 if(x==0) break;
} 
return "";
}

function pop_up(){
	if(notice_getCookie("popup") != "on"){
	window.open('popup.jsp', 'ÆË¾÷Ã¢', 'width=350,height=400,top=50,left=150');
	}
}
</script>
<frameset rows="80,*" cols="*" frameborder="NO" border="0" framespacing="0" onload="pop_up()">
  
  <frame src="/layout/top.jsp" name="topFrame" scrolling="NO" noresize >
  
  <frameset rows="*" cols="160,*" framespacing="0" frameborder="NO" border="0">
    <frame src="/layout/left.jsp" name="leftFrame" scrolling="NO" noresize>
    <frame src="" name="rightFrame"  scrolling="auto">
  </frameset>

</frameset>

<noframes>
<body>
</body>
</noframes>

</html>