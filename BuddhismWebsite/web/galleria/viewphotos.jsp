<%-- 
    Document   : viewphotos
    Created on : 2012-8-30, 23:31:39
    Author     : EthanPan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.buddhism.model.Post"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>照片浏览</title>
        

        <!-- load jQuery -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
       <!-- load Galleria -->
        <script src="<%=path%>/galleria/galleria-1.2.7.min.js"></script>

    </head>
    <body>
       
        <div class="content">
            <h1><s:property value="title"/></h1>
            <p><s:property value="packetDescription"/></p>

            <!-- Adding gallery images. We use resized thumbnails here for better performance, but it’s not necessary -->

            <div id="galleria">

                <s:iterator value="medium" id="media">
                    <p>
                        <img data-title="图片名称" date-description="<s:property value="mediaDesc" />" src="<s:property value="mediaUrl" />" />
                    </p>
                </s:iterator>


            </div>

        </div>

        <script>

            // Load the classic theme
            Galleria.loadTheme('<%=path%>/galleria/galleria.classic.min.js');

            // Initialize Galleria
            Galleria.run('#galleria');

        </script>
    </body>
</html>
