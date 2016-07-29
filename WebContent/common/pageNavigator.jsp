<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	${ request.requestURI }
	
	<c:if test="${ pageVO.currentPage <= pageVO.pageUnit }">
			�� ����
	</c:if>
	<c:if test="${ pageVO.currentPage > pageVO.pageUnit }">
			<a href="javascript:fncGetUserList('${ pageVO.currentPage-1}')">�� ����</a>
	</c:if>
	
	<c:forEach var="i"  begin="${pageVO.beginPage}" end="${pageVO.endPage}" step="1">
		<a href="javascript:fncGetUserList('${ i }');">${ i }</a>
	</c:forEach>
	
	<c:if test="${ pageVO.endPage >= pageVO.totalpage }">
			���� ��
	</c:if>
	<c:if test="${ pageVO.endPage < pageVO.totalpage }">
			<a href="javascript:fncGetUserList('${pageVO.endPage+1}')">���� ��</a>
	</c:if>