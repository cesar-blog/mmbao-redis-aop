<%@ page language="java" contentType="text/html; charset=UTF-8"
         trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include.inc.jsp" %>
<%-- floor-one begin--%>
<div class="floor-middle fl"
     style="border-right:1px solid #cecece;width:633px;height:525px;padding-left:65px;padding-top:40px;">
    <table class="table-one">
        <thead>
        <tr>
            <th>型号</th>
            <th>规格</th>
            <th>电压等级</th>
            <th>交货期</th>
            <th>参考价格</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${dxdlDzList}">
            <tr>
                <td>${item.dzType }</td>
                <td>${item.dzStandard }</td>
                <td>${item.voltage }</td>
                <td>${item.delivert }天</td>
                <td>￥${item.referencePrc }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%-- floor-one end--%>
