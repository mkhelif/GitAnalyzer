
function createTimeChart(element, series) {
	new Highcharts.StockChart({
		chart: {
			renderTo: element,
			type: 'area'
		},
		
		// Axis
		xAxis: {
            type: 'datetime'
        },
        yAxis: {
        	min: 0
        },
        
        // Series
		series: series,
		
		// Navigator
		navigator: {
			maskFill: 'rgba(255, 255, 255, 0.75)'
		},
		
		// Scrollbar
        scrollbar: {
        	// Bar
            barBackgroundColor: '#EEE',
            barBorderRadius: 7,
            barBorderWidth: 1,
            barBorderColor: '#AAA',
            
            // Buttons
            buttonBackgroundColor: '#EEE',
            buttonBorderRadius: 7,
            buttonBorderWidth: 1,
            buttonBorderColor: '#AAA',
            
            // Track
            trackBackgroundColor: '#FEFEFE',
            trackBorderWidth: 1,
            trackBorderRadius: 7,
            trackBorderColor: '#DDD'
        },
        
        // Settings
        rangeSelector: {
        	enabled: false
        }
	});
}