<%@ page import="java.util.Date" %>
<!-- 支持页面中文展示 -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<!-- 使用jstl处理页面逻辑 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<h3>一行JAVA代码</h3>
<p>
    今天的日期是：<%=(new Date())%>
</p>
<h3>多行JAVA代码</h3>
<p>
    你的IP地址是：
    <%
        out.println("Your IP Address is " + request.getRemoteAddr()+"</br>");
        out.println("一段代码");
    %>
</p>
<h3>循环次数</h3>
<%
    int count = (int)session.getAttribute("count");
    for (int fontSize=1;fontSize<=count;fontSize++){
%>
good good study!day day up!<br>
    }
<%}%>
<h3>标签 c:if</h3>
<c:if test="${username != null}">
    <p>用户名为：${username}</p>
</c:if>
<h3>标签 c:choose</h3>
<c:choose>
    <c:when test="${salary <= 0}">
        狗带
    </c:when>
    <c:when test="${salary > 1000}">
        能活
    </c:when>
    <c:otherwise>
        没有
    </c:otherwise>
</c:choose>
<h3>布局</h3>
<!-- 此处为静态包含，在翻译阶段执行 -->
<%@include file="footer.jsp"%>
<!-- 此处为动态包含，在请求处理阶段执行 -->
<jsp:include page="footer.jsp" flush="true"/>
</html>

