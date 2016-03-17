
<%@ include file="/WEB-INF/jsp/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="wrap">	
	<div class="erhtm">
		<div class="erbg">
			<div class="ercont">
				<img src="/images/error/cont.gif" alt="您访问的页面不存在或已删除..." />
				<div class="btn">
				您可以<a href="<c:url value='/0_0_0.html'/>" class="blink">逛逛其他商品</a>，或者将在<span class="countdown" id="timeCountSecond">3</span>秒后自动转入<a href="<c:url value='${idsurl }'/>" class="blink">买卖宝首页</a>，请稍候...
				</div>
			</div>
		</div>
	</div>
</div>