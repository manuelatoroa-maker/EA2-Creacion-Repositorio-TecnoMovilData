import  java.time.LocalDateTime;
import java.util.List;

public class Main{

    public static void main(String[]args){

        RegistroTransporte r1 = new RegistroTransporte(
                "U001",
                "Ruta 1",
                "Estacion Norte",
                "Entrada",
                LocalDateTime.now()
        );

        RegistroTransporte r2 = new RegistroTransporte(
                "U002",
                "Ruta 1",
                "Estacion Norte",
                "entrada",
                LocalDateTime.now()
        );

        RegistroTransporte r3 = new RegistroTransporte(
                "U003",
                "Ruta 2",
                "Estacion Centro",
                "entrada",
                LocalDateTime.now()
        );

        List<RegistroTransporte> registros = List.of(r1,r2,r3);

        ProcesadorTransporte procesador = new ProcesadorTransporte(registros);

        System.out.println("Afluencia por estación:");
        System.out.println(procesador.calcularAfluenciaporestacion());

        System.out.println("\nHoras pico:");
        System.out.println(procesador.identificarHorasPico());

        System.out.println("\nRutas más utilizadas:");
        System.out.println(procesador.rutasMasUtilizadas());

        System.out.println("\nViaje Por Usuario:");
        System.out.println(procesador.patronesViajePorUsuario());

        System.out.println("\nSobrecarga en rutas:");
        System.out.println(procesador.detectarSobrecargaRutas());

        System.out.println("\nTiempo promedio entre estaciones:");
        System.out.println(
                procesador.calcularTiempoPromedioEntreEstaciones()+"minutos"
        );
    }
}