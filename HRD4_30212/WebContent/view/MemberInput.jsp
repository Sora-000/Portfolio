<%@page import="dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/master/header.jsp"/>
<%
	BookDAO dao = new BookDAO();
	int n = dao.getMaxNo();
%>

<section>
	<p class="title">회원 등록</p>
	<div class="container">
		<form action="MemberInputProc.jsp" method="post" name="frm">
			<table class="insert">
				<tr>
					<th>회원번호</th>
					<td><input type="text" name="custno" id="custno" value="<%= n %>" readonly="readonly"></td>
				</tr>
				<tr>
					<th>회원이름</th>
					<td><input type="text" name="custname" id="custname"></td>
				</tr>
				<tr>
					<th>가입일자</th>
					<td><input type="date" name="joindate" id="joindate"></td>
				</tr>
				<tr>
					<th>회원등급</th>
					<td>
						<select name="grade" id="grade">
							<option value="A">VIP</option>
							<option value="B">일반</option>
							<option value="C">직원</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="address" id="address"></td>
				</tr>
				<tr>
					<td colspan="2" class="btn">
						<input type="submit" onclick="return checkFrm()" value="등록">
						<input type="reset" onclick="document.frm.custno.focus();" value="다시쓰기">
					</td>
				</tr>
			</table>
		</form>
	</div>
</section>
<script>
	function checkFrm(){
		if(document.frm.custno.value.trim() == ""){
			document.frm.custno.focus();
			alert("회원번호가 입력되지 않았습니다.");
			return false;
		}if(document.frm.custname.value.trim() == ""){
			document.frm.custname.focus();
			alert("회원이름이 입력되지 않았습니다.");
			return false;
		}if(document.frm.custname.value.length > 3){
			document.frm.custname.focus();
			alert("회원이름은 3글자를 초과하면 않됩니다.");
			return false;
		}if(document.frm.joindate.value.trim() == ""){
			document.frm.joindate.focus();
			alert("가입일자가 입력되지 않았습니다.");
			return false;
		}if(document.frm.grade.value.trim() == ""){
			document.frm.grade.focus();
			alert("회원등급이 입력되지 않았습니다.");
			return false;
		}if(document.frm.address.value.trim() == ""){
			document.frm.address.focus();
			alert("주소가 입력되지 않았습니다.");
			return false;
		}else{
			document.frm.submit();
		}
	}
</script>
<jsp:include page="/master/footer.jsp"/>