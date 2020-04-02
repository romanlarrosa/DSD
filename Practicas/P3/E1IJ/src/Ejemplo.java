//Implementa la interfaz remota (Ejemplo_I)
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.lang.Thread;

public class Ejemplo implements Ejemplo_I {

    public Ejemplo() {
        super();
    }

    public void escribir_mensaje(int id) {
        System.out.println("Recibida peticion de proceso:" + id);
        if (id == 0) {
            try{
                System.out.println("Empiezo a dormir...");
                Thread.sleep(5000);
                System.out.println("He terminado de dormir.");
            }catch(Exception e) {
                System.err.println("Ejemplo exception: ");
                e.printStackTrace();
            }
        }
        System.out.println("\nHebra " + id);
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String nombre_objeto_remoto = "Ejemplo_I";
            Ejemplo_I prueba = new Ejemplo();
            Ejemplo_I stub = (Ejemplo_I) UnicastRemoteObject.exportObject(prueba, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(nombre_objeto_remoto, stub);
            System.out.println("Ejemplo bound: OK");
        }catch(Exception e) {
            System.err.println("Ejemplo exception: ");
            e.printStackTrace();
        }
    }
}