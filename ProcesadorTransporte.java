import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProcesadorTransporte {

    private final List<RegistroTransporte> registros;

    public ProcesadorTransporte(List<RegistroTransporte> registros) {
        this.registros = List.copyOf(registros);
    }

    public Map<String, Long> calcularAfluenciaporestacion() {

        return registros.stream()
                .filter(r -> r.getAccion().equalsIgnoreCase("entrada"))
                .collect(Collectors.groupingBy(
                        RegistroTransporte::getEstacion,
                        Collectors.counting()
                ));
    }

    public Map<Integer, Long> identificarHorasPico() {

    return registros.stream()
            .collect(Collectors.groupingBy(
                    r->r.getTimestamp().getHour(),
                    Collectors.counting()
            ));
    }
    public Map<String, Long> rutasMasUtilizadas() {

        return registros.stream()
                .collect(Collectors.groupingBy(
                        RegistroTransporte::getRuta,
                        Collectors.counting()
                ));
    }
    public Map<String, List<String>> patronesViajePorUsuario() {

        return registros.stream()
                .collect(Collectors.groupingBy(
                        RegistroTransporte::getIdUsuario,
                        Collectors.mapping(
                                RegistroTransporte::getEstacion,
                                Collectors.toList()
                        )
                ));
    }
    public List<String> detectarSobrecargaRutas() {

        long umbral =1;

         return registros.stream()
                .collect(Collectors.groupingBy(
                        RegistroTransporte::getRuta,
                        Collectors.counting()
                ))
                 .entrySet()
                 .stream()
                 .filter(e->e.getValue()> umbral)
                 .map(Map.Entry::getKey)
                 .collect(Collectors.toList());
    }
    public double calcularTiempoPromedioEntreEstaciones() {
        if (registros.size()< 2) {
            return 0;
        }
        long totalMinutos = 0;

        for(int i = 1; i < registros.size(); i++) {
           totalMinutos += java.time.Duration.between(
              registros.get(i - 1).getTimestamp(),
              registros.get(i).getTimestamp()
           ).toMinutes();
        }
        return (double) totalMinutos / (registros.size()- 1);
        }
}