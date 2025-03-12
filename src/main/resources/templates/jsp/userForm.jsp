
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>查询所有用户</title>
  <!-- 引入 Bootstrap -->
  <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <div class="row clearfix">
    <div class="col-md-12 column">
      <div class="page-header">
        <h1>
          <small>用户列表 —— 显示所有用户</small>
        </h1>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-md-4 column">
      <a class="btn btn-primary" href="${pageContext.request.contextPath}/user/toAddUser">新增</a>
    </div>
    <div class="col-md-4 column">
      <a class="btn btn-primary" href="${pageContext.request.contextPath}/user/allUser">显示所有用户</a>
    </div>
    <div class="col-md-4 column">
      <form class="form-inline" action="${pageContext.request.contextPath}/user/queryUser" method="post" style="float: right">
        <input type="text" name="userName" class="form-control" placeholder="输入用户名" required>
        <input type="submit" value="查询" class="btn btn-primary">
      </form>
    </div>
  </div>
  <div class="row clearfix">
    <div class="col-md-12 column">
      <table class="table table-hover table-striped">
        <thead>
        <tr>
          <th>用户id</th>
          <th>用户名</th>
          <th>密码</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.get('userList')}">
          <tr>
            <td>${user.id}</td>
            <td>${user.userName}</td>
            <td>${user.password}</td>
            <td>
              <a href="${pageContext.request.contextPath}/user/toUpdateUser?id=${user.getId()}">更改</a> |
              <a href="${pageContext.request.contextPath}/user/delUser/?id=${user.getId()}">删除</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>
