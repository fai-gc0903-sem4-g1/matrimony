<%@ tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="homeHead" fragment="true" %>
    <%@attribute name="left" fragment="true" %>
        <%@attribute name="middle" fragment="true" %>
            <%@attribute name="right" fragment="true" %>
                <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
                 <body>
                     <div id="container" class='row'>
                         <div id='left' class='col-lg-2'>
                            <jsp:invoke fragment="left"></jsp:invoke>
                         </div>
                         <div id='middle' class='col-lg-7' style='background-color: #ffffff; border: solid 2px #f4f4f4; border-radius: 6px;'>
								<jsp:invoke fragment="middle"></jsp:invoke>
                         </div>
                         <div id='right' class='col-lg-3'>
								<jsp:invoke fragment="right"></jsp:invoke>
                         </div>
                     </div>
                
                 </body>