/**
 * La clase modela un sencillo podómetro que registra información acerca de
 * los pasos, distancia... que una persona realiza durante una semana
 * 
 * @ Carlos Arévalo
 * 27-10-21
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7; 
    private String marca;
    private char sexo;
    private double longitudZancada;
    private double altura;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private int totalPasosLaborables;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int caminatasNoche;
    private int tiempo;
    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        sexo = MUJER;
        longitudZancada = 0;
        altura = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalPasosLaborables = 0;
        totalDistanciaFinSemana = 0;
        totalDistanciaSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;  
    }

    /**
     * Accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     */
    public void configurar(double queAltura, char queSexo) {
        sexo = queSexo;
        altura = queAltura;
        if (sexo == HOMBRE) {
            longitudZancada = queAltura * ZANCADA_HOMBRE;
        }
        else {
            longitudZancada = queAltura * ZANCADA_MUJER;
        }
    }

    /**
     * Recibe cuatro parámetros que supondremos correctos:
     *  pasos - el nº de pasos caminados
     *  dia - nº de día de la semana en que se ha hecho la caminata 
     *      (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *  horaInicio – hora de inicio de la caminata
     *  horaFin – hora de fin de la caminata
     * A partir de estos parámetros el método debe realizar ciertos cálculos
     * y  actualizará el podómetro adecuadamente  
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        switch (dia){      
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
                totalPasosLaborables += pasos;
                totalDistanciaSemana += pasos;
                break;
            case 6:
                totalPasosSabado += pasos;
                totalDistanciaFinSemana += pasos;
                break;
            default:
                totalPasosDomingo += pasos;
                totalDistanciaFinSemana += pasos;
                break;
        }
        totalDistanciaFinSemana = (totalPasosSabado + totalPasosDomingo) * longitudZancada;
        totalDistanciaSemana = totalPasosLaborables * longitudZancada;
        if (horaInicio >= 2100) {
            caminatasNoche++;
        }
    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada) 
     */
    public void printEstadísticas() {
        System.out.println("Estadísticas");
        System.out.println("*********************************");
        System.out.println("Distancia recorrida toda la semana: " + totalDistanciaSemana / 1000 + "km");
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinSemana / 1000 + "km");
        System.out.println();
        System.out.println("Nº pasos días laborables: " + totalPasosLaborables);
        System.out.println("Nº pasos SÁBADO: " + totalPasosSabado);
        System.out.println("Nº pasos DOMINGO: " + totalPasosDomingo);
        System.out.println();
        System.out.println("Nº caminatas realizadas a partir de las 21h.: " + caminatasNoche);
        System.out.println();
        System.out.println("Tiempo total caminado en la semana: ");
        System.out.println("Día/s con más pasos caminados: ");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado....
     */
    public void printConfiguracion() {
        System.out.println("Configuración del podómetro");
        System.out.println("*************************");
        System.out.println();
        System.out.println("Altura: " + altura);
        System.out.println("Sexo: " + sexo); 
        System.out.println("Longitud zancada: " + longitudZancada + "mtos"); 
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        int diaMayorNumeroPasos;
        String dia = "";
        if (totalPasosLaborables > totalPasosSabado) {
            diaMayorNumeroPasos = totalPasosLaborables;
            dia = "Laborables";
        }
        else {
            diaMayorNumeroPasos = totalPasosSabado;
            dia = "Sabado";
        }
        
        if (totalPasosDomingo > diaMayorNumeroPasos) {
            diaMayorNumeroPasos = totalPasosDomingo;
            dia = "Domingo";
        }
        return dia;
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo que se establece a MUJER.
     * La marca no varía
     */    
    public void reset() {
        sexo = MUJER;
        longitudZancada = 0;
        altura = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalPasosLaborables = 0;
        totalDistanciaFinSemana = 0;
        totalDistanciaSemana = 0;
        tiempo = 0;
        caminatasNoche = 0; 
    }
}
