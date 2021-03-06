<%-- 
    Document   : articleList
    Created on : 2012-7-1, 1:44:26
    Author     : GodBlessedMay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.buddhism.model.Post"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<%
    String path = request.getContextPath(); 
%>  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <script type='text/javascript' src='js/dropdown.js'></script>
        <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
        <script type='text/javascript' src='js/picture_show.js'></script>	
        <title><s:property value="title"/></title>
                <link href="<%=path%>/admin/styles/layout.css" rel="stylesheet" type="text/css" />
        <link href="<%=path%>/admin/styles/wysiwyg.css" rel="stylesheet" type="text/css" />
        <!-- Theme Start -->
        <link href="<%=path%>/admin/themes/blue/styles.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" media="all" type="text/css" href="css/buddhism_page.css">

        <!-- Theme Start -->
    </head>
    <body>
        <div class="wrapper">
            <%@ include file="/jsp/banner_nav.jsp" %>
            <div class="content">
                <div class="primary">
                    <div class="article">
                        <div class="location">
                            <s:property value="nav" escape="false"/>
                    </div>
                        <%
                            List<Post> posts = (List<Post>)session.getAttribute("posts");
                            
                            for (int i = 0; i != posts.size(); i++)
                            {
                        %>

                            <div class="article-list-item clear">
                                <div class="title  f16 cr1"><a class="cr1" href="articlePage?id=<%= posts.get(i).getId() %>&&index=<%= i %>&&parentType=<s:property value="type"/>" ><%= posts.get(i).getPostTitle() %></a>
                                    <div class="f12 cb2" style="float:right">2012/10/10 </div>
						<div class="cb2 f12" style="float:right">浏览次数 : <%= posts.get(i).getPostClickTimes() %>&nbsp;&nbsp;|&nbsp;&nbsp;</div>
                                </div>
                                
                               
                            </div>
                            <div class="dashline"></div>
                        <%
                            }
                        %>
                    </div>
                    <ul class="pagination">
                        <s:if test="currentIndex != 0">
                            <li class="text"><a href="previousPageAction">前一页</a></li>
                        </s:if>
                        <li class="page"><a href="#" title=""><s:property value="currentIndex + 1"/></a></li>
                        <%

                            int currentIndex = (Integer)request.getAttribute("currentIndex");
                            int maxIndex = (Integer)(request.getAttribute("maxIndex"));

                            int pageCount = maxIndex - currentIndex;

                            if (pageCount > 3)
                                pageCount = 3; 

                            for (int i = 0; i < pageCount; i++)
                            {


                        %>
                            <li><a href="JumpToManagement.action?currentIndex=<%=currentIndex + i%>" title=""><%=i + currentIndex + 1%></a></li>
                        <%
                            }
                        %>
                        <s:if test="currentIndex < maxPage">
                            <li class="text"><a href="nextPageAction" title="">后一页</a></li>
                        </s:if>
                    </ul>

                </div>
                <%@include file="/jsp/right_nav.jsp" %>
            </div>
            <%@include  file="/jsp/footer.jsp" %>
        </div>
    </body>
</html>

