import java.util.concurrent.ConcurrentHashMap;

public class Exemplo3 {

    public static ConcurrentHashMap<Long, Parametro> concurrentHashMap = new ConcurrentHashMap();

    public static void main(String[] args) {

        for (long i = 0; i < 100000; i++) {
            long chave = i;

            Servico servico = new Servico();

            new Thread(() -> {
                servico.salvar(chave);
                Long chaveRetorno = servico.buscar(chave);

                if (chave != chaveRetorno)
                    System.out.printf("%s %s efeito colateral\n", chave, chaveRetorno);

            }).start();
        }
    }

    public static class Servico {

        public void salvar(Long codigo) {
            concurrentHashMap.put(codigo, new Parametro(codigo));
        }

        public Long buscar(Long codigo) {
            return concurrentHashMap.get(codigo).getCodigo();
        }
    }
}
