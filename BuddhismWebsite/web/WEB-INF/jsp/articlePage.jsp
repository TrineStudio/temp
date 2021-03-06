<%-- 
    Document   : articlePage
    Created on : 2012-6-30, 16:09:57
    Author     : GodBlessedMay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%
    String path = request.getContextPath(); 
%> 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
    <link rel="stylesheet" media="all" type="text/css" href="css/buddhism_page.css">
    <script type='text/javascript' src='js/dropdown.js'></script>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
    
    <title><s:property value="post.postTitle" /></title>
    
</head>
<body>
    <div class="wrapper">
        <%@ include file="/jsp/banner_nav.jsp" %>
        <div class="content">
            <div class="primary">
                <div class="article clear">
                    <div class="location">
                        <s:property value="nav" escape="false"/>
                    </div>
                    <div class="article_title cr1">
                        <s:property value="post.postTitle" />
                    </div>
                    <div class="article_info">
                        时间： 点击次数：
                        <div class="article_share" style="padding: 0px;">
                        <!-- JiaThis Button BEGIN -->
                        <div class="jiathis_style">
                            <span class="jiathis_txt">分享到：</span>
                            <a class="jiathis_button_twitter"></a>
                            <a class="jiathis_button_fb"></a>
                            <a class="jiathis_button_renren"></a>
                            <a class="jiathis_button_kaixin001"></a>
                            <a class="jiathis_button_tsina"></a>
                            <a class="jiathis_button_qzone"></a>
                            <a href="http://www.jiathis.com/share?uid=1641922" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
                            <a class="jiathis_counter_style"></a>
                        </div>
                        <script type="text/javascript">var jiathis_config = {data_track_clickback:true};</script>
                        <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1334590586743539" charset="utf-8"></script>
                        <!-- JiaThis Button END -->			
                    </div>
                    </div>
                    <div class="article_content">
                        
                        <s:property value="post.postContent" escape="false"/>
                    </div>
                    <div class="article_share">
                        <!-- JiaThis Button BEGIN -->
                        <div class="jiathis_style">
                            <span class="jiathis_txt">分享到：</span>
                            <a class="jiathis_button_twitter"></a>
                            <a class="jiathis_button_fb"></a>
                            <a class="jiathis_button_renren"></a>
                            <a class="jiathis_button_kaixin001"></a>
                            <a class="jiathis_button_tsina"></a>
                            <a class="jiathis_button_qzone"></a>
                            <a href="http://www.jiathis.com/share?uid=1641922" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
                            <a class="jiathis_counter_style"></a>
                        </div>
                        <script type="text/javascript">var jiathis_config = {data_track_clickback:true};</script>
                        <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1334590586743539" charset="utf-8"></script>
                        <!-- JiaThis Button END -->			
                    </div>
                    <div class="article_bottom">
                        <%
            int index = (Integer) session.getAttribute("index");

            if (index != -1) {
                List<Post> posts = (List<Post>) session.getAttribute("posts");

                int nextId = -1;
                int previousId = -1;
                int nextIndex = -1;

                if (index != 0) {
                    previousId = posts.get(index - 1).getId();
                }

                if (index < posts.size() - 1 && index >= 0) {
                    nextId = posts.get(index + 1).getId();
                }

                if (previousId != -1) {
                    nextIndex = index - 1;
        %>
        <a href="articlePage?id=<%=previousId%>&&index=<%=nextIndex%>" class="cr1">上一页&nbsp;&nbsp;</a>
        <%
            }

            if (nextId != -1) {
                nextIndex = index + 1;
        %>
        <a href="articlePage?id=<%=nextId%>&&index=<%=nextIndex%>" class="cr1">下一页</a>
        <%
                }
            }
        %>
                    </div>
                </div>

            </div>
            <%@include file="/jsp/right_nav.jsp" %>
        </div>
        
        <%@include  file="/jsp/footer.jsp" %>
    </div>
    <script>
        $(document).ready(function(){
                $('img').each(function() {   
                var maxWidth =500; // 图片最大宽度   
                var maxHeight =500;    // 图片最大高度   
                var ratio = 0;  // 缩放比例  
                 var width = $(this).width();    // 图片实际宽度   
                 var height = $(this).height();  // 图片实际高度     // 检查图片是否超宽   
                 if(width > maxWidth){       
                 ratio = maxWidth / width;   // 计算缩放比例       
                 $(this).css("width", maxWidth); // 设定实际显示宽度       
                 height = height * ratio;    // 计算等比例缩放后的高度       
                  $(this).css("height", height);  // 设定等比例缩放后的高度   
                  }     // 检查图片是否超高  
                   if(height > maxHeight){       
                   ratio = maxHeight / height; // 计算缩放比例      
                    $(this).css("height", maxHeight);   // 设定实际显示高度       
                    width = width * ratio;    // 计算等比例缩放后的高度       
                    $(this).css("width", width);    // 设定等比例缩放后的高度   
                    }});
            });
    </script>
</body>
</html>
