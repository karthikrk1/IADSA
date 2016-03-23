
/** Timer class for roughly calculating running time of programs
 *  @author rbk
 *  Usage:  Timer timer = new Timer();
 *          timer.start();
 *          timer.end();
 *          System.out.println(timer);  // output statistics
 */

public class G05_Timer {
    long startTime, endTime, elapsedTime, memAvailable, memUsed;

    G05_Timer() {
	startTime = System.currentTimeMillis();
    }

    public void start() {
	startTime = System.currentTimeMillis();
    }

    public G05_Timer end() {
	endTime = System.currentTimeMillis();
	elapsedTime = endTime-startTime;
	memAvailable = Runtime.getRuntime().totalMemory();
	memUsed = memAvailable - Runtime.getRuntime().freeMemory();
	return this;
    }

    public String toString() {
        return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1000000) + " MB / " + (memAvailable/1000000) + " MB.";
    }

}
