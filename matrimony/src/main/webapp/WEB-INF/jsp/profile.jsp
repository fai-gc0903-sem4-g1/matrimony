<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <jsp:useBean id="userDAO" class="com.matrimony.database.UserDAO" />
                    <t:profileLayout>
                        <jsp:attribute name="head">
                            <title>Payment</title>
<style>

</style>
                            <script>
								$(document).ready(function() {
								
								});
								
								
								
							</script>
                        </jsp:attribute>
                        <jsp:attribute name="middle">
                            
                        </jsp:attribute>
                    </t:profileLayout>