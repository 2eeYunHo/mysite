<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<c:forEach items='${list }' var='vo' varStatus='status'>
					<table class="tbl-ex">
						<tr>
							<th colspan="101">글보기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td>${vo.title }</td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<div class="view-content">
									${fn:replace(vo.contents, newLine, "<br/>") }
								</div>
							</td>
						</tr>
					</table>
					<div class="bottom"><!-- 답글버튼 여기만들기 -->
						<a href="${pageContext.request.contextPath }/board">글목록</a>
						<c:choose>
							<c:when test="${empty authUser }"></c:when>
							<c:when test="${authUser.no eq vo.user_no }">
								<a href="${pageContext.request.contextPath }/board?a=modifyform&no=${vo.no}">글수정</a>
								<a href="${pageContext.request.contextPath }/board?a=writeform&no=${vo.no}&groupNo=${vo.group_no}&orderNo=${vo.order_no}&depth=${vo.depth}">답글달기</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath }/board?a=writeform&no=${vo.no}&groupNo=${vo.group_no}&orderNo=${vo.order_no}&depth=${vo.depth}">답글달기</a>
							</c:otherwise>
						</c:choose>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/includes/navigation.jsp">
		<c:param name="menu" value="board"/>
	</c:import>
	<c:import url="/WEB-INF/views/includes/footer.jsp" />
</body>
</html>