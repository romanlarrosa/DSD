import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.lang.Thread;

public class Ejemplo implements Ejemplo_I {

    public Ejemplo() {
        super();
    }

    public void escribir_mensaje(final String mensaje) {
        System.out.println("Entra Hebra " + mensaje);
        //Buscamos los procesos 0, 10, 20...
        if (mensaje.endsWith("0")) {
            try{
                System.out.println("Empiezo a dormir...");
                Thread.sleep(5000);
                System.out.println("He terminado de dormir.");
            }catch(final Exception e) {
                System.err.println("Ejemplo exception: ");
                e.printStackTrace();
            }
        }
        System.out.println("Sale Hebra " + mensaje);
    }

    public static void main(final String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            final String nombre_objeto_remoto = "Ejemplo_I";
            final Ejemplo_I prueba = new Ejemplo();
            final Ejemplo_I stub = (Ejemplo_I) UnicastRemoteObject.exportObject(prueba, 0);
            final Registry registry = LocateRegistry.getRegistry();
            registry.rebind(nombre_objeto_remoto, stub);
            System.out.println("Ejemplo bound: OK");
        }catch(final Exception e) {
            System.err.println("Ejemplo exception: ");
            e.printStackTrace();
        }
    }
}