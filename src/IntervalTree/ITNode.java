package IntervalTree;

public class ITNode {

  Interval interval;
  int maxValue;

  ITNode left;
  ITNode right;

  public ITNode(Interval interval, int maxValue) {
    this.interval = interval;
    this.maxValue = maxValue;
  }

  @Override
  public String toString() {
    return interval + " " + maxValue;
  }
}
