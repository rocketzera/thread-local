import java.util.concurrent.ConcurrentHashMap;

public class Exemplo3 {

    public static ConcurrentHashMap<Long, Parametro> concurrentHashMap = new ConcurrentHashMap();

    public static void main(String[] args) {

        for (long i = 0; i < 100000; i++) {
            long codigo = i;

            Servico servico = new Servico();

            new Thread(() -> {
                servico.salvar(codigo);

            }).start();
        }
    }

    public static class Servico {

        public void salvar(Long codigo) {
            concurrentHashMap.put(codigo, new Parametro(codigo));
            Long codigoRetorno = concurrentHashMap.get(codigo).getCodigo();

            if (codigo != codigoRetorno)
                System.out.printf("%s %s efeito colateral\n", codigo, codigoRetorno);
        }
    }
}
