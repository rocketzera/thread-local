import java.util.concurrent.ConcurrentHashMap;

public class Exemplo3 {

    public static ConcurrentHashMap<Long, Parametro> concurrentHashMap = new ConcurrentHashMap();

    public static Parametro parametros;

    public static void main(String[] args) {

        Servico servico = new Servico();

        for (long i = 0; i < 100000; i++) {
            long codigo = i;

            new Thread(() -> {
                servico.salvar(codigo);
                Long codigoRetorno = servico.buscar(codigo);

                if (codigo != codigoRetorno)
                    System.out.printf("%s %s efeito colateral\n", codigo, codigoRetorno);

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
