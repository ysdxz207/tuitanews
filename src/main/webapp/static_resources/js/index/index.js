var pageSize = 20;
var pageOffset = 0;

/*
mui.init({
	swipeBack:true, // 启用右滑关闭功能
	gestureConfig:{
		doubletap:true
	},
	pullRefresh : {
	    container:"#refresh_container1",// 下拉刷新容器标识，querySelector能定位的css选择器均可，比如：id、.class等
	    id:'',//内容页面标志
	    styles:{
	        top:'48px',//内容页面顶部位置,需根据实际页面布局计算，若使用标准mui导航，顶部默认为48px；
	    },
	    down : {
	      height:50,// 可选,默认50.触发下拉刷新拖动距离,
	      auto: false,// 可选,默认false.自动下拉刷新一次
	      contentdown : "下拉刷新(๑•ᴗ•๑)",// 可选，在下拉可刷新状态时，下拉刷新控件上显示的标题内容
	      contentover : "快松开！我要刷新了！o(*￣▽￣*)o",// 可选，在释放可刷新状态时，下拉刷新控件上显示的标题内容
	      contentrefresh : "刷新ing...♪(＾∀＾●)ﾉ",// 可选，正在刷新状态时，下拉刷新控件上显示的标题内容
	      callback : function(){// 必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
	    	  loadNewsData(this, "down", 1);
	    	  //注意，加载完新数据后，必须执行如下代码，注意：若为ajax请求，则需将如下代码放置在处理完ajax响应数据之后
	    	  //mui('#refresh_container').pullRefresh().endPulldownToRefresh();
	      }
	    },
	    up: {
	    	height:800,// 可选,默认50.触发下拉刷新拖动距离,
			contentrefresh: '努力加载...',
			contentnomore:'已经到底啦！(＞人＜;)',//可选，请求完毕若没有更多数据时显示的提醒内容；
			callback: function(){
				//1、加载完新数据后，必须执行如下代码，true表示没有更多数据了：
				//2、若为ajax请求，则需将如下代码放置在处理完ajax响应数据之后
				loadNewsData(this, "up", 1);
			}
		}
	  }
	});
*/

mui.init();

function loadNewsData(obj, type, newsChannelId){
	console.info(pageOffset);
	var params = {};
	params.pageOffset = pageOffset;
	params.pageSize = pageSize;
	params.newsChannelId = newsChannelId;
	mui.ajax('index.ajax',{
		data: params,
		dataType:'json',//服务器返回json格式数据
		type:'get',//HTTP请求类型
		timeout:10000,//超时时间设置为10秒；
		//headers:{'Content-Type':'application/json'},	              
		success:function(data){
			console.log(data);
			var flag = true;//没有更多数据了
			if (data.newsList.length > 0){
				var html = template('template' + newsChannelId, data);
				if (type == "up"){
					document.getElementById('template_contener' + newsChannelId).innerHTML += html;
				} else {
					document.getElementById('template_contener' + newsChannelId).innerHTML = html;
				}
				flag = false;
			}
			if (obj){
				if (type == "up"){
					//pageOffset += pageSize;
					obj.endPullUpToRefresh(flag);
				} else if (type == "down"){
					obj.endPullDownToRefresh(flag);
					
				}
			}
		},
		error:function(xhr,type,errorThrown){
			//异常处理；
			console.error("ajax error type =" + type);
		}
	});
};

document.querySelector('.mui-slider').addEventListener('slide',
		function(event) {
			// 注意slideNumber是从0开始的；
			console.info("你正在看第" + (event.detail.slideNumber + 1) + "个选项卡");
			loadNewsData(null, null, event.detail.slideNumber + 1);
		});



(function($) {
	//阻尼系数
	var deceleration = mui.os.ios?0.003:0.0009;
	$('.mui-scroll-wrapper').scroll({
		bounce: false,
		indicators: true, //是否显示滚动条
		deceleration:deceleration
	});
	$.ready(function() {
		//循环初始化所有下拉刷新，上拉加载。
		$.each(document.querySelectorAll('.mui-slider-group .mui-scroll'), function(index, pullRefreshEl) {
			$(pullRefreshEl).pullToRefresh({
				down: {
					tips: {
						colors: ['ea2000']
					},
					callback: function() {
						loadNewsData(this, "down", index + 1);
					}
				},
				up: {
					contentinit: '',
					contentrefresh: '努力加载...',
					contentnomore:'已经到底啦！(＞人＜;)',//可选，请求完毕若没有更多数据时显示的提醒内容；
					callback: function() {
						pageOffset += pageSize;
						loadNewsData(this, "up", index + 1);
					}
				}
			});
		});
		
	});
})(mui);


loadNewsData(null, null, 1);