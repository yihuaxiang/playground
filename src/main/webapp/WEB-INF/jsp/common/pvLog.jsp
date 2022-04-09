<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
  fetch(`https://playground.fudongdong.com/blog/pv?url=\${encodeURIComponent(location.pathname)}`);
</script>
