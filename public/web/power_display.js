/**
 * Control another MVC system that is not part of React.
 */
window.createPowerDisplay = domId => {

	// chart as the view
	var chart = echarts.init(document.getElementById(domId));

	// the controller
	var that = {};

	// notify the controller of model updates
	that.setData = data => {
		var option = {
			xAxis: {
				type: 'category',
				data: data.map(d => d.time)
			},
			
			yAxis: {
				name: 'Power (W)',
				type: 'value'
			},			
			
			series: [{
				name: 'PlugA',
				type: 'line',
				data: data.map(d => d.power)
			}]
		};

		// render the chart
		chart.setOption(option);
	};

	return that;
}
