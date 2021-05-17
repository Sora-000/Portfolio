<%@page import="vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/master/header.jsp" />

<%
	ArrayList<MemberVO> list = new ArrayList<MemberVO>();
	BookDAO dao = new BookDAO();
	list = dao.getMemberList();
%>

<section>
	<p class="title">회원목록 조회 / 수정</p>
	<div class="container">
		<%
			if (!list.isEmpty()) {
		%>
		<table>
			<tr>
				<th>회원번호</th>
				<th>회원이름</th>
				<th>가입일자</th>
				<th>고객등급</th>
				<th>주소</th>
				<th>기능</th>
			</tr>
			<%
				for (MemberVO vo : list) {
			%>
			<tr>
				<td><%=vo.getCustno()%></td>
				<td><%=vo.getCustname()%></td>
				<td><%=vo.getJoindate()%></td>
				<td><%=vo.getGrade()%></td>
				<td><%=vo.getAddress()%></td>
				<td><a href="/view/MemberUpdata.jsp?custno=<%=vo.getCustno()%>">수정</a></td>
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