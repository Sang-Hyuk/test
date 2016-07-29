<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	${ request.requestURI }
	
	<c:if test="${ pageVO.currentPage <= pageVO.pageUnit }">
			◀ 이전
	</c:if>
	<c:if test="${ pageVO.currentPage > pageVO.pageUnit }">
			<a href="javascript:fncGetUserList('${ pageVO.currentPage-1}')">◀ 이전</a>
	</c:if>
	
	<c:forEach var="i"  begin="${pageVO.beginPage}" end="${pageVO.endPage}" step="1">
		<a href="javascript:fncGetUserList('${ i }');">${ i }</a>
	</c:forEach>
	
	<c:if test="${ pageVO.endPage >= pageVO.totalpage }">
			이후 ▶
	</c:if>
	<c:if test="${ pageVO.endPage < pageVO.totalpage }">
			<a href="javascript:fncGetUserList('${pageVO.endPage+1}')">이후 ▶</a>
	</c:if>