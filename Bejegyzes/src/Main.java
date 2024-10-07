import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Bejegyzesek> bejegyzesek = new ArrayList<>();
        bejegyzesek.add(new Bejegyzesek("Szerzo1", "Ez az első bejegyzés."));
        bejegyzesek.add(new Bejegyzesek("Szerzo2", "Ez a második bejegyzés."));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Kérlek, adj meg egy darabszámot: ");
        int darabszam;
        try {
            darabszam = Integer.parseInt(scanner.nextLine());
            if (darabszam < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Hibás számformátum. Természetes számot adj meg.");
            return;
        }

        for (int i = 0; i < darabszam; i++) {
            System.out.print("Add meg a szerző nevét: ");
            String szerzo = scanner.nextLine();
            System.out.print("Add meg a tartalmat: ");
            String tartalom = scanner.nextLine();
            bejegyzesek.add(new Bejegyzesek(szerzo, tartalom));
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Diák\\IdeaProjects\\Bejegyzes\\src\\bejegyzesek.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    bejegyzesek.add(new Bejegyzesek(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Hiba történt a fájl beolvasása közben.");
        }

        Random random = new Random();
        int osszLikes = bejegyzesek.size() * 20;
        for (int i = 0; i < osszLikes; i++) {
            int randomIndex = random.nextInt(bejegyzesek.size());
            bejegyzesek.get(randomIndex).like();
        }

        if (bejegyzesek.size() > 1) {
            System.out.print("Adj meg egy új tartalmat a második bejegyzéshez: ");
            String ujTartalom = scanner.nextLine();
            bejegyzesek.get(1).setTartalom(ujTartalom);
        }

        for (Bejegyzesek bejegyzes : bejegyzesek) {
            System.out.println(bejegyzes);
        }

        Bejegyzesek legnepszerubb = Collections.max(bejegyzesek, Comparator.comparing(Bejegyzesek::getLikeok));
        System.out.println("A legnépszerűbb bejegyzés like-jainak száma: " + legnepszerubb.getLikeok());

        boolean vanTobbMint35 = bejegyzesek.stream().anyMatch(bejegyzes -> bejegyzes.getLikeok() > 35);
        System.out.println(vanTobbMint35 ? "Van olyan bejegyzés, amely 35-nél több like-ot kapott." : "Nincs ilyen bejegyzés.");

        long kevesebbMint15 = bejegyzesek.stream().filter(bejegyzes -> bejegyzes.getLikeok() < 15).count();
        System.out.println("Ennyi bejegyzés kapott 15-nél kevesebb like-ot: " + kevesebbMint15);

        bejegyzesek.sort(Comparator.comparing(Bejegyzesek::getLikeok).reversed());
        for (Bejegyzesek bejegyzes : bejegyzesek) {
            System.out.println(bejegyzes);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bejegyzesek_rendezett.txt"))) {
            for (Bejegyzesek bejegyzes : bejegyzesek) {
                writer.write(bejegyzes.toString());
                writer.newLine();
            }
            System.out.println("Rendezett lista kiírva a fájlba.");
        } catch (IOException e) {
            System.out.println("Hiba történt a fájl írása közben.");
        }
    }
}
