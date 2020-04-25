/**
 * The Power controller holds the state of power consumption.
 * It creates its view in render().
 */
class Power extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			powerData: [],
		};
	}

	componentDidMount() {
		this.getPowerData();
		setInterval(this.getPowerData, 1000);
	}

	render() {
		var powerData = this.state.powerData;

		if (powerData.length == 0)
			return <div>There is no data.</div>;
		
		var last = powerData[powerData.length-1];
		return (<div>
			Last power reading: {last.power} Watts, {last.date} {last.time}
		</div>);
	}

	getPowerData = () => {
		// for simplicity, we generate some fake data here
		var now = new Date();
		var date = now.toLocaleDateString();
		var time = now.toLocaleTimeString();

		var powerData = [{date: date, time: time, power: 100}];
		
		if (this.state.powerData.length != 0) {
			powerData = this.state.powerData.slice(0);
			var last = powerData[powerData.length-1].power;
			var delta = Math.floor(Math.random()*21)-10; // [-10, 10]
			powerData.push({date: date, time: time, power: last+delta});
		}

		// discard old data
		if (powerData.length > 20)
			powerData = powerData.slice(powerData.length-20);

		// update state
		this.props.display.setData(powerData);
		this.setState({powerData: powerData});
	}
}

// export
window.Power = Power;
