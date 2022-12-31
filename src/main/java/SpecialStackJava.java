import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
import lombok.Data;

@Data
public class SpecialStackJava<T extends Comparable> {

  private final Deque<T> mainStack;
  private final Deque<T> minStack;

  public SpecialStackJava() {
      this(new ArrayDeque<>(), new ArrayDeque<>());
  }

  public SpecialStackJava(final Deque<T> mainStack, final Deque<T> minStack) {
    this.mainStack = mainStack;
    this.minStack = minStack;
  }

  public void push(T element) {

    mainStack.push(element);

    T currentMin;
    if (!minStack.isEmpty()) {
      currentMin = minStack.pop();
      minStack.push(currentMin);
    } else {
      currentMin = element;
    }

    T newMin = element.compareTo(currentMin) > 0 ? currentMin : element;

    minStack.push(newMin);
  }

  public Optional<T> pop() {
    if (mainStack.isEmpty() || minStack.isEmpty()) {
      return Optional.empty();
    }

    minStack.pop();
    return Optional.ofNullable(mainStack.pop());
  }

  public Optional<T> getMin() {
    return Optional.ofNullable(minStack.peek());
  }

  public static void main(String[] args){
    var stack = new SpecialStackJava<Integer>();
    var size = 10;

    new Random().ints(size, 0, 100).forEach(stack::push);

    System.out.println(stack);
    IntStream.range(0, 10).forEach(i -> {
        System.out.println("Min: " + stack.getMin());
        System.out.println("Value: " + stack.pop());
        System.out.println(stack);
    });
  }
}
