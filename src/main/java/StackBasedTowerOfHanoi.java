import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StackBasedTowerOfHanoi {

  Map<StackType, Deque<Integer>> stacks;

  public StackBasedTowerOfHanoi(Map<StackType, Deque<Integer>> stacks) {
    this.stacks = stacks;
  }

  private static Map<StackType, Deque<Integer>> initStacks(int size) {
    var stacks = createStacks(size);
    var sourceStack = stacks.get(StackType.SRC);

    IntStream.rangeClosed(1, size).forEach(sourceStack::addLast);

    return stacks;
  }

  private static Map<StackType, Deque<Integer>> createStacks(int maxSize) {
    return EnumSet.allOf(StackType.class).stream().collect(Collectors.toMap(Function.identity(), t -> new ArrayDeque<>(maxSize)));
  }

  public static void main(String[] args) {
    var size = 4;
    var towerOfHanoi = new StackBasedTowerOfHanoi(StackBasedTowerOfHanoi.initStacks(size));

    System.out.println("Start: " + towerOfHanoi.stacks);
    towerOfHanoi.solve(size);
    System.out.println("End: " + towerOfHanoi.stacks);
  }

  public void solve(int num) {
    solveInternal(num, StackType.SRC, StackType.DEST, StackType.AUX);
  }

  private void solveInternal(int num, StackType source, StackType destination, StackType auxiliary) {

    if (num > 0) {
      solveInternal(num - 1, source, auxiliary, destination);

      var value = this.stacks.get(source).pop();
      this.stacks.get(destination).push(value);
      System.out.println(String.format("Move disk %d from %s to %s -> %s", value, source, destination, this.stacks));

      solveInternal(num - 1, auxiliary, destination, source);
    }
  }

  enum StackType {
    SRC,
    DEST,
    AUX
  }
}
