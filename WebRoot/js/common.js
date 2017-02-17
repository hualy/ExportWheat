jQuery(document).ready(function($) {
	var currenturl = window.location.href;
	var aurl=$('#navRight li a');
	for (var i = 0; i < aurl.length; i++) {
	    var url = aurl.eq(i).prop('href'); /*获取链接的href值*/
	    if (currenturl.indexOf(url) != -1) { /*如果链接的href值在当前页面地址中有匹配*/
	        aurl.eq(i).removeProp('color').addClass('hovernav');
	    }
	}


});

var ChangeSelect=function(obj){
	// var citySelect = document.getElementById('citySelect');
	// alert(citySelect);
	// var citySelect = obj.nextSibling.id;
	// alert(citySelect);
	// 返回一个集合
	// var citySelect = document.getElementsByClassName("citySelect")[0];
	// var citySelect = obj.parentNode.lastChild.previousSibling;
	var citySelect = obj.parentNode.children[2];
	// alert(citySelect);
	change(obj,citySelect);
};
var ChangeSelect1=function(obj){
	// var citySelect = document.getElementById('citySelect');
	// alert(citySelect);
	// var citySelect = obj.nextSibling.id;
	// alert(citySelect);
	// 返回一个集合
	// var citySelect = document.getElementsByClassName("citySelect")[0];
	var citySelect = obj.parentNode.children[1];
	// alert(citySelect);
	change(obj,citySelect);
}
function change(obj,citySelect){
		var n = obj.selectedIndex; //获取第一个列表中选中的项的序列
		if(n==0){
		citySelect.length = 0;
		var option0 = new Option("毫州",0);
		var option1 = new Option("砀山",1);
		var option2 = new Option("阜阳",2);
		var option3 = new Option("蚌埠",3);
		citySelect.options.add(option0);
		citySelect.options.add(option1);
		citySelect.options.add(option2);
		citySelect.options.add(option3);
	}
	if(n==1){
		citySelect.length = 0;
		var option0 = new Option("唐山",0);
		var option1 = new Option("乐亭",1);
		var option2 = new Option("饶阳",2);
		var option3 = new Option("黄骅",3);
		citySelect.options.add(option0);
		citySelect.options.add(option1);
		citySelect.options.add(option2);
		citySelect.options.add(option3);
	}
	if(n==2){
		citySelect.length = 0;
		var option0 = new Option("新乡",0);
		var option1 = new Option("许昌",1);
		var option2 = new Option("驻马店",2);
		var option3 = new Option("尚丘",3);
		citySelect.options.add(option0);
		citySelect.options.add(option1);
		citySelect.options.add(option2);
		citySelect.options.add(option3);
	}
	if(n==3){
		citySelect.length = 0;
		var option0 = new Option("徐州",0);
		var option1 = new Option("盱眙",1);
		citySelect.options.add(option0);
		citySelect.options.add(option1);
	}
	if(n==4){
		citySelect.length = 0;
		var option0 = new Option("朝阳",0);
		var option1 = new Option("兖州",1);
		var option2 = new Option("日照",2);
		citySelect.options.add(option0);
		citySelect.options.add(option1);
		citySelect.options.add(option2);
	}
	if(n==5){
		citySelect.length = 0;
		var option0 = new Option("塘沽",0);
		citySelect.options.add(option0);
	}
}