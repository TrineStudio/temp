<%@page import="java.util.List"%>
<%@page import="com.buddhism.model.PicturePost"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
%>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <link rel="stylesheet" media="all" type="text/css" href="<%=path%>/css/buddhism_page.css">
        <script type='text/javascript' src='<%=path%>/js/dropdown.js'></script>
        <script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
<!--        <script type='text/javascript' src='<%=path%>/js/picture_show.js'></script>-->
        <script type='text/javascript' src='<%=path%>/js/jqFancyTransitions.1.8.min.js'></script>	
<!--        <link rel="stylesheet" href="../themes/default/default.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="../themes/light/light.css" type="text/css" media="screen" />-->  
    <script type="text/javascript" src="<%=path%>/coin-slider/coin-slider.min.js"></script>
    <link rel="stylesheet" href="<%=path%>/coin-slider/coin-slider-styles.css" type="text/css" />
    <link rel="stylesheet" href="<%=path%>/orbit/orbit-1.2.3.css">
		<script type="text/javascript" src="<%=path%>/orbit/jquery.orbit-1.2.3.min.js"></script>	
<!--    <link rel="stylesheet" href="style.css" type="text/css" media="screen" />-->
        <title>果硕普利协会</title>

        <script type="text/javascript">

            //        function loadXMLDoc()
            //        {
            //        var xmlhttp;
            //        if (window.XMLHttpRequest)
            //        {// code for IE7+, Firefox, Chrome, Opera, Safari
            //        xmlhttp=new XMLHttpRequest();
            //        }
            //        else
            //        {// code for IE6, IE5
            //        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            //        }
            //        xmlhttp.onreadystatechange=function()
            //        {
            //        if (xmlhttp.readyState==4 && xmlhttp.status==200)
            //            {
            //            document.getElementById("article-items").innerHTML=xmlhttp.responseText;
            //            }
            //        }
            //        xmlhttp.open("POST","mainPageArticle.action",true);
            //        xmlhttp.send();
            //        }
            function loadArticles()
            {
                $("#viewmore").html("加载中...");
                $.ajax({
                    url: 'moreArticle.action',
                    success: function(data) {
                        $("#addflag").before(data);   
                  
                        $("#viewmore").html("瀏覽更多資訊");
                    },
                    error:function(){
                        $("#viewmore").html("没有更多資訊"); 
                    }
                });
            }
            
            $(function(){
                $("#viewmore").click(function(){
                    loadArticles();
                })
              
                $.post('mainPageArticle.action',function(data){
                    $("#addflag").before(data); 
                })
                $('#slider').orbit({
                    animation: 'fade'
                });
//                  $('#coin-slider').coinslider({ width: 565, height:335,delay: 5000 ,navigation:true});

//                $('#slider').jqFancyTransitions({ width: 565, height: 330,delay: 2000 });
            });
           
            

        </script>
        
    </head>
    <body>
        <div class="wrapper">
            <%@ include file="/jsp/banner_nav.jsp" %>
              
            <div class="content">
                <div class="primary">
                    <div id="pictureswall_bk">
<!--                        <div id="pictures_bg" >
                            <div id="pictures_info"></div> 标题
                            <ul>
                                <li class="on">1</li>
                                <li>2</li>
                                <li>3</li>
                                <li>4</li>
                            </ul>

                        </div>  标题背景-->

                        <div id="pictureswall">	
                            
                            <div id='slider'>



<!--                            <div id="pictures">-->
<!--                                <ul>-->
                                    
                                    <%!
                                      int i =0;
                                    %>
                                    <s:iterator id="imgPath" value="postImages">
                                        <img src="<s:property value="imgPath"/>"  alt="and more features"  data-caption="#<%=i%>" />
                                        <span class="orbit-caption" id="<%=i%>"><strong>path:<s:property value="imgPath"/></strong></span>
                                        <%
                                            i++;
                                        %>
                                    </s:iterator>
                            </div>
<!--                                </ul>-->
</div>
                            
                        
                    </div>	  	
                    <div class="three">
                        <div class="two">
                            <div class="left">
                                <div id="left_title">甘露教言</div>
                                <s:iterator value="buddleWords" id="buddleWord">
                                    <a href="articlePage?id=${id}&&parentType=4&&index=-1" class="bold f12 cr1" title="${postTitle}">
                                        <s:if test="postTitle.length() > 12">
                                                    <s:property value="postTitle.substring(0, 14)" />...
                                                    </s:if>
                                                    <s:else>
                                                    <s:property value="postTitle" />
                                                    </s:else>
                                        </a>
                                        <br/>
                                </s:iterator>
                                       
                            </div>
                            <div class="right">
                                <div id="left_title">正法蘭若</div>
                                       <s:iterator value="temples" id="temples">
                                           <a href="articlePage?id=${id}&&parentType=16&&index=-1" class="bold f12 cr1" title="${postTitle}">
                                               
                                                    <s:if test="postTitle.length() > 12">
                                                    <s:property value="postTitle.substring(0, 14)" />...
                                                    </s:if>
                                                    <s:else>
                                                    <s:property value="postTitle" />
                                                    </s:else>
                                           </a><br/>
                                       </s:iterator>
                                </div>
                            </div>
                            <div class="last">
                                <div id="left_title">清涼共享</div>
                                <s:iterator value="shares" id="shares">
                                    <a href="articlePage?id=${id}&&parentType=14&&index=-1" class="bold f12 cr1" title="${postTitle}">
                                    <s:if test="postTitle.length() > 12">
                                                    <s:property value="postTitle.substring(0, 14)" />...
                                                    </s:if>
                                                    <s:else>
                                                    <s:property value="postTitle" />
                                                    </s:else>
                                    </a><br/>
                                </s:iterator>
                            </div>
                        </div>
                        <div class="article-items" id="article-items">
                            <div id="addflag"></div>
                        </div>
                        <div id="viewmore" style="cursor:pointer;">瀏覽更多資訊</div>
                    </div>
                    <%@include file="/jsp/right_nav.jsp" %>



                </div>

                <%@include  file="/jsp/footer.jsp" %>
            </div>
            
        </body>
    </html>

