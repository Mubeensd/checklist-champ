<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="edittask" method="post">
<table>
<tr>
<th><label>Task Name :</label></th>
<td><input type="text" name="taskname" placeholder="Enter task to do" required></td>



<th><label> Despription </label></th>
<td><input type="text" name="description" placeholder="Enter Description" required></td>


<td><input type="text" id="idpass" hidden name="id"></td>



<td><button type="submit"> Edit </button></td>

</tr>
</table>
</form>
</body>
<script >
let key=window.location.search;
let urlParm=new URLSearchParams(key);
let getid=urlParm.get('id');
let input2=document.querySelector("#idpass");
input2.value=getid;

</script>
</html>