import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {

    private static Executor threadPool = Executors.newFixedThreadPool(1000);

    public static ThreadLocal<Long> df = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        Main main = new Main();

        for (long i = 0; i < 100000; i++) {
            long id = i;

            threadPool.execute(() -> {

                main.setDf(id);
                Long dfRetorno = main.getDf();

                if(id != dfRetorno){
                    System.out.printf("%s %s efeito colateral\n", id, dfRetorno);
                }

            });
        }

        Thread.sleep(1000l);

        System.out.printf("fim ");
    }

    public synchronized Long getDf() {
        return df.get();
    }

    public synchronized void setDf(Long df) {
        this.df.set(df);
    }
}
