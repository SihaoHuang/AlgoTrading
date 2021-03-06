# Advanced Machine Learning Trading System
<h3>Full implementation of a backpropagating neural network and financial datafeed without the use of any preexisting libraries.
This is a project for Stuyvesant's AP Computer Science class, taught by Mr. Konstantinovich. Made by LongXShortY.</h3>

<b>Brief introduction to the project:</b><br>
The neural network, which forms the core of this project, consists of then Neuron class and the NeuralNetwork class. The network is formed by a known number of inputs and outputs, together with a grid of interconnected neurons with a specified depth and width. The weights and are initialized and stored in arrays, where they are subsequently adjusted through backpropagation during training. <br> <br> 
Fundemental data is obtained from YahooFinance through a web query. Volume and price time series are retrieved from a csv file on the Yahoo server which is updated periodically, and they are passed through a gaussian normalization function before being concatenated with fundemental data to form the input set to the network. As the backpropagating network itself is not recurrent (i.e. not time-dependent), pseudo time-dependence is allowed through a moving window which slides across the live datafeed.<br><br> 
The network is trained with the processed input set to a output value, and is repeated 503 times for every stock in the S&P500 index to allow it to generalize price movement characteristics. This whole process forms a single iteration, which is then repeated a specified number of times to decrease the cost function. This needs to be tweaked so that overfitting does not occur. <br> <br> 

<b>Instructions:</b> <br>
Compile and run Terminal.java. Instructions will be presented. This allows access of equity mode (financial datafeed and neural network), handwriting recognition mode, and GUI mode. Note that training the network will take a very long period of time, typically ranging from 1 hour to 10 hours. <b>A good network configuration to test with would be 200 wide by 1 deep by 100 iterations (20-30 minute runtime), though thousands of iterations are needed to produce good results.</b><br><br>

<b>How well does it work? Are the goals achieved?</b> <br>
We were able to create a functional, backpropagating neural network from scratch. Its functionality is proven by the handwriting recognition test, which achieves up to 90% accuracy. On the financial backend side of the story we were able to process the time series into a usable set of inputs and pull data from Yahoo from scrach. When they are brought together the network is shown to effectively reduce the cost function despite overfitting. Even though the end result was not particularly valuable to trading, we have achieved our goals in creating this working project. <br>
Future implementations which use convolutional or recurrent networks and more optimized configurations might have a hope of bringing this to the next level.<br>
*Note, the GUI was abandoned as it was just not worth the time, though some features such as displaying fundementals work.

<b>Known bug list:</b> <br> 
-Terminal.java does not catch any wrong input formats or invalid user inputs (such as invalid tickers), so it would exit the program
-A rather careless bug: training works much worse (or does not work at all) on days when market is not open as the output was derived using currentPrice-lastClosingPrice, instead of previous data point
-Not a bug, but note that exit does not work when a command is running <br><br>

<b>Resources:</b><br>
https://www.tensorflow.org/ - Powerful open source machine learning/numerical computation library with large user base and many resources <br>
https://www.mathworks.com/products/neural-network.html - Many machine learning textbooks use the Matlab neural network toolbox. It also has good integration with datafeeds and the financial toolbox. <br> 
