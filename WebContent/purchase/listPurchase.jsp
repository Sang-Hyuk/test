<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncGetUserList(page){
	document.getElementById("page").value = page;
	document.detailForm.submit();
}

-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 ${ pageVO.totalCount } 건수, 현재${ pageVO.currentPage } 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:set var="no" value="${list.size()}"/>

	<c:forEach var="vo" items="${ list }">
	<tr class="ct_list_pop">
		<td align="center">
		
		<c:if test="${ vo.tranCode.trim() == '1' || vo.tranCode.trim() == '0'}">
			<a href="/getPurchase.do?tranNo=${vo.tranNo}">${ no }</a>
			<c:set var="no" value="${no-1}"/>
		</c:if>
		<c:if test="${ vo.tranCode.trim() != '1' && vo.tranCode.trim() != '0'}">
			${ no }
			<c:set var="no" value="${no-1}"/>
		</c:if>
		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=${ vo.buyer.userId }">${ vo.buyer.userId }</a>
		</td>
		<td></td>
		<td align="left">${ vo.buyer.userName }</td>
		<td></td>
		<td align="left">${ vo.receiverPhone }</td>
		<td></td>
		<td align="left">현재

				<c:if test="${ vo.tranCode.trim() == '1' }">
					구매완료
				</c:if>
				<c:if test="${ vo.tranCode.trim() == '2' }">
					배송중
				</c:if>
				<c:if test="${ vo.tranCode.trim() == '3' }">
					배송완료
				</c:if>
				상태 입니다.
				
				
				</td>
		<td></td>
		<td align="left">
			<c:if test="${ vo.tranCode.trim() == '2' }">
			<a href="/updateTranCode.do?tranNo=${ vo.tranNo }&tranCode=3">물건도착</a>
			</c:if>
			
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		<%-- <c:set var="doneLoop" value="false"/>
		<c:forEach var="k" begin="${ pageVO.beginPage }" step="1" end="${ pageVO.endPage }">
		<c:if test="${ not doneLoop }">
			<c:if test="${ k%pageVO.pageSize == 1 && k != 1 }">
			<a href="/listPurchase.do?page=${ k-1 }">이전</a>
			</c:if>
			<a href="/listPurchase.do?page=${ k }">${ k }</a>
			<c:if test="${ k==(pageVO.totalpage-1) }">
				<c:set var="doneLoop" value="true"/>
			</c:if>
			<c:if test="${ k%pageVO.pageSize == 0 }">
				<a href="/listPurchase.do?page=${ k+1 }">다음</a>
			</c:if>
		</c:if>
		</c:forEach> --%>
		<input type="hidden" name="page" id="page">
		<jsp:include page="../common/pageNavigator.jsp"/>
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>