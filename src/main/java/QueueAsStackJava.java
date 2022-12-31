import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.Data;

@Data
public class QueueAsStackJava<T> {

  private final Deque<T> mainQueue;
  private final Deque<T> auxQueue;

  public QueueAsStackJava() {
    this(new ArrayDeque<>(), new ArrayDeque<>());
  }

  public QueueAsStackJava(final Deque<T> mainQueue, final Deque<T> auxQueue) {
    this.mainQueue = mainQueue;
    this.auxQueue = auxQueue;
  }

  public void add(T element) {
    this.mainQueue.push(element);
  }

  public Optional<T> get() {
    if (mainQueue.isEmpty() && auxQueue.isEmpty()) {
      return Optional.empty();
    }

    if (auxQueue.isEmpty()) {
      while (!mainQueue.isEmpty()) {
        auxQueue.push(mainQueue.pop());
      }
    }
    return Optional.of(auxQueue.pop());
  }

  public static void main(String[] args) {
    var queue = new QueueAsStackJava<String>();

    Stream.of("A", "B", "C").forEach(queue::add);
    System.out.println(queue);

    System.out.println(queue.get());
    System.out.println(queue);
  }
}
