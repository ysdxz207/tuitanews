<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>推荐</title>
<#include "/nav.ftl"/>
<link rel="stylesheet" href="lib/mui/css/mui.min.css">
<link rel="stylesheet" href="css/index/app_mui.css">
</head>
<body>
	
	<header class="mui-bar mui-bar-nav">
		<a class="mui-action-back mui-icon mui-pull-left"></a>
		<h1 class="mui-title">推他新闻</h1>
	</header>
	<#include "/tab.ftl"/>
	
	
	<script src="lib/mui/js/mui.min.js"></script>
	<script src="lib/mui/js/mui.pullToRefresh.js"></script>
	<script src="lib/mui/js/mui.pullToRefresh.material.js"></script>
	<script src="lib/artTemplate/template.js"></script>
	<script src="js/index/index.js"></script>
	<script type="text/html" id="template1">
	{{each newsList as news i}}
	<li class="mui-table-view-cell mui-media">
		<a href="javascript:;" class="mui-navigate-right">
			<div class="mui-media-body">
				<div class="mui-table-cell mui-col-xs-10">
					<img class="mui-media-object mui-pull-left" src="{{news.faceUrl}}">
                    <h4 class="mui-ellipsis">{{news.title}}</h4>
                    <h5>来源：{{news.source}}</h5>
                    <p class="mui-h6 mui-pull-left">{{news.pubDate}}</p>
                </div>
            </div>
		</a>
	</li>
	{{/each}}
	</script>
	<script type="text/html" id="template2">
	{{each newsList as news i}}
	<li class="mui-table-view-cell mui-media">
		<a href="javascript:;" class="mui-navigate-right">
			<div class="mui-media-body">
				<div class="mui-table-cell mui-col-xs-10">
					<img class="mui-media-object mui-pull-left" src="{{news.faceUrl}}">
                    <h4 class="mui-ellipsis">{{news.title}}</h4>
                    <h5>来源：{{news.source}}</h5>
                    <p class="mui-h6 mui-pull-left">{{news.pubDate}}</p>
                </div>
            </div>
		</a>
	</li>
	{{/each}}
	</script>
	<script type="text/html" id="template3">
	{{each newsList as news i}}
	<li class="mui-table-view-cell mui-media">
		<a href="javascript:;" class="mui-navigate-right">
			<div class="mui-media-body">
				<div class="mui-table-cell mui-col-xs-10">
					<img class="mui-media-object mui-pull-left" src="{{news.faceUrl}}">
                    <h4 class="mui-ellipsis">{{news.title}}</h4>
                    <h5>来源：{{news.source}}</h5>
                    <p class="mui-h6 mui-pull-left">{{news.pubDate}}</p>
                </div>
            </div>
		</a>
	</li>
	{{/each}}
	</script>
	<script type="text/html" id="template4">
	{{each newsList as news i}}
	<li class="mui-table-view-cell mui-media">
		<a href="javascript:;" class="mui-navigate-right">
			<div class="mui-media-body">
				<div class="mui-table-cell mui-col-xs-10">
					<img class="mui-media-object mui-pull-left" src="{{news.faceUrl}}">
                    <h4 class="mui-ellipsis">{{news.title}}</h4>
                    <h5>来源：{{news.source}}</h5>
                    <p class="mui-h6 mui-pull-left">{{news.pubDate}}</p>
                </div>
            </div>
		</a>
	</li>
	{{/each}}
	</script>
</body>
</html>