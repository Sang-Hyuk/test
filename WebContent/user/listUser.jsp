<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>회원 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncGetUserList(page){
	document.getElementById("page").value = page;
	location.href="listUser.do?page="+page+"&menu=search&searchKeyword=${searchVO.searchKeyword}&searchCondition=${searchVO.searchCondition}";
}
function fncSearch(page){
	document.detailForm.submit();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listUser.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">회원 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">

				<option value="0" ${ !empty searchVO.searchCondition &&  searchVO.searchCondition == '0' ? "selected" : ""}>회원ID</option>
				<option value="1" ${ !empty searchVO.searchCondition &&  searchVO.searchCondition == '1' ? "selected" : ""}>회원명</option>
		
			</select>
			<input 	type="text" name="searchKeyword"  value="${!empty searchVO.searchKeyword ? searchVO.searchKeyword : ''}" 
							class="ct_input_g" style="width:200px; height:19px" >
		</td>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncSearch('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체 ${ pageVO.totalCount } 건수, 현재 ${pageVO.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">이메일</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
<%-- 	<% 	
		int no=list.size();
		for(int i=0; i<list.size(); i++) {
			UserVO vo = (UserVO)list.get(i);
	%> --%>
	<c:set var="i" value="0"/>
	<c:forEach var="vo" items="${ list }">
	<tr class="ct_list_pop">
		<c:set var="i" value="${ i+1 }"/>
		<td align="center">${ i }</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=${ vo.userId }">${vo.userId}</a>
		</td>
		<td></td>
		<td align="left">${vo.userName}</td>
		<td></td>
		<td align="left">${vo.email}</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height=""></td>
	</tr>
	</c:forEach>
	<%-- <% } %> --%>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		 <input type="hidden" id="page" name="page" value="1"/>
			<%-- <%for(int i=beginPage;i<=endPage;i++){%>
			<%if(i%pageSize==1 && i!=1){ %>
			<a href="/listUser.do?page=<%=i-1%>&searchKeyword=<%=searchVO.getSearchKeyword()%>&searchCondition=<%=searchVO.getSearchCondition()%>">이전</a>
			<%} %>
			<a href="/listUser.do?page=<%=i%>&searchKeyword=<%=searchVO.getSearchKeyword()%>&searchCondition=<%=searchVO.getSearchCondition()%>"><%=i %></a>
			<%if(i==totalPage){ break; }%>
			<%if(i%pageSize==0){ %>
			<a href="/listUser.do?page=<%=i+1%>&searchKeyword=<%=searchVO.getSearchKeyword()%>&searchCondition=<%=searchVO.getSearchCondition()%>">다음</a>	
			<%}%>
			<%}%> --%>
			<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->
</form>
</div>

</body>
</html>