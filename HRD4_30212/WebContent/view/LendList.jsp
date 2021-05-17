<%@page import="dao.BookDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.LentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/master/header.jsp" />
<section>
	<p class="title">회원별 대여현황</p>
	<div class="container">
		<%
		BookDAO dao = new BookDAO();
		ArrayList<LentVO> list1 = new ArrayList<LentVO>();
		list1 = dao.getRentList();
			if (!list1.isEmpty()) {
		%>
		<table>
			<tr>
				<th>회원번호</th>
				<th>회원이름</th>
				<th>대여횟수</th>
			</tr>
			<%
				for (LentVO vo : list1) {
			%>
			<tr>
				<td><%=vo.getNo()%></td>
				<td><%=vo.getName()%></td>
				<td><%=vo.getCnt()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			} else {
		%>

		<p style="text-align: center;">등록된 회원이 없습니다.</p>

		<%
			}
		%>
	</div>
	<p class="title">도서별 대여 현황</p>
	<div class="container">
		<%
			ArrayList<LentVO> list2 = new ArrayList<LentVO>();
			list2 = dao.getBookList();
			if (!list2.isEmpty()) {
		%>
		<table>
			<tr>
				<th>도서번호</th>
				<th>대여횟수</th>
			</tr>
			<%
				for (LentVO vo : list2) {
			%>
			<tr>
				<td><%=vo.getNo()%></td>
				<td><%=vo.getCnt()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			} else {
		%>

		<p style="text-align: center;">등록된 회원이 없습니다.</p>

		<%
			}
		%>
	</div>
</section>
<jsp:include page="/master/footer.jsp" />