import financialdata.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Driver{

	static ArrayList<double[]> masterTraining = new ArrayList<double[]>(); // use this to store all data offline first!
	static ArrayList<Double> masterTarget = new ArrayList<Double>();

  public static double[] createInputs(String ticker){
    ArrayList<Double> out = new ArrayList<Double>();
    out.addAll(Datafeed.getFundementals(ticker));
		out.addAll(Convolutions.gaussianNormalization(Datafeed.getPriceSeries(ticker)));
		out.addAll(Convolutions.gaussianNormalization(Datafeed.getVolumeSeries(ticker)));
		return typeCastDouble(out.toArray(new Double[out.size()]));
  }

	public static void writeMasterData(){
		int tickerCount = 0;
		while (tickerCount < Datafeed.getTickerList().size()){ 
			masterTraining.add(createInputs(Datafeed.getTickerList().get(tickerCount)));
			masterTarget.add(Datafeed.getNewestPrice(Datafeed.getTickerList().get(tickerCount))/10000);
			tickerCount ++;
		}	
	}

	public static void feedAll(int width,int depth,int out,int in){
		NeuralNetwork a = new NeuralNetwork();
		a.initializeNet(80,3,1,206); 
		int iters = 100;
		int displayDivisor = 1;
		double cost = 0;
		for (int i = 0; i < iters; i++){
			int tickerCount = 0;
			while (tickerCount < Datafeed.getTickerList().size()){ //goes through an trains on all stocks in the S&P500 inex
				a.feedData(masterTraining.get(tickerCount),masterTarget.get(tickerCount));
				cost += Math.abs(Double.parseDouble(a.toStringOutLast()) - masterTarget.get(tickerCount));
				if (i % displayDivisor == 0){
					System.out.println("Output is: " + a.toStringOutLast());
					System.out.println("Target is: " + masterTarget.get(tickerCounnt));
				}
				tickerCount ++;
			}
			System.out.println(i+"th iteration");
			System.out.println("Cost: " + cost);
			System.out.println("--");
			cost = 0;
		}
	}

	public static double[] typeCastDouble(Double[] in){
		double[] out = new double[in.length];
		int i = 0;
		while(i<in.length){
			out[i] = in[i];
			i++;
		}
		return out;
	}
	
	public static void main(String[] args){

		String ticker = args[0];

		if(ticker.equals("train")){
			Datafeed.loadStocks(); 
			writeMasterData();
			feedAll(80,2,1,206);
		}
		
		else{
			Datafeed.loadStocks(); //remember to load stocks whenever Datafeed is used!

			System.out.println(Datafeed.nameFromTicker(ticker));
			System.out.println(Datafeed.sectorFromTicker(ticker));
			Datafeed.printFundementals(ticker);  
		}
  }

}
