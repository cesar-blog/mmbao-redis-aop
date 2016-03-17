<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include.inc.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>redis-aop</title>
    <meta name="description" content="Redis基于AOP缓存方案">
    <meta name="Keywords" content="spring,redis,aop,cache">
</head>
<body class="dls" style="background:#f5f5f5;">
    <%--楼层 --%>
    <div class="wrapper mt20">
        <tiles:insertAttribute name="floor-first"/>
    </div>
    <%--楼层 --%>
</body>
</html>