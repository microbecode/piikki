package piikki;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

public class Analysis {

	protected TimeSeriesCollection chartDataSet = new TimeSeriesCollection();
	protected XYSeriesCollection chartXYDataSet = new XYSeriesCollection();
	protected ArrayList <String> chartPlotType = new ArrayList<String>(); // @jve:decl-index=0:
	protected String chartTitle = "Hey";
	protected String chartYAxisTitle = "Wow";
	protected String chartXAxisTitle = "NoWay";
	
	private int totalSells = 0;

	protected ChartPanel getChart(String[] products) {
		
		XYDataset set = getDataset(products);
		ChartPanel chartPanel = new ChartPanel(createChart(set));
		
		Dimension d = new Dimension();
		d.height = 300;
		d.width = 500;
		chartPanel.setMinimumSize(d);
		chartPanel.setBorder(BorderFactory.createTitledBorder(new
				SoftBevelBorder(SoftBevelBorder.RAISED), "Päivittäiset ryyppäykset",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, null));
		chartPanel.setMouseZoomable(true, false);
		if (products[0] != "") {
			chartPanel.getChart().getXYPlot().getRangeAxis().setLabel("Kpl");
		
		}
		
		return chartPanel;
	}

	public JFreeChart createChart(XYDataset data) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				   "",
				   "",
				   "Summa",
				   data,
				   true,
				   true,
				   false);
		return chart;
	}




	private XYDataset getDataset(String[] products) {
		TimeSeriesCollection col = new TimeSeriesCollection();
		for (String prod : products) {
			List<StatsData> stats = GUIWrapper.getStats(prod);

			TimeSeries series = new TimeSeries((prod == "" ? new String("Yhteensä") : prod.replace('%', ' ')));
			for (StatsData data : stats) {
				Day day = new Day(data.getDate());
				series.add(day, data.getPrice());
				if (prod == "") totalSells += data.getPrice();
			}
			col.addSeries(series);
		}
		return col;

	}

	public int getTotalSells() {
	return totalSells;	
	
	}

	public class StatsData {

		private Date date;
		
		private String name;

		private double price;

		private String product;

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getProduct() {
			return product;
		}

		public void setProduct(String product) {
			this.product = product;
		}
	}
}
