
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css" >

<script type="text/javascript">
<!--
function fncGetUserList(page){
	document.getElementById("page").value = page;
	document.getElementById("minPrice").value = '${searchVO.minPrice}';
	document.getElementById("maxPrice").value = '${searchVO.maxPrice}';
	document.getElementById("priceRange").value = '${searchVO.priceRange}';
	
	document.detailForm.submit();
}
function fncSearch(page){
	document.detailForm.submit();
}

function fncPriceSort(){
	
	document.getElementById("priceSort").value = '${searchVO.priceSort}';
	document.detailForm.submit();
}


-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=${ menu }" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					
							상품 목록조회
					
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">

				<option value="0" ${! empty searchVO.searchCondition && searchVO.searchCondition == '0' && !empty searchVO.searchKeyword  ? "selected" : "" }>상품번호</option>
				<option value="1" ${! empty searchVO.searchCondition && searchVO.searchCondition == '1' ? "selected" : "" }>상품명</option>

			</select>
			<input type="text" name="searchKeyword" value="${!empty searchVO.searchKeyword ? searchVO.searchKeyword : ''}" class="ct_input_g" style="width:200px; height:19px" />
			<select name="priceRange" class="ct_input_g" style="width:80px">
				<option value="2" ${! empty searchVO.searchCondition && searchVO.searchCondition == '2' ? "selected" : "" }>상품가격</option>
			</select>
			<input type="text" name="minPrice" value="${!empty searchVO.minPrice ? searchVO.minPrice : ''}" class="ct_input_g" style="width:200px; height:19px" /> ~
			<input type="text" name="maxPrice" value="${!empty searchVO.maxPrice ? searchVO.maxPrice : ''}" class="ct_input_g" style="width:200px; height:19px" />
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
		<td colspan="11" >전체 ${ pageVO.totalCount } 건수, 현재 ${ pageVO.currentPage }  페이지
			<select name="priceSort" class="ct_input_g" style="width:80px" onchange="javascript:fncPriceSort();">
				<option value="" ${searchVO.priceSort == "" ? "selected" : "" }>선택하세요</option>
				<option value="asc" ${! empty searchVO.priceSort && searchVO.priceSort == 'asc' ? "selected" : "" }>가격낮은순</option>
				<option value="desc" ${! empty searchVO.priceSort && searchVO.priceSort == 'desc' ? "selected" : "" }>가격높은순</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
		<c:set var="no" value="${list.size()}"/>

		<c:forEach var="vo" items="${list}">

	<tr class="ct_list_pop">
		<td align="center">${ no }</td>
		<c:set var="no" value="${no-1}"/>
		<td></td>
				<td align="left">
				<c:if test="${ vo.tranCode.trim() == '0' or vo.tranCode.trim() == '1'}">
					<a href="/getProduct.do?prodNo=${ vo.purchaseProd.prodNo }&menu=${ param.menu }&trancode=${ vo.tranCode }">${ vo.purchaseProd.prodName }</a>
				</c:if>
				<c:if test="${ vo.tranCode.trim() != '0' and vo.tranCode.trim() != '1'}">
					${ vo.purchaseProd.prodName }
				</c:if>
				</td>
		<td></td>
		<td align="left">${ vo.purchaseProd.price }</td>
		<td></td>
		<td align="left">${ vo.purchaseProd.regDate }</td>
		<td></td>
		<td align="left">
		
		<c:if test="${ userVO.role == 'admin' }">
			<c:if test="${ vo.tranCode.trim() == '0' }">
				판매중
			</c:if>
			<c:if test="${ vo.tranCode.trim() == '1' && param.menu == 'manage' }">
				구매완료 <a href="/updateTranCodeByProd.do?prodNo=${vo.purchaseProd.prodNo}&tranCode=2">배송하기</a>
			</c:if>
			<c:if test="${ vo.tranCode.trim() == '1' && param.menu == 'search' }">
				구매완료
			</c:if>
			<c:if test="${ vo.tranCode.trim() == '2' }">
				배송중
			</c:if>
			<c:if test="${ vo.tranCode.trim() == '3' }">
				 배송완료
			</c:if>
		</c:if>
		<c:if test="${ userVO.role == 'user' }">
			<c:if test="${vo.tranCode.trim() == '0' }">
				판매중
			</c:if>
			<c:if test="${vo.tranCode.trim() != '0' }">
				재고없음
			</c:if>
		</c:if>
		</td>		
	</tr>
	</c:forEach>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	

</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<input type="hidden" id="page" name="page" value=""/>
		<input type="hidden" id="minPrice" name="minPrice" value=""/>
		<input type="hidden" id="maxPrice" name="maxPrice" value=""/>
		<input type="hidden" id="priceRange" name="priceRange" value=""/>
		<input type="hidden" id="priceSort" name="priceSort" value=""/>
		
		 <jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>
