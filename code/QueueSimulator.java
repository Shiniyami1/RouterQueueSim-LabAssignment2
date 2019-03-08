import java.lang.*;

public class QueueSimulator{
  public enum Event { ARRIVAL, DEPARTURE };
  private double currTime;
  private double arrivalRate;
  private double serviceTime;
  private double timeForNextArrival;
  private double timeForNextDeparture;
  private double totalSimTime;
  LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
  LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();
  private Event e;

  public double getRandTime(double arrivalRate){
    double num, time1, max=1, min=0, randNUM;
    randNUM= Math.random();
    time1= (-1/arrivalRate) * (Math.log(1-randNUM));
    //System.out.println(time1);
    return time1;
  }

  public QueueSimulator(double aR, double servT, double simT){
    arrivalRate = aR;
    serviceTime = servT;
    totalSimTime = simT;
    timeForNextArrival = getRandTime(arrivalRate);
    timeForNextDeparture = serviceTime + timeForNextArrival;
  }

  public double calcAverageWaitingTime(){
    double totalWaitTime = 0;
    int count = 0;
    while(!eventQueue.isEmpty()){
      Data node = eventQueue.dequeue();
      totalWaitTime += node.getDepartureTime() - node.getArrivalTime();
      count++;
    }
    double averageWaitTime = totalWaitTime/count;
    return averageWaitTime;
  }

  public double runSimulation() {
   
    while (currTime < totalSimTime) {
        if (timeForNextDeparture > timeForNextArrival || buffer.isEmpty()) {
            e = Event.ARRIVAL;
        } 
        else {
            e = Event.DEPARTURE;
        }
      
        if (e == Event.DEPARTURE) { 
            currTime = currTime + timeForNextDeparture;
            Data node = buffer.dequeue();
            node.setDepartureTime(currTime);
            eventQueue.enqueue(node);
            timeForNextArrival -= timeForNextDeparture;
            
            if (buffer.isEmpty()) {
                timeForNextDeparture = timeForNextArrival + serviceTime;
            }
            else {
                timeForNextDeparture = serviceTime;
            }
        }
        else if (e == Event.ARRIVAL) {
            currTime = currTime + timeForNextArrival;
            Data node = new Data();
            node.setArrivalTime(currTime);
            buffer.enqueue(node);
            timeForNextDeparture -= timeForNextArrival;
            timeForNextArrival = getRandTime(arrivalRate);
        }

    }
    return calcAverageWaitingTime();
  }
}
