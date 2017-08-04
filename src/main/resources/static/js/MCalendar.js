(function($){
	  $.hasClass = function(obj, cls) {
            return obj.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));
        }

        $.addClass = function(obj, cls) {
            if (!$.hasClass(obj, cls)) obj.className += " " + cls;
        }

        $.removeClass =function(obj, cls) {
            if ($.hasClass(obj, cls)) {
                var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
                obj.className = obj.className.replace(reg, ' ');
            }
        }
  
    $.DateUtil={};
    $.DateUtil.addDate = function(date, inc){
				var d = new Date(date);
				d.setTime(date.getTime() + inc*24*3600*1000);
				return d;
		}
	$.DateUtil.getDateDiff= function(base, target){
			return (target.getTime() - base.getTime())/(24*3600*1000);
		}
	$.DateUtil.getToday= function(){
			var d = new Date();
			d.setHours(0,0,0,0);
			return d;
		}
	
}(mui));


(function($){
	
	var MonthView =(function($){
		var MonthViewTemplate = '' +
	   '<div class="mc-toolbar">'+

			'<span id="mc-date-label" class="mui-pull-left">date</span>'+
		'</div>'+
		'<div><table class="mc-table">'+
			'<tr class="">'+
				'<th>周日</th><th>周一</th><th>周二</th><th>周三</th><th>周四</th><th>周五</th><th>周六</th>'+
			'</tr>'+
		'</table></div>' +
		'<div>'+
			'<table id = "mc-table-body" class="mc-table"></table>'+
	    '</div>';
	
		var CellViewTemplate = '<td class="mc-table-cell"><a>1</a></td>';
		var cell_selected;
		var date_selected;
		var firstDateinMonthView;
		
		var renderSkelekon = function(container){
				var div = document.createElement("div");
				div.className = "mc-container"
				div.innerHTML = MonthViewTemplate;
				container.appendChild(div);

				var html ="";
				for (var i=0; i<1; i++){
					html += '<tr class="mc-table-row">';
					for(var j=0; j<7; j++){
						html += CellViewTemplate;
					}
					html += '</tr>';
				}
				$("#mc-table-body")[0].innerHTML = html;
				
				var i =0;
				$(".mc-table-cell").each(function(){
					this.setAttribute("mc-cell-index",i++);
				});
		};
		var changeMonth= function(date){
				var firstDate = getFirstDateInMonth(date);
				firstDateinMonthView = firstDate;
				var i=0
				$(".mc-table-cell").each(function(){
					var d =  $.DateUtil.addDate(firstDate, i++);
					this.firstChild.innerHTML = ""+d.getDate();
					if(d.getMonth() != date.getMonth()){
						this.setAttribute("disabled","disabled");
					}
					else{
						this.removeAttribute("disabled");
					}
					if(d.getTime() == date.getTime()){
						$.addClass(this,"mc-cell-selected");
						cell_selected = this;
						date_selected = d;
					}
					
					if(d.getTime() == $.DateUtil.getToday().getTime()){
						$.addClass(this, "mc-table-cell-today")
					}
					else{
						$.removeClass(this, "mc-table-cell-today")
					}
				});

		};
		var changeDate = function(date){
			  date && date.setHours(0,0,0,0)
				if(cell_selected){
					$.removeClass(cell_selected,"mc-cell-selected");
					if(date_selected.getFullYear() == date.getFullYear()
					   && date_selected.getMonth() == date.getMonth()){
					   	  var index = 1*cell_selected.getAttribute("mc-cell-index") + $.DateUtil.getDateDiff(date_selected, date);
					   	  cell_selected = $(".mc-table-cell")[index];
					   	  $.addClass(cell_selected,"mc-cell-selected");
					   	  date_selected = date;
					   }
					else{
						changeMonth(date);
					}
				}
				else{
					changeMonth(date);
				}
				
				$("#mc-date-label")[0].innerHTML = (date_selected.getMonth()+1) +'月' +date_selected.getFullYear() +'年';
				
			}
		function getFirstDateInMonth(date){
			var d = new Date(date);
			d.setDate(1)
			var fd = $.DateUtil.addDate(d, (0-d.getDay()));
			return fd;
		}
			
		return{
			date_selected: function(){return date_selected},
			init: function(o,c){
				options = o
				renderSkelekon(o.container);
				
				this.changeDate(o.date || $.DateUtil.getToday());
				
				$("#mc-table-body").on('tap','.mc-table-cell',function(){
					var idx = this.getAttribute("mc-cell-index");
					changeDate($.DateUtil.addDate(firstDateinMonthView,idx))
						
				});
				var swipeMonth = function(direction){
					var d = new Date(date_selected);
					var m = d.getMonth() + direction;
					if(m==12){
						d.setMonth(0);
						d.setFullYear(d.getFullYear() + 1);
					}
					else if(m==-1){
						d.setMonth(11);
						d.setFullYear(d.getFullYear() - 1);
					}
					else{
						d.setMonth(m) ;
					}
					changeDate(d);
				}
				o.container.addEventListener('swipeleft', function(){
					swipeMonth(1);
				})
				o.container.addEventListener('swiperight', function(){
					swipeMonth(-1);
				})
			},
			
			changeDate:changeDate,
		}
	}($));
	
	
	$.fn.MCalendar = function(option){
		var options = {
			container : this[0],
			row_len:6,
			date:undefined,
		}

		$.extend(options, option||{});
		options.date && options.date.setHours(0,0,0,0);
		
		
		var mc ={
			options:{},
			getDate: function(){
				return MonthView.date_selected();
			},
			init:function(){
				var el;
				this.options = options;
				MonthView.init(options);

			},
			
			show:function(){
				options.container.style.display = "initial";
				
			},
			hide:function(){
				options.container.style.display = 'none';
			},
			
			changeDate:function(date){
				MonthView.changeDate(date)
			},
			
		};

		
		mc.init();
		return mc;
	};
	
	
}(mui));
