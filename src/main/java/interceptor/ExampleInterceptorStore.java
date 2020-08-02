package interceptor;

public class ExampleInterceptorStore {
  private int pduCount;

  public ExampleInterceptorStore() {
    pduCount = 0;
  }

  public int getPduCount() {
    return pduCount;
  }

  public int increaseAndGetCount() {
    return ++pduCount;
  }

  public void increaseCount() {
    ++pduCount;
  }
}
