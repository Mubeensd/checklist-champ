<%@page import="dao.TodoDao"%>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="dto.Todotask"%>
<%@page import="dto.TodoUser"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.util.*"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	align-items: center;
	height: 30px;
	width: 90vw;
	
}
th{
	background: rgb(0,0,0,0.1);
	color:blue;
	
}
tr{
	box-shadow:0px 0px 1px rgb(0,0,0,1);
	
}
td{
	box-shadow:0px 0px 1px rgb(0,0,0,0.1);
	
}

</style> 
</head>
<body style="min-height: 100vh;display: flex;justify-content: center;align-items: center;">
<%List<Todotask> lists=new ArrayList();%>
<% if(session.getAttribute("user")!=null){
	TodoUser user = (TodoUser)session.getAttribute("user");
	lists = new TodoDao().findbytaskid(user.getId());
}%>

<table border="0" cellspacing="0" cellpadding="10px" style="text-align:center;box-shadow:0px 0px 10px rgb(0,0,0,0.1);
	border:none;">
<tr>
<th>Task name</th>
<th>Discription</th>
<th>Time</th>
<th>Status</th>
<th>Edit</th>
<th>Delete</th>
</tr>

<%if(!lists.isEmpty()){ %>
<%for(Todotask task:lists){ %>

<tr>

<td> <%=task.getTaskName() %></td>
<td> <%=task.getDescirption()%></td>
 <td> <%=task.getCreatedTime()%></td>
 <td> <%
    if(task.isStatus()){
    	%>Completed<%
    }else{
    	%><a href="chageStatus?id=<%=task.getTaskNumber()%>"><button>pending</button></a>
    
    	
  <% }%>  
    	
    
    

 
 </td> 
 
 <%  if(task.isStatus()!=true){ %>  
 <td> <a href="edit.jsp?id=<%=task.getTaskNumber()%>"><button>Edit</button></a></td>
<%} 
else{
	%>
	<td> <a href="edit.jsp?id=<%=task.getTaskNumber()%>"><button disabled="disabled">Edit</button ></a></td>
	<%
	
}%>
<td> <a href="delete?id=<%=task.getTaskNumber()%>"><button>Delete</button></a></td>
</tr>
<%} }%>


<tr>

<tr>
  <td colspan="3" style="text-align:center;border:none;padding: 30px"><a href="addTask.html"><button >Add Task</button></a></td>
<td colspan="3" style="border:none;"><a href="logout"><button >logout</button></a></td>

</tr>
</table>

<script type="text/javascript">
	<% if(session.getAttribute("msg")!=null){ %>
		alert('<%= session.getAttribute("msg")%>');
	<% session.setAttribute("msg", null);}%>
</script>

</body>
</html>