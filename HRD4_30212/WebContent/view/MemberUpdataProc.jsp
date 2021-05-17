<%@page import="java.sql.Date"%>
<%@page import="vo.MemberVO"%>
<%@page import="dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BookDAO dao = new BookDAO();
MemberVO vo = new MemberVO();

request.setCharacterEncoding("utf-8");
int custno = Integer.parseInt(request.getParameter("custno"));
String custname = request.getParameter("custname");
Date joindate = Date.valueOf(request.getParameter("joindate"));
String grade = request.getParameter("grade");
String address = request.getParameter("address");

vo.setCustno(custno);
vo.setCustname(custname);
vo.setJoindate(joindate);
vo.setGrade(grade);
vo.setAddress(address);

int n = dao.MemberUpdata(vo);

if (n > 0) {
	response.sendRedirect("/view/MemberList.jsp");
} else {
%>
<script>
	history.back();
</script>
<%
	}
%>