package IntervalTree;

public class Interval {
  int low;
  int high;

  public Interval(int low, int high) {
    this.low = low;
    this.high = high;
  }

  @Override
  public String toString() {
    return String.format("[%d, %d]", low, high);
  }
}
